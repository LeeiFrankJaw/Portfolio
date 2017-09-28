package week5

object HigherOrderListFun {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(87); 
  println("Welcome to the Scala worksheet");$skip(235); 

  def map[T, U](xs: List[T])(f: T => U) = {
    def mapAcc(xs: List[T])(f: T => U)(acc: List[U]): List[U] = xs match {
      case Nil => acc.reverse
      case x :: xs1 => mapAcc(xs1)(f)(f(x) :: acc)
    }
    mapAcc(xs)(f)(Nil)
  };System.out.println("""map: [T, U](xs: List[T])(f: T => U)List[U]""");$skip(332); 

  def filter[T](xs: List[T])(f: T => Boolean): List[T] = {
    def filterAcc(xs: List[T])(f: T => Boolean)(acc: List[T]): List[T] =
      xs match {
        case Nil => acc.reverse
        case x :: xs1 =>
          if (f(x)) filterAcc(xs1)(f)(x :: acc)
          else filterAcc(xs1)(f)(acc)
      }
    filterAcc(xs)(f)(Nil)
  };System.out.println("""filter: [T](xs: List[T])(f: T => Boolean)List[T]""");$skip(393); 

  def partition[T](xs: List[T])(f: T => Boolean) = {
    def partAcc(xs: List[T])(f: T => Boolean)(acc: (List[T], List[T])): (List[T], List[T]) =
      xs match {
        case Nil => (acc._1.reverse, acc._2.reverse)
        case x :: xs1 =>
          if (f(x)) partAcc(xs1)(f)(x :: acc._1, acc._2)
          else partAcc(xs1)(f)(acc._1, x :: acc._2)
      }
    partAcc(xs)(f)(Nil, Nil)
  };System.out.println("""partition: [T](xs: List[T])(f: T => Boolean)(List[T], List[T])""");$skip(318); 

  def takeWhile[T](xs: List[T])(f: T => Boolean): List[T] = {
    def takeAcc(xs: List[T])(f: T => Boolean)(acc: List[T]): List[T] =
      xs match {
        case Nil => acc.reverse
        case x :: xs1 =>
          if (f(x)) takeAcc(xs1)(f)(x :: acc)
          else acc.reverse
      }
    takeAcc(xs)(f)(Nil)
  };System.out.println("""takeWhile: [T](xs: List[T])(f: T => Boolean)List[T]""");$skip(166); 

  def dropWhile[T](xs: List[T])(f: T => Boolean): List[T] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      if (f(x)) dropWhile(xs1)(f)
      else xs
  };System.out.println("""dropWhile: [T](xs: List[T])(f: T => Boolean)List[T]""");$skip(348); 

  def span[T](xs: List[T])(f: T => Boolean): (List[T], List[T]) = {
    def spanAcc(xs: List[T])(f: T => Boolean)(acc: List[T]): (List[T], List[T]) =
      xs match {
        case Nil => (acc.reverse, Nil)
        case x :: xs1 =>
          if (f(x)) spanAcc(xs1)(f)(x :: acc)
          else (acc.reverse, xs)
      }
    spanAcc(xs)(f)(Nil)
  };System.out.println("""span: [T](xs: List[T])(f: T => Boolean)(List[T], List[T])""");$skip(182); 

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => {
      val (take, drop) = span(xs)(y => x == y)
      take :: pack(drop)
    }
  };System.out.println("""pack: [T](xs: List[T])List[List[T]]""");$skip(72); 

  def encode[T](xs: List[T]) = map(pack(xs))(x => (x.head, x.length));System.out.println("""encode: [T](xs: List[T])List[(T, Int)]""");$skip(79); 

  def scale(xs: List[Double], factor: Double) =
    map(xs)(x => factor * x);System.out.println("""scale: (xs: List[Double], factor: Double)List[Double]""");$skip(31); 

  val a = List(1, -2, 3, -4);System.out.println("""a  : List[Int] = """ + $show(a ));$skip(34); 
  val b = map(a)(x => x.toDouble);System.out.println("""b  : List[Double] = """ + $show(b ));$skip(50); 
  val c = List("a", "a", "a", "b", "c", "c", "a");System.out.println("""c  : List[String] = """ + $show(c ));$skip(16); val res$0 = 

  scale(b, 2);System.out.println("""res0: List[Double] = """ + $show(res$0));$skip(21); val res$1 = 
  map(a)(x => 2 * x);System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(21); val res$2 = 
  map(a)(x => x * x);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(31); val res$3 = 

  filter(a)(x => x % 2 == 0);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(24); val res$4 = 
  filter(a)(x => x > 0);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(32); val res$5 = 
  partition(a)(x => x % 2 == 0);System.out.println("""res5: (List[Int], List[Int]) = """ + $show(res$5));$skip(27); val res$6 = 
  partition(a)(x => x > 0);System.out.println("""res6: (List[Int], List[Int]) = """ + $show(res$6));$skip(29); val res$7 = 

  takeWhile(a)(x => x < 2);System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(30); val res$8 = 
  takeWhile(c)(x => x == "a");System.out.println("""res8: List[String] = """ + $show(res$8));$skip(27); val res$9 = 
  dropWhile(a)(x => x < 2);System.out.println("""res9: List[Int] = """ + $show(res$9));$skip(30); val res$10 = 
  dropWhile(c)(x => x == "a");System.out.println("""res10: List[String] = """ + $show(res$10));$skip(22); val res$11 = 
  span(a)(x => x < 2);System.out.println("""res11: (List[Int], List[Int]) = """ + $show(res$11));$skip(25); val res$12 = 
  span(c)(x => x == "a");System.out.println("""res12: (List[String], List[String]) = """ + $show(res$12));$skip(12); val res$13 = 

  pack(c);System.out.println("""res13: List[List[String]] = """ + $show(res$13));$skip(12); val res$14 = 
  encode(c);System.out.println("""res14: List[(String, Int)] = """ + $show(res$14))}
}
