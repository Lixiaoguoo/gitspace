package com.gcy.facetoobject

//在scala中，类并不用声明为public
//scala源文件中可以包含多个类，所有这些类都具有共有可见性
class Person {
  //用val修饰的变量是只读属性，有getter但没有setter
  //相当于java中用final修饰的变量
  val id = "hello"

  //用var修饰的变量既有getter又有setter
  var age:Int = 12

  //private 修饰的 变量或方法可以在类内部及伴生对象中使用。
  private val name:String = "唐伯虎"

  //只有在类里能用，在伴生对象里也不能用
  private[this] var pop:String = _

  def printPop:Unit = {
    println(pop)
  }




}

object Person{
  def main(args: Array[String]): Unit = {
    val person = new Person()
    println(person.id+" "+person.age+" "+person.name)
    person.age = 22
    println(person.id+" "+person.age+" "+person.name)

    println(person.printPop)
  }
}

object aa{
  def main(args: Array[String]): Unit = {
    val person = new Person()
    //这里的person对象就访问不到name变量，因为aa不是person类的半生对象
  }
}
