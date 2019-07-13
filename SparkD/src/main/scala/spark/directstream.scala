package spark

import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Assign
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object directstream {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("directstream")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Seconds(4))

    val fromOffsets = collection.mutable.Map[TopicPartition,Long]()
    fromOffsets += (new TopicPartition("gcy2",2) -> 60)
    fromOffsets.toMap

    val kafkaParams = Map[String,Object](
      "bootstrap.servers" -> "192.168.2.3:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test3",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false:java.lang.Boolean)
    )

    val stream = KafkaUtils.createDirectStream[String,String](
      ssc,
      PreferConsistent,
      Assign[String,String](fromOffsets.keys,kafkaParams,fromOffsets)
    )

    val words = stream.map(re => (re.key(),re.value(),re.offset(),re.partition(),re.topic())).print()



    ssc.start()
    ssc.awaitTermination()
  }

}
