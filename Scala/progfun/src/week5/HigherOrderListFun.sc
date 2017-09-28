package week5

object HigherOrderListFun {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def map[T, U](xs: List[T])(f: T => U) = {
    def mapAcc(xs: List[T])(f: T => U)(acc: List[U]): List[U] = xs match {
      case Nil => acc.reverse
      case x :: xs1 => mapAcc(xs1)(f)(f(x) :: acc)
    }
    mapAcc(xs)(f)(Nil)
  }                                               //> map: [T, U](xs: List[T])(f: T => U)List[U]

  def filter[T](xs: List[T])(f: T => Boolean): List[T] = {
    def filterAcc(xs: List[T])(f: T => Boolean)(acc: List[T]): List[T] =
      xs match {
        case Nil => acc.reverse
        case x :: xs1 =>
          if (f(x)) filterAcc(xs1)(f)(x :: acc)
          else filterAcc(xs1)(f)(acc)
      }
    filterAcc(xs)(f)(Nil)
  }                                               //> filter: [T](xs: List[T])(f: T => Boolean)List[T]

  def partition[T](xs: List[T])(f: T => Boolean) = {
    def partAcc(xs: List[T])(f: T => Boolean)(acc: (List[T], List[T])): (List[T], List[T]) =
      xs match {
        case Nil => (acc._1.reverse, acc._2.reverse)
        case x :: xs1 =>
          if (f(x)) partAcc(xs1)(f)(x :: acc._1, acc._2)
          else partAcc(xs1)(f)(acc._1, x :: acc._2)
      }
    partAcc(xs)(f)(Nil, Nil)
  }                                               //> partition: [T](xs: List[T])(f: T => Boolean)(List[T], List[T])

  def takeWhile[T](xs: List[T])(f: T => Boolean): List[T] = {
    def takeAcc(xs: List[T])(f: T => Boolean)(acc: List[T]): List[T] =
      xs match {
        case Nil => acc.reverse
        case x :: xs1 =>
          if (f(x)) takeAcc(xs1)(f)(x :: acc)
          else acc.reverse
      }
    takeAcc(xs)(f)(Nil)
  }                                               //> takeWhile: [T](xs: List[T])(f: T => Boolean)List[T]

  def dropWhile[T](xs: List[T])(f: T => Boolean): List[T] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      if (f(x)) dropWhile(xs1)(f)
      else xs
  }                                               //> dropWhile: [T](xs: List[T])(f: T => Boolean)List[T]

  def span[T](xs: List[T])(f: T => Boolean): (List[T], List[T]) = {
    def spanAcc(xs: List[T])(f: T => Boolean)(acc: List[T]): (List[T], List[T]) =
      xs match {
        case Nil => (acc.reverse, Nil)
        case x :: xs1 =>
          if (f(x)) spanAcc(xs1)(f)(x :: acc)
          else (acc.reverse, xs)
      }
    spanAcc(xs)(f)(Nil)
  }                                               //> span: [T](xs: List[T])(f: T => Boolean)(List[T], List[T])

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => {
      val (take, drop) = span(xs)(y => x == y)
      take :: pack(drop)
    }
  }                                               //> pack: [T](xs: List[T])List[List[T]]

  def encode[T](xs: List[T]) = map(pack(xs))(x => (x.head, x.length))
                                                  //> encode: [T](xs: List[T])List[(T, Int)]

  def scale(xs: List[Double], factor: Double) =
    map(xs)(x => factor * x)                      //> scale: (xs: List[Double], factor: Double)List[Double]

  val a = List(1, -2, 3, -4)                      //> a  : List[Int] = List(1, -2, 3, -4)
  val b = map(a)(x => x.toDouble)                 //> b  : List[Double] = List(1.0, -2.0, 3.0, -4.0)
  val c = List("a", "a", "a", "b", "c", "c", "a") //> c  : List[String] = List(a, a, a, b, c, c, a)

  scale(b, 2)                                     //> res0: List[Double] = List(2.0, -4.0, 6.0, -8.0)
  map(a)(x => 2 * x)                              //> res1: List[Int] = List(2, -4, 6, -8)
  map(a)(x => x * x)                              //> res2: List[Int] = List(1, 4, 9, 16)

  filter(a)(x => x % 2 == 0)                      //> res3: List[Int] = List(-2, -4)
  filter(a)(x => x > 0)                           //> res4: List[Int] = List(1, 3)
  partition(a)(x => x % 2 == 0)                   //> res5: (List[Int], List[Int]) = (List(-2, -4),List(1, 3))
  partition(a)(x => x > 0)                        //> res6: (List[Int], List[Int]) = (List(1, 3),List(-2, -4))

  takeWhile(a)(x => x < 2)                        //> res7: List[Int] = List(1, -2)
  takeWhile(c)(x => x == "a")                     //> res8: List[String] = List(a, a, a)
  dropWhile(a)(x => x < 2)                        //> res9: List[Int] = List(3, -4)
  dropWhile(c)(x => x == "a")                     //> res10: List[String] = List(b, c, c, a)
  span(a)(x => x < 2)                             //> res11: (List[Int], List[Int]) = (List(1, -2),List(3, -4))
  span(c)(x => x == "a")                          //> res12: (List[String], List[String]) = (List(a, a, a),List(b, c, c, a))

  pack(c)                                         //> res13: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a
                                                  //| ))
  encode(c)                                       //> res14: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
}