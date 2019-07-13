package spark.partition

import java.util.UUID

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

object hashPartition {

  def me(json:String) :String = {
    var result:String = ""
    var res = ""
    if(json.contains("!,!")){
      res = json.substring(0,json.length-3)
    }else{
      res = json
    }
    result = JSON.parseObject(res).getString("table").split("\\.")(1)
    result
  }

  def paseJson(x:String):(String,String,mutable.Map[String,String]) = {
    var word = ""
    if(x.contains("!,!")){
      word = x.substring(0,x.length-3)
    }else {
      word = x
    }
    val json = JSON.parseObject(word)
    val op_type = json.getString("op_type")
    val table = json.getString("table").split("\\.")(1)
    val map = mutable.Map[String,String]()
    if(!"D".equals(op_type)){
      val after = json.getJSONObject("after")

      import scala.collection.JavaConversions._
      for(i <- after.keySet()){
        var value = after.getString(i)
        if(value == null) {
          value = "null"
        }
        map.put(i,value)
      }
    }
    val uuid = UUID.randomUUID().toString.replaceAll("-","")
    (uuid,table,map)
  }

//  val tablemap = Map[String,String]("LACOM"->"LACOM","LCCONT"->"LCCONT","LCCONTSTATE"->"LCCONTSTATE","LPEDORITEM"->"LPEDORITEM")
//  def getTableName(x:String):String = {
//    tablemap.getOrElse(x,"ALL")
//  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("hashPartition")
    val sc = new SparkContext(conf)

    //广播变量，也可以从hdfs上读取一个文件，转换成rdd，作为规则，广播出去、
    val tablemap = Map[String,String]("LACOM"->"LACOM","LCCONT"->"LCCONT","LCCONTSTATE"->"LCCONTSTATE","LPEDORITEM"->"LPEDORITEM")
    val broadCase = sc.broadcast(tablemap)

    val ssc = new StreamingContext(sc,Seconds(8))

    val kafkaParams = Map[String,Object](
      "bootstrap.servers" -> "192.168.2.3:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test1",
      //earliest
      //latest
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false:java.lang.Boolean)
    )


  val customPart = new Partitioner(){
    val map = Map("LACOM"->0,"LCCONT"->1,"LCCONTSTATE"->2,"LPEDORITEM"->3)
    val partitions  =4
    override def numPartitions: Int = {
      return partitions
    }

    override def getPartition(key: Any): Int = {
      map.getOrElse(key.toString,0)
    }
  }


    val topics = Array("lmis")
    val stream = KafkaUtils.createDirectStream[String,String](ssc,PreferConsistent,Subscribe[String,String](topics,kafkaParams))
//    val rdd1 = stream.repartition(3)
//    val rdd2 = rdd1.map(x => (me(x.value()),1)).groupByKey()
//    rdd2.saveAsTextFiles("hdfs://192.168.2.3:8020/gcy/partition/1")

    //reduceByKey 和 groupByKey会产生shuffer过程
    val rdd = stream.map(x => (me(x.value()),x.value())).groupByKey(customPart)
      rdd.print()
      rdd.saveAsTextFiles("hdfs://192.168.2.3:8020/gcy/partition/2")



    val rdd2 = stream.map(x => (me(x.value()),x.value())).groupByKey(customPart).flatMap(_._2)
    rdd2.foreachRDD(foreach => {
      foreach.foreachPartition(partition => {
        val connection = HBaseUtil.getConnection()
        try{
          partition.foreach(arr => {
            val touple = paseJson(arr)
//            println("######## "+touple.toString())

            val table = connection.getTable(TableName.valueOf(broadCase.value.getOrElse(touple._2,"ALL")))
            val put = new Put(Bytes.toBytes(touple._1+"_"+touple._2))
            if(touple._3.size>0){
              for(i <- touple._3){
                put.addColumn(Bytes.toBytes("info"),Bytes.toBytes(i._1),Bytes.toBytes(i._2))
              }
            }
            table.put(put)

            table.close()
          })
        }catch {
          case e:Exception => e.printStackTrace()
        }finally {
          connection.close()
        }
      })
    })







    ssc.start()
    ssc.awaitTermination()
  }

}


object HBaseUtil{
  val conf:Configuration = HBaseConfiguration.create()
  conf.set("hbase.zookeeper.quorum","192.168.2.3:2181,192.168.2.4:2181,192.168.2.5:2181")
  def getConnection():Connection = {
    return ConnectionFactory.createConnection(conf)
  }
}

























