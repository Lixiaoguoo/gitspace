package com.gcy.facetoobject

class Dog {
  println(Dog.CONTEXT)
}

object Dog{
  private val CONTEXT = "汪汪汪"

  def apply(): Dog = {
    new Dog
  }

  def apply(x:Int, xs:Int*):Array[Int] ={
    val arr = new Array[Int](xs.length+1)
    arr(0) = x
    var i = 1
    for(x <- xs.iterator) {
      arr(i) = x
      i += 1
    }
    arr
  }

  def apply(name:String): Unit = {
    println(name)
  }

  def main(args: Array[String]): Unit = {
//    val dog =  Dog //不打印 “汪汪汪”
//    val dog2 = new Dog //打印 “汪汪汪”

    //获取了一个单例对象
    val d0 = Dog

    //会调用类 的伴生对象里的参数列表相同的apply 方法
    val dog1 = Dog()
    println(dog1)

    val dog2 = Dog()
    println(dog2)

    val d2 = Dog("123")
    println(d2)

    val d3 = Dog(1,2,3,4,5,6,7,8,9)
    println(d3.toBuffer)

  }
}
