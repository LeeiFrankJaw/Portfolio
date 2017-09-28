package week5

object funlist {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def last[T](xs: List[T]): T = xs match {
    case Nil => throw new Error("last of an emplty list")
    case List(x) => x
    case y :: ys => last(ys)
  }                                               //> last: [T](xs: List[T])T

  def init[T](xs: List[T]): List[T] = xs match {
    case Nil => throw new Error("init of an empty list")
    case x :: Nil => Nil
    case y :: ys => y :: init(ys)
  }                                               //> init: [T](xs: List[T])List[T]

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil => ys
    case z :: zs => z :: concat(zs, ys)
  }                                               //> concat: [T](xs: List[T], ys: List[T])List[T]

  def reverse[T](xs: List[T]): List[T] = xs match {
    case Nil => Nil
    case y :: ys => reverse(ys) ++ List(y)
  }                                               //> reverse: [T](xs: List[T])List[T]

  def removeAt[T](xs: List[T], n: Int): List[T] = xs match {
    case Nil => Nil
    case y :: ys => if (n == 0) ys else y :: removeAt(ys, n - 1)
  }                                               //> removeAt: [T](xs: List[T], n: Int)List[T]

  def flatten(xs: List[Any]): List[Any] = xs match {
    case Nil => Nil
    case y :: ys => y match {
      case Nil => flatten(ys)
      case z :: zs => flatten(z :: zs) ++ flatten(ys)
      case _ => y :: flatten(ys)
    }
  }                                               //> flatten: (xs: List[Any])List[Any]

  val a = List(1, 2, 3, 4, 5, 6)                  //> a  : List[Int] = List(1, 2, 3, 4, 5, 6)
  val b = List(-1, -2, -3)                        //> b  : List[Int] = List(-1, -2, -3)
  last(a)                                         //> res0: Int = 6
  init(b)                                         //> res1: List[Int] = List(-1, -2)
  concat(a, b)                                    //> res2: List[Int] = List(1, 2, 3, 4, 5, 6, -1, -2, -3)
  reverse(a)                                      //> res3: List[Int] = List(6, 5, 4, 3, 2, 1)
  reverse(b)                                      //> res4: List[Int] = List(-3, -2, -1)
  removeAt(a, 3)                                  //> res5: List[Int] = List(1, 2, 3, 5, 6)
  removeAt(a, 7)                                  //> res6: List[Int] = List(1, 2, 3, 4, 5, 6)
  removeAt(a, 0)                                  //> res7: List[Int] = List(2, 3, 4, 5, 6)
  flatten(List(List(1, 1), 2, List(3, List(5, 8))))
                                                  //> res8: List[Any] = List(1, 1, 2, 3, 5, 8)
  flatten(List(List(), 2, List(3, List(5, 8))))   //> res9: List[Any] = List(2, 3, 5, 8)
}