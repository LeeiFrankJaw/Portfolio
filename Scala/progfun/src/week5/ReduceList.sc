package week5

object ReduceList {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def reduceLeft[T](xs: List[T])(implicit op: (T, T) => T): T = xs match {
    case Nil => throw new Error("Reduce an empty list")
    case x :: xs1 => foldLeft(xs1)(x)
  }                                               //> reduceLeft: [T](xs: List[T])(implicit op: (T, T) => T)T

  def foldLeft[T, U](xs: List[T])(z: U)(implicit op: (U, T) => U): U = xs match {
    case Nil => z
    case x :: xs1 => foldLeft(xs1)(op(z, x))
  }                                               //> foldLeft: [T, U](xs: List[T])(z: U)(implicit op: (U, T) => U)U

  def reduceRight[T](xs: List[T])(implicit op: (T, T) => T): T = {
    val xsrvs = reverse(xs)
    def oprvs(x: T, y: T) = op(y, x)
    reduceLeft(xsrvs)(oprvs)
  }                                               //> reduceRight: [T](xs: List[T])(implicit op: (T, T) => T)T

  def foldRight[T, U](xs: List[T])(z: U)(implicit op: (T, U) => U): U = {
    val xsrvs = reverse(xs)
    def oprvs(x: U, y: T) = op(y, x)
    foldLeft(xsrvs)(z)(oprvs)
  }                                               //> foldRight: [T, U](xs: List[T])(z: U)(implicit op: (T, U) => U)U

  def reverse[T](xs: List[T]): List[T] =
    foldLeft(xs)(List[T]())((xs, x) => x :: xs)   //> reverse: [T](xs: List[T])List[T]

  def mapFun[T, U](xs: List[T])(f: T => U) =
    foldRight(xs)(List[U]())(f(_) :: _)           //> mapFun: [T, U](xs: List[T])(f: T => U)List[U]

  def lengthFun[T](xs: List[T]) =
    foldRight(xs)(0)((_, z) => z + 1)             //> lengthFun: [T](xs: List[T])Int

  def sum(xs: List[Int]) = foldLeft(xs)(0)(_ + _) //> sum: (xs: List[Int])Int
  def product(xs: List[Int]) = foldLeft(xs)(1)(_ * _)
                                                  //> product: (xs: List[Int])Int

  val a = List(2, 4, 6, 8)                        //> a  : List[Int] = List(2, 4, 6, 8)
  val b = List(0)                                 //> b  : List[Int] = List(0)
  val c = List(1, 1, 1)                           //> c  : List[Int] = List(1, 1, 1)

  sum(a)                                          //> res0: Int = 20
  sum(b)                                          //> res1: Int = 0
  sum(c)                                          //> res2: Int = 3

  product(a)                                      //> res3: Int = 384
  product(b)                                      //> res4: Int = 0
  product(c)                                      //> res5: Int = 1

  sum(b.tail)                                     //> res6: Int = 0
  product(b.tail)                                 //> res7: Int = 1

  reduceLeft(a)(_ + _)                            //> res8: Int = 20
  reduceLeft(a)(_ * _)                            //> res9: Int = 384

  reduceLeft(a)(_ - _)                            //> res10: Int = -16
  reduceRight(a)(_ - _)                           //> res11: Int = -4
  foldLeft(a)(0)(_ - _)                           //> res12: Int = -20
  foldRight(a)(0)(_ - _)                          //> res13: Int = -4

  reverse(a)                                      //> res14: List[Int] = List(8, 6, 4, 2)
  mapFun(a)(_/2)                                  //> res15: List[Int] = List(1, 2, 3, 4)
  mapFun(List(a, b, c))(lengthFun)                //> res16: List[Int] = List(4, 1, 3)
}