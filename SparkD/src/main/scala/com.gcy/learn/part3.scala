package com.gcy.learn

import scala.collection.mutable.ArrayBuffer

object part3 {

  //利用下划线可以将方法转换成函数
  def m1(x:String): Unit ={
    println(x)
  }
  val f1 = m1 _

  def main(arga:Array[String]):Unit = {
    val res = m1("hello")
    println(res)
    f1("tom")

    /*
    * 数组操作
    * */
    //创建一个长度为4的数组，默认初始值为0
    val a1 = new Array[Int](4)
    println(a1.toBuffer)
    //a1(0) = "hello"
    a1(1) = 3
    a1(2) = 5
    println(a1.toBuffer)

    //创建一个长度为4的数组，并赋初始值
    val a2 = Array(1,2,3,4)
    println(a2.toBuffer)

    //创建一个变长数组
    val ab = new ArrayBuffer[Int](1)
    println(ab)
    //向变长数组中添加元素
    ab += 1
    ab += (3,4)
    ab ++= Array(11,22)
    ab ++= ArrayBuffer(33,44)
    println(ab)

    val ab1 = ArrayBuffer(2,3,1)
    println(ab1)
    ab1 += 5
    ab1 += (7,9)
    ab1 ++= Array(10,12)
    ab1 ++ ArrayBuffer(21,23)
    println(ab1)

    //在角标 为2的位置增加一个元素为，-3，原来2位置及后边的元素向后移
    ab1.insert(2,-3)
    println(ab1)
    //在角标为5的位置增加三个元素99,88,77，原来5位置及后边的元素后移
    ab1.insert(5,99,88,77)
    println(ab1)

    //在角标为1的位置删除一个元素
    ab1.remove(1,1)
    println(ab1)
    //在角标为6的位置删除2个元素
    ab1.remove(6,2)
    println(ab1)

    /*
    * reverse关键字
    * */
    for(i <- (1 to 4).reverse){
      println(i)
    }











  }


}
