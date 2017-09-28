package week3

object intsets {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(8); val res$0 = 
  Empty;System.out.println("""res0: week3.Empty.type = """ + $show(res$0));$skip(24); 
  val t1 = Empty incl 9;System.out.println("""t1  : week3.NonEmpty = """ + $show(t1 ));$skip(21); 
  val t2 = t1 incl 0;System.out.println("""t2  : week3.NonEmpty = """ + $show(t2 ));$skip(21); 
  val t3 = t2 incl 3;System.out.println("""t3  : week3.NonEmpty = """ + $show(t3 ));$skip(21); 
  val t4 = t3 incl 5;System.out.println("""t4  : week3.NonEmpty = """ + $show(t4 ));$skip(22); 
  val t5 = t2 incl 10;System.out.println("""t5  : week3.NonEmpty = """ + $show(t5 ));$skip(22); 
  val t6 = t5 incl 15;System.out.println("""t6  : week3.NonEmpty = """ + $show(t6 ));$skip(26); 
  val u1 = Empty union t6;System.out.println("""u1  : week3.IntSet = """ + $show(u1 ));$skip(23); 
  val u2 = t4 union t6;System.out.println("""u2  : week3.IntSet = """ + $show(u2 ))}
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
