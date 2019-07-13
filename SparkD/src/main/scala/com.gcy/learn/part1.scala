package com.gcy.learn

object part1 {
  def main(args: Array[String]): Unit = {
    //使用val 定义的变量是不可变的，相当于java中用final 修饰的变量
    val i = 1
    //使用var 定义的变量是可变的，在scala中鼓励使用val
    var s = "hello"
    //变量名在前，类型在后
    val str:String = "itcast"

    /*
    * 条件表达式
    * */
    val g = 1
    val t = if(g>0) 2 else -2
    println(t)

    var t2 = 0
    if(g>0){
      t2 = 2
    }else {
      t2 = -2
    }
    println(t2)

    //支持混合类型表达式
    val z = if (g>0) 2 else "error"
    println(z)

    //如果缺失else，相当于if(g>0) 2 else ()
    val m = if(g<0) 2
    println(m)

    //在scala中每个表达式都有值，scala中有个Unit类，写做()，相当于java中的void。
    val n = if(g>0) 2 else ()
    println(n)

    //if 和else if
    val k2 = if(g>0) 2 else if(g< -1) 1 else 0
    println(k2)

    var k = 0
    if(g>0){
      k=2
    }else if(g< -1){
      k=1
    }else {
      k = 0
    }
    println(k)

    /*
    * 块表达式
    * */
    val x = 0
    val result = {
      if(x>0){
        -1
      }else if(x< -1){
        2
      }else {
        "error"
      }
    }
    println("x="+x)
println("-------------------------")
    /*
    * 循环
    * */
    for (i <- 1 to 10) {
      print(i)
    }
    println()

    //定义数组
    val arr = Array("a","b","c")
    for(i <- arr){
      print(i)
    }
    println()


    for(i <- 1 to 3; j <- 1 to 3 if i != j){
      println(i+"_"+j)
    }


    val arr2 = Array(1,2,3)
    val t3 = for(i <- arr2)  yield i * 2
    for(i <- t3){
      println(i)
    }

    println("====================")
    val arr3 = Array(1,2,3,4,5,6,7,8,9)
    val arr4 = for(i <- arr3) yield i%2==0
    for (i <- arr4){
      println(i)
    }
    println("===============")
    for (i <- 0 to arr3.length-1) println(arr3(i))
    println("===============")
    for (i <- 0 until arr3.length) println(arr3(i))










  }
}
