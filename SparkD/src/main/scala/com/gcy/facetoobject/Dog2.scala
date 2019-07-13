package com.gcy.facetoobject

class Dog2 {
  var id:String = _
  var name:String = _

  //用this关键字来定义辅助构造器
  def this(id:String, name:String){
    //在辅助构造器里一定要先调用主构造器
    this()
    this.id = id
    this.name = name
  }

}

object Dog2{
  def main(args: Array[String]): Unit = {
    val d = new Dog2("123","tom")
    println(d.id+" "+d.name)
  }
}
