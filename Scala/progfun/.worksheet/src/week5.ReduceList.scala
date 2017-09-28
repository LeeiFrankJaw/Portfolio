package week5

object ReduceList {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(79); 
  println("Welcome to the Scala worksheet");$skip(175); 

  def reduceLeft[T](xs: List[T])(implicit op: (T, T) => T): T = xs match {
    case Nil => throw new Error("Reduce an empty list")
    case x :: xs1 => foldLeft(xs1)(x)
  };System.out.println("""reduceLeft: [T](xs: List[T])(implicit op: (T, T) => T)T""");$skip(151); 

  def foldLeft[T, U](xs: List[T])(z: U)(implicit op: (U, T) => U): U = xs match {
    case Nil => z
    case x :: xs1 => foldLeft(xs1)(op(z, x))
  };System.out.println("""foldLeft: [T, U](xs: List[T])(z: U)(implicit op: (U, T) => U)U""");$skip(167); 

  def reduceRight[T](xs: List[T])(implicit op: (T, T) => T): T = {
    val xsrvs = reverse(xs)
    def oprvs(x: T, y: T) = op(y, x)
    reduceLeft(xsrvs)(oprvs)
  };System.out.println("""reduceRight: [T](xs: List[T])(implicit op: (T, T) => T)T""");$skip(175); 

  def foldRight[T, U](xs: List[T])(z: U)(implicit op: (T, U) => U): U = {
    val xsrvs = reverse(xs)
    def oprvs(x: U, y: T) = op(y, x)
    foldLeft(xsrvs)(z)(oprvs)
  };System.out.println("""foldRight: [T, U](xs: List[T])(z: U)(implicit op: (T, U) => U)U""");$skip(91); 

  def reverse[T](xs: List[T]): List[T] =
    foldLeft(xs)(List[T]())((xs, x) => x :: xs);System.out.println("""reverse: [T](xs: List[T])List[T]""");$skip(87); 

  def mapFun[T, U](xs: List[T])(f: T => U) =
    foldRight(xs)(List[U]())(f(_) :: _);System.out.println("""mapFun: [T, U](xs: List[T])(f: T => U)List[U]""");$skip(74); 

  def lengthFun[T](xs: List[T]) =
    foldRight(xs)(0)((_, z) => z + 1);System.out.println("""lengthFun: [T](xs: List[T])Int""");$skip(52); 

  def sum(xs: List[Int]) = foldLeft(xs)(0)(_ + _);System.out.println("""sum: (xs: List[Int])Int""");$skip(54); 
  def product(xs: List[Int]) = foldLeft(xs)(1)(_ * _);System.out.println("""product: (xs: List[Int])Int""");$skip(29); 

  val a = List(2, 4, 6, 8);System.out.println("""a  : List[Int] = """ + $show(a ));$skip(18); 
  val b = List(0);System.out.println("""b  : List[Int] = """ + $show(b ));$skip(24); 
  val c = List(1, 1, 1);System.out.println("""c  : List[Int] = """ + $show(c ));$skip(11); val res$0 = 

  sum(a);System.out.println("""res0: Int = """ + $show(res$0));$skip(9); val res$1 = 
  sum(b);System.out.println("""res1: Int = """ + $show(res$1));$skip(9); val res$2 = 
  sum(c);System.out.println("""res2: Int = """ + $show(res$2));$skip(15); val res$3 = 

  product(a);System.out.println("""res3: Int = """ + $show(res$3));$skip(13); val res$4 = 
  product(b);System.out.println("""res4: Int = """ + $show(res$4));$skip(13); val res$5 = 
  product(c);System.out.println("""res5: Int = """ + $show(res$5));$skip(16); val res$6 = 

  sum(b.tail);System.out.println("""res6: Int = """ + $show(res$6));$skip(18); val res$7 = 
  product(b.tail);System.out.println("""res7: Int = """ + $show(res$7));$skip(25); val res$8 = 

  reduceLeft(a)(_ + _);System.out.println("""res8: Int = """ + $show(res$8));$skip(23); val res$9 = 
  reduceLeft(a)(_ * _);System.out.println("""res9: Int = """ + $show(res$9));$skip(25); val res$10 = 

  reduceLeft(a)(_ - _);System.out.println("""res10: Int = """ + $show(res$10));$skip(24); val res$11 = 
  reduceRight(a)(_ - _);System.out.println("""res11: Int = """ + $show(res$11));$skip(24); val res$12 = 
  foldLeft(a)(0)(_ - _);System.out.println("""res12: Int = """ + $show(res$12));$skip(25); val res$13 = 
  foldRight(a)(0)(_ - _);System.out.println("""res13: Int = """ + $show(res$13));$skip(15); val res$14 = 

  reverse(a);System.out.println("""res14: List[Int] = """ + $show(res$14));$skip(17); val res$15 = 
  mapFun(a)(_/2);System.out.println("""res15: List[Int] = """ + $show(res$15));$skip(35); val res$16 = 
  mapFun(List(a, b, c))(lengthFun);System.out.println("""res16: List[Int] = """ + $show(res$16))}
}
