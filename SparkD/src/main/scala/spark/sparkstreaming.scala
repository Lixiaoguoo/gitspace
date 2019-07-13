package spark

import java.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object sparkstreaming {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("streamingSpark")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Seconds(4))

    val kafkaParams = Map[String,Object](
      "bootstrap.servers" -> "192.168.2.3:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test1",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false:java.lang.Boolean)
    )

    val topics = Array("gcy2")
    val stream = KafkaUtils.createDirectStream[String,String](ssc,PreferConsistent,Subscribe[String,String](topics,kafkaParams))

    val words = stream.map(re => (re.key()+"|"+re.value()+"|"+re.offset()+"|"+re.partition()))
    words.map(x => x).print()

    stream.foreachRDD(rdd => {
      rdd.foreachPartition(partition => {
        val connection = HBaseUtil.getConnection
        val tableName = TableName.valueOf("test")
        val table = connection.getTable(tableName)
        val puts = new util.ArrayList[Put]

        try {
          partition.foreach(arr => {
            val value = arr.value()



            val put = new Put(Bytes.toBytes(""))
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(value), Bytes.toBytes("tt_"+value))
            puts.add(put)



            // 这里为了提高性能，每一万条入一次HBase库
            if (puts.size % 10 == 0) {
              table.put(puts)
              puts.clear()
            }
          })
        } catch {
          case e: Exception => e.printStackTrace
        } finally {
          table.put(puts)
          table.close
          connection.close
        }
      })
    })

    ssc.start()
    ssc.awaitTermination()
  }

}

object HBaseUtil {
  val conf: Configuration = HBaseConfiguration.create()
  conf.set("hbase.zookeeper.quorum", "192.168.42.101:2181,192.168.42.102:2181,192.168.42.101:2181")
  def getConnection(): Connection = {
    return ConnectionFactory.createConnection(conf)
  }
}
