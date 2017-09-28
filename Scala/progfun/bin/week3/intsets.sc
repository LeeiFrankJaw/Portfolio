package week3

object intsets {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  Empty                                           //> res0: week3.Empty.type = {}
  val t1 = Empty incl 9                           //> t1  : week3.NonEmpty = {9}
  val t2 = t1 incl 0                              //> t2  : week3.NonEmpty = {0, 9}
  val t3 = t2 incl 3                              //> t3  : week3.NonEmpty = {0, 3, 9}
  val t4 = t3 incl 5                              //> t4  : week3.NonEmpty = {0, 3, 5, 9}
  val t5 = t2 incl 10                             //> t5  : week3.NonEmpty = {0, 9, 10}
  val t6 = t5 incl 15                             //> t6  : week3.NonEmpty = {0, 9, 10, 15}
  val u1 = Empty union t6                         //> u1  : week3.IntSet = {0, 9, 10, 15}
  val u2 = t4 union t6                            //> u2  : week3.IntSet = {0, 3, 5, 9, 10, 15}
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
  def toList: List[Int]
}

object Empty extends IntSet {
  def incl(x: Int) = new NonEmpty(x: Int, Empty, Empty)
  def contains(x: Int) = false
  def union(other: IntSet) = other
  def toList = Nil
  override def toString = "{}"
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def incl(x: Int) =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  def contains(x: Int) =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
    
  def union(other: IntSet) =
  	((left union right) union other) incl elem

  def toList = left.toList ::: elem :: right.toList
  
  def middle(a: List[Int]) = {
    def iter(a: List[Int], acc: String): String =
      if (a.isEmpty) acc
      else iter(a.tail, acc + ", " + a.head)
    if (a.length > 1) iter(a.tail, "" + a.head)
    else "" + a.head
  }
  
  override def toString = "{" + middle(toList) + "}"
}