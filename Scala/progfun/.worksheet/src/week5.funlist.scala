package week5

object funlist {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(158); 

  def last[T](xs: List[T]): T = xs match {
    case Nil => throw new Error("last of an emplty list")
    case List(x) => x
    case y :: ys => last(ys)
  };System.out.println("""last: [T](xs: List[T])T""");$skip(171); 

  def init[T](xs: List[T]): List[T] = xs match {
    case Nil => throw new Error("init of an empty list")
    case x :: Nil => Nil
    case y :: ys => y :: init(ys)
  };System.out.println("""init: [T](xs: List[T])List[T]""");$skip(129); 

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil => ys
    case z :: zs => z :: concat(zs, ys)
  };System.out.println("""concat: [T](xs: List[T], ys: List[T])List[T]""");$skip(121); 

  def reverse[T](xs: List[T]): List[T] = xs match {
    case Nil => Nil
    case y :: ys => reverse(ys) ++ List(y)
  };System.out.println("""reverse: [T](xs: List[T])List[T]""");$skip(152); 

  def removeAt[T](xs: List[T], n: Int): List[T] = xs match {
    case Nil => Nil
    case y :: ys => if (n == 0) ys else y :: removeAt(ys, n - 1)
  };System.out.println("""removeAt: [T](xs: List[T], n: Int)List[T]""");$skip(232); 

  def flatten(xs: List[Any]): List[Any] = xs match {
    case Nil => Nil
    case y :: ys => y match {
      case Nil => flatten(ys)
      case z :: zs => flatten(z :: zs) ++ flatten(ys)
      case _ => y :: flatten(ys)
    }
  };System.out.println("""flatten: (xs: List[Any])List[Any]""");$skip(35); 

  val a = List(1, 2, 3, 4, 5, 6);System.out.println("""a  : List[Int] = """ + $show(a ));$skip(27); 
  val b = List(-1, -2, -3);System.out.println("""b  : List[Int] = """ + $show(b ));$skip(10); val res$0 = 
  last(a);System.out.println("""res0: Int = """ + $show(res$0));$skip(10); val res$1 = 
  init(b);System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(15); val res$2 = 
  concat(a, b);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(13); val res$3 = 
  reverse(a);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(13); val res$4 = 
  reverse(b);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(17); val res$5 = 
  removeAt(a, 3);System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(17); val res$6 = 
  removeAt(a, 7);System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(17); val res$7 = 
  removeAt(a, 0);System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(52); val res$8 = 
  flatten(List(List(1, 1), 2, List(3, List(5, 8))));System.out.println("""res8: List[Any] = """ + $show(res$8));$skip(48); val res$9 = 
  flatten(List(List(), 2, List(3, List(5, 8))));System.out.println("""res9: List[Any] = """ + $show(res$9))}
}
