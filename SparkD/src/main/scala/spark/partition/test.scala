package spark.partition
import java.net.URL
import org.apache.spark.{Partitioner, SparkConf, SparkContext}
import scala.collection.mutable
object test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UrlCountPartition").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("E:\\BaiduYunDownload\\大数据\\day29\\itcast.log").map(y =>{
      val splits = y.split("\t")
      ( splits(1),1)
    }).reduceByKey(_+_).map(x => {
      val url = x._1
      val host = new URL(url).getHost
      (host,(x._1,x._2))
    })
    val rdd2 = rdd1.map(_._1).distinct().collect()
    val hostPartitioner = new HostPartitioner(rdd2)
    rdd1.partitionBy(hostPartitioner).mapPartitions(it => {
      it.toList.sortBy(_._2._2).reverse.take(2).iterator
    }).saveAsTextFile("E:\\out2")
  }
}
//以下类继承Partitioner，重写里边的方法，实现自定义分区逻辑
class HostPartitioner(array: Array[String]) extends Partitioner {
  val hostMap = new mutable.HashMap[String,Int]()
  var count = 0
  for(i <- array){
    hostMap += (i -> count)
    count +=1
  }
  override def numPartitions = array.length
  override def getPartition(key: Any) = {
    hostMap.getOrElse(key.toString,0)
  }

}
