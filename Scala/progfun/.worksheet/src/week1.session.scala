package week1

object session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(31); 

  def sqr(x: Double) = x * x;System.out.println("""sqr: (x: Double)Double""");$skip(46); 

  def abs(x: Double) = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(261); 

  def sqrt(b: Double) = {
    def isGoodEnough(x: Double) =
      abs(sqr(x) - b) <= b / 1e8

    def improve(x: Double) = (x + b / x) / 2

    def sqrtIter(x: Double): Double =
      if (isGoodEnough(x)) x else sqrtIter(improve(x))

    sqrtIter(1.0)
  };System.out.println("""sqrt: (b: Double)Double""");$skip(17); val res$0 = 

  sqr(sqrt(4));System.out.println("""res0: Double = """ + $show(res$0));$skip(15); val res$1 = 
  sqr(sqrt(2));System.out.println("""res1: Double = """ + $show(res$1));$skip(15); val res$2 = 
  sqr(sqrt(3));System.out.println("""res2: Double = """ + $show(res$2));$skip(18); val res$3 = 
  sqr(sqrt(1e-6));System.out.println("""res3: Double = """ + $show(res$3));$skip(18); val res$4 = 
  sqr(sqrt(1e60));System.out.println("""res4: Double = """ + $show(res$4));$skip(19); val res$5 = 
  sqr(sqrt(0.001));System.out.println("""res5: Double = """ + $show(res$5));$skip(21); val res$6 = 
  sqr(sqrt(0.1e-20));System.out.println("""res6: Double = """ + $show(res$6));$skip(20); val res$7 = 
  sqr(sqrt(1.0e20));System.out.println("""res7: Double = """ + $show(res$7));$skip(20); val res$8 = 
  sqr(sqrt(1.0e50));System.out.println("""res8: Double = """ + $show(res$8));$skip(71); 

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b);System.out.println("""gcd: (a: Int, b: Int)Int""");$skip(17); val res$9 = 
   
  gcd(5, 4);System.out.println("""res9: Int = """ + $show(res$9));$skip(14); val res$10 = 
  gcd(12, 60);System.out.println("""res10: Int = """ + $show(res$10));$skip(139); 
  
  def factorial(n: Int): Int = {
  	def factIter(n: Int, p: Int): Int =
  		if (n == 0) p else factIter(n-1, p*n)
  	factIter(n, 1)
  };System.out.println("""factorial: (n: Int)Int""");$skip(18); val res$11 = 
  
  factorial(0);System.out.println("""res11: Int = """ + $show(res$11));$skip(15); val res$12 = 
  factorial(1);System.out.println("""res12: Int = """ + $show(res$12));$skip(15); val res$13 = 
  factorial(2);System.out.println("""res13: Int = """ + $show(res$13));$skip(15); val res$14 = 
  factorial(3);System.out.println("""res14: Int = """ + $show(res$14));$skip(15); val res$15 = 
  factorial(4);System.out.println("""res15: Int = """ + $show(res$15));$skip(15); val res$16 = 
  factorial(5);System.out.println("""res16: Int = """ + $show(res$16))}
}
