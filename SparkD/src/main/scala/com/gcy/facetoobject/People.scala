package com.gcy.facetoobject

/*
* scala中主构造器和类名交织在一起，
*gender:String 相当于private[this] 在伴生对象中也访问不到
* val age:Int = 18 相当于给age一个初始值，在床架对象时，不给age传参，age就为初始值，若给age传参，age就被修改成传进来的参数值
*
* */
class People (val id:String,var name:String,gender:String,val age:Int = 18){


}

object People{
  def main(args: Array[String]): Unit = {
    val p = new People("123","tom","f")
    println(p.id+" "+p.name+" "+p.age)
    val p2 = new People("123","tom","f",20)
    println(p2.id+" "+p2.name+" "+p2.age)



  }
}
