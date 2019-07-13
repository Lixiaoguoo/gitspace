package com.gcy.facetoobject

//object 对象就是一个单例对象
object SingletonDemo {
  def main(args:Array[String]):Unit={
    val s = SingletonDemo
    println(s)

    val b = SingletonDemo
    println(b)


  }

}
