package week6

object otherCollections {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
  println("Welcome to the Scala worksheet");$skip(32); 

  val xs = Array(1, 2, 3, 44);System.out.println("""xs  : Array[Int] = """ + $show(xs ));$skip(24); val res$0 = 

  xs map (x => x * x);System.out.println("""res0: Array[Int] = """ + $show(res$0));$skip(27); 

  val s = "Helle World!";System.out.println("""s  : String = """ + $show(s ));$skip(30); val res$1 = 

  s filter (c => c.isUpper);System.out.println("""res1: String = """ + $show(res$1));$skip(30); val res$2 = 

  s exists (c => c.isUpper);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(28); val res$3 = 
  s forall (c => c.isUpper);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(29); 

  val zp = xs.toList zip s;System.out.println("""zp  : List[(Int, Char)] = """ + $show(zp ));$skip(11); val res$4 = 
  zp.unzip;System.out.println("""res4: (List[Int], List[Char]) = """ + $show(res$4));$skip(34); val res$5 = 

  s flatMap (c => List('.', c));System.out.println("""res5: String = """ + $show(res$5));$skip(11); val res$6 = 

  xs.sum;System.out.println("""res6: Int = """ + $show(res$6));$skip(13); val res$7 = 
  xs.product;System.out.println("""res7: Int = """ + $show(res$7));$skip(9); val res$8 = 
  xs.max;System.out.println("""res8: Int = """ + $show(res$8));$skip(9); val res$9 = 
  xs.min;System.out.println("""res9: Int = """ + $show(res$9));$skip(51); val res$10 = 

  1 to 2 flatMap (x => 1 to 3 map (y => (x, y)));System.out.println("""res10: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$10));$skip(117); 
  def ScalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    xs zip ys map { case (x, y) => x * y } sum;System.out.println("""ScalarProduct: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(31); 

  val x = Vector(1., 2., 3.);System.out.println("""x  : scala.collection.immutable.Vector[Double] = """ + $show(x ));$skip(31); 
  val y = Vector(.5, 2., .333);System.out.println("""y  : scala.collection.immutable.Vector[Double] = """ + $show(y ));$skip(24); val res$11 = 

  ScalarProduct(x, y);System.out.println("""res11: Double = """ + $show(res$11));$skip(96); 

  def isPrime(n: Int): Boolean = !(2 to Math.sqrt(n).toInt exists (x => n % x == 0)) && n > 1;System.out.println("""isPrime: (n: Int)Boolean""");$skip(22); val res$12 = 
  1 to 20 map isPrime;System.out.println("""res12: scala.collection.immutable.IndexedSeq[Boolean] = """ + $show(res$12));$skip(74); 

  val pairs = (1 until 10) flatMap (i => (1 until i) map (j => (i, j)));System.out.println("""pairs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(pairs ));$skip(51); val res$13 = 

  pairs filter { case (x, y) => isPrime(x + y) };System.out.println("""res13: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$13));$skip(51); val res$14 = 
  for (p <- pairs if isPrime(p._1 + p._2)) yield p;System.out.println("""res14: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$14));$skip(88); val res$15 = 

  for {
    i <- 1 until 10
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j);System.out.println("""res15: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$15));$skip(128); 
  def ScalarTimes(xs: Vector[Double], ys: Vector[Double]): Double =
    (for {
      (x, y) <- xs zip ys
    } yield x * y) sum;System.out.println("""ScalarTimes: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(22); val res$16 = 

  ScalarTimes(x, y);System.out.println("""res16: Double = """ + $show(res$16))}
}
