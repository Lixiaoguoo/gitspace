package com.gcy.facetoobject

import scala.io.Source

//在用new 创建一个对象的时候，主构造器中的内容会被执行，而主构造器中的方法及函数不会被执行，而是会被加载。
class MissRight {
  val name = "tom"
  println(name)

  def sayHi={
    println("Hi~~")
  }

  try{
    val text = Source.fromFile("c://test.txt").mkString
    println(text)
  }catch {
    case e:Exception => e.printStackTrace()
  }finally {
    println("finally!!")
  }

}

object MissRight{
  def main(args:Array[String]):Unit ={
    val m = new MissRight
    m.sayHi

  }
}
