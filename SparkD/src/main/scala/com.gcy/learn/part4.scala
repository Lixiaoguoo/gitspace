package com.gcy.learn

import scala.collection.mutable
/*
*  集合操作
* */
object part4 {
  def main(arga:Array[String]):Unit={
    //filter 用法
    val arr = Array(1,2,3,4,5,6,7,8,9)
    val arr2 = arr.filter(_ % 2 == 0)
    println(arr.toBuffer)
    println(arr2.toBuffer)

    //map用法
    val arr3 = arr.filter(_ % 2 == 0).map(_ * 10)
    println(arr3.toBuffer)

    //sum用法
    val i = arr.sum
    println(i)

    //sorted
    val arr4 = Array(6,8,3,1,2,9)
    val arr5 = arr4.sorted
    println(arr5.toBuffer)

    //reverse
    println(arr5.reverse.toBuffer)

    //sortBy
    val arr6 = arr4.sortBy(_ % 3)
    println(arr6.toBuffer)

    //sortWith
    val arr7 = arr4.sortWith(_ < _)
    println(arr7.toBuffer)

    val arr8 = arr4.sortWith(_ > _)
    println(arr8.toBuffer)

    //不可变的map集合
    val m = Map("a" -> 1,"b" -> 2)
    println(m("a"))

    /*
    * Error:(41, 5) value update is not a member of scala.collection.immutable.Map[String,Int]
    m("a") = 10
    * */

    //可变的集合
    val mm = mutable.Map("i" -> 1,"j" -> 2)
    println(mm("i"))
    mm("i")=10
    println(mm("i"))

    mm("k") = 20
    println(mm.toBuffer)

    mm += ("o" -> 6)
    mm +=(("p",7))
    println(mm.toBuffer)
    mm+= (("p",8))
    println(mm.toBuffer)


    //getOrElse
    println(mm.getOrElse("c",-1))

    //元组
      val touple = (1,"spark",2.0)
    println(touple._2)

    val pair = ("spark",22)
    mm += pair
    println(mm.toBuffer)

    mm += (("solr",11),("hbase",33))
    println(mm.toBuffer)

    //拉链操作
    val a = Array("a","b","c")
    val b = Array(1,2,3)
    val c = a.zip(b)
    val d = b.zip(a)
    println(c.toBuffer)
    println(d.toBuffer)







  }


}
