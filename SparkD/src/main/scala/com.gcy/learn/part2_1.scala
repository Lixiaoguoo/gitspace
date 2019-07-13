package com.gcy.learn

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


object part2_1 {
  def main(args:Array[String]):Unit={
    val i:Int = 1
    var j:String = "hello"
    j = "tom"

    val g:Int = -2

    val k = if(g>0) 2
    println(k)

    val k1 = if(g>0) 2 else if(g< -2) 0 else "error"
    println(k1)

    val k2 = {
      if(g>0){
        2
      }else if(g< -2){
        0
      }else {
        "error"
      }
    }
    println(k2)

    for(i <- 1 to 4){
      println(i)
    }

    val arr = Array(1,2,3,4)
    for(i <- arr){
      println(i)
    }

    for(i <- 0 until arr.length){
      println(arr(i))
    }

    val  arr2 = for(i <- arr) yield i * 2
    println(arr2.toBuffer)

  }
}
