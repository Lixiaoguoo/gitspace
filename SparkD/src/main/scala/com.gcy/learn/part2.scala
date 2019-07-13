package com.gcy.learn

object part2 {
  /*
  * 1.定义一个方法
  * def 关键字定义一个方法
  * m1是方法名
  * （x:Int,y:Int）是参数列表
  * :Int 是方法返回值类型
  * {x*y} 是方法体
  *
  * 方法的返回值类型可以不写，编译器可以自动推断出来，但是对于递归方法，必须制定返回值类型
  * */
  def m1(x: Int, y: Int):Int = {
    x * y
  }

  /*
  * 定义一个函数
  * */
  //这是一个匿名函数
  (x:Int, y:Int) => { x*y }

  //这是一个有名字的函数，函数名为fun1
  val fun1 = (x: Int, y: Int) => {x + y}

  //函数的另外一种定义方式
   //函数名：入参个数及类型 => 返回值个数及类型 = {入参的引用 => 函数体}
  val fun2: Int => (String, Int) = {x => ("tom",x)}
  val fun3:(String,Int) => (String) = {(a,b) => "I`m "+a + ", "+b + " years old"}

  /*
  * 方法和函数的区别：
  * 1.用def关键字修饰的为方法，函数没有修饰的关键字，可以用普通的变量来引用
  * */


  //定义一个方法
  def m1(a: Int=> Int):Int = {
    a(3)
  }

  val f1 = (x:Int) => {x*10}


  def main(args:Array[String]):Unit = {
    val r = m1(2,3)
    println(r)

    val r1 = fun1(3,4)
    println(r1)

    val r2 = m1(f1)
    println(r2)

    //方法中传入匿名函数时，可以不指定函数的 参数类型
    val arr = Array(1,2,3,4,5,6,7,8)
    val arr2  = arr.map(x => x * 5)
    val arr3 = arr.map(x => x + 1)
    println(arr.toBuffer)
    println(arr2.toBuffer)
    println(arr3.toBuffer)

    val r3 = fun3("tom",12)
    println(r3)










  }

}
