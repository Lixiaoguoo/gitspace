package com.gcy.learn

object part6 {
  def main(args:Array[String]):Unit={
    val arr = List(1,2,3,4,5,6,7,8,9)
    println(arr.sum)

    //arr 将集合并行化元素
    //reduce将非特定顺序的二元操作应用到所有元素
    //默认调用reduceLeft
    val res = arr.par.reduce(_+_)
    println(res)

    //fold 折叠 无特定顺序
    //foldLeft 从左到右的顺序
    //foldright 从右到做的顺序
    val res1 = arr.par.fold(0)(_-_)
    println(res1)

    //聚合 aggregate
    val lst = List(List(1,2,3),List(4,5),List(6),List(0))
    val res2 = lst.aggregate(0)(_+_.sum,_+_)
    println(res2)

    //求并集
    val lst1 = List(4,5,6,7)
    val lst2 = List(1,2,3,4)
    val r1 = lst1.union(lst2)
    println(r1)

    //求交集
    val r2 = lst1.intersect(lst2)
    println(r2)

    //求差集
    val r3 = lst1.diff(lst2)
    println(r3)







  }
}
