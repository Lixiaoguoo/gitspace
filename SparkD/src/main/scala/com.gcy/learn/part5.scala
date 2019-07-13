package com.gcy.learn

import scala.collection.mutable.ListBuffer

/*
*序列操作
* */
object part5 {
  /*
:: 该方法被称为cons，意为构造，向队列的头部追加数据，创造新的列表。用法为 x::list,其中x为加入到头部的元素，无论x是列表与否，它都只将成为新生成列表的第一个元素，也就是说新生成的列表长度为list的长度＋1(btw, x::list等价于list.::(x))
:+和+: 两者的区别在于:+方法用于在尾部追加元素，+:方法用于在头部追加元素，和::很类似，但是::可以用于pattern match ，而+:则不行. 关于+:和:+,只要记住冒号永远靠近集合类型就OK了。
::: 该方法只能用于连接两个List类型的集合。
++ 该方法用于连接两个集合，list1++list2 。
  * */

  def main(args:Array[String]):Unit={
    // 字符串列表
    val site = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
    println(site)

    // 整型列表
    val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
    println(nums)

    // 空列表
    val empty = Nil
    println(empty)


    //创建一个不可变的集合
    val lst1 = List(1,2,3,4)
    println(lst1.toBuffer)

    //将0插入到lst1前面，生成一个新的集合
    val lst2 = 0 :: lst1
    println(lst2.toBuffer)

    val lst3 = lst1.::(10)
    println(lst3.toBuffer)

    val lst4 = 20 +: lst1
    println(lst4.toBuffer)

    val lst5 = lst1.+:(30)
    println(lst5)

    //将一个元素加到lst1后边，生成一个新元素
    val lst6 = lst1 :+ 5
    println(lst6)

    val lst7 = lst1.:+(6)
    println(lst7)

    //合并两个新集合
    val lst0 = List(10,20,30)
    val lst8 = lst0 ++ lst1
    println(lst8)

    val lst9 = lst1 ++ lst0
    println(lst9)

    //将lst0 插入到lst1后面生成一个新集合
    val lst10 = lst1 ++: lst0
    println(lst10)

    val lst11 = lst1.:::(lst0)
    println(lst11)


    val lb = ListBuffer(10)
    println(lb)

    val lb2 = ListBuffer(1,2)
    println(lb2)

    val lb3 = lb ++= lb2
    println(lb3)

    val lb4 = lb2 += 3
    println(lb4)

    println("=================================================")
    val lines = List("hello tom hello jerry","hello tom hello kitty")
    val words = lines.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).map(t => (t._1,t._2.size)).toList.sortBy(_._2).reverse
    println(words)

    val words2 = lines.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+ _._2)).toList.sortBy(_._2).reverse
    println(words2)







  }



}
