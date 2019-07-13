package spark.test

import com.alibaba.fastjson.JSON

object test {
  def me(json:String) :String = {
    JSON.parseObject(json).getString("table").split("\\.")(1)
  }

  def main(args: Array[String]): Unit = {
    val str  = "java"
    println("lcduty".hashCode % 5)

  }

}
