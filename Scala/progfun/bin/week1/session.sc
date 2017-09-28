package week1

object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def sqr(x: Double) = x * x                      //> sqr: (x: Double)Double

  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double

  def sqrt(b: Double) = {
    def isGoodEnough(x: Double) =
      abs(sqr(x) - b) <= b / 1e8

    def improve(x: Double) = (x + b / x) / 2

    def sqrtIter(x: Double): Double =
      if (isGoodEnough(x)) x else sqrtIter(improve(x))

    sqrtIter(1.0)
  }                                               //> sqrt: (b: Double)Double

  sqr(sqrt(4))                                    //> res0: Double = 4.000000000000009
  sqr(sqrt(2))                                    //> res1: Double = 2.0000000000045106
  sqr(sqrt(3))                                    //> res2: Double = 3.0000000084726732
  sqr(sqrt(1e-6))                                 //> res3: Double = 1.0000000000000235E-6
  sqr(sqrt(1e60))                                 //> res4: Double = 1.0000000062161491E60
  sqr(sqrt(0.001))                                //> res5: Double = 0.001000000000000034
  sqr(sqrt(0.1e-20))                              //> res6: Double = 1.000000000000003E-21
  sqr(sqrt(1.0e20))                               //> res7: Double = 1.0000000000046159E20
  sqr(sqrt(1.0e50))                               //> res8: Double = 1.0000000000001448E50

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)              //> gcd: (a: Int, b: Int)Int
   
  gcd(5, 4)                                       //> res9: Int = 1
  gcd(12, 60)                                     //> res10: Int = 12
  
  def factorial(n: Int): Int = {
  	def factIter(n: Int, p: Int): Int =
  		if (n == 0) p else factIter(n-1, p*n)
  	factIter(n, 1)
  }                                               //> factorial: (n: Int)Int
  
  factorial(0)                                    //> res11: Int = 1
  factorial(1)                                    //> res12: Int = 1
  factorial(2)                                    //> res13: Int = 2
  factorial(3)                                    //> res14: Int = 6
  factorial(4)                                    //> res15: Int = 24
  factorial(5)                                    //> res16: Int = 120
}