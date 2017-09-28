package week2

object Rationals {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val x = new Rational(1, 3)                      //> x  : week2.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week2.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week2.Rational = 3/2
  x.add(y)                                        //> res0: week2.Rational = 22/21
  x.neg                                           //> res1: week2.Rational = 1/-3
  x.sub(y)                                        //> res2: week2.Rational = 8/-21
  x.sub(y).sub(z)                                 //> res3: week2.Rational = -79/42
  y.add(y)                                        //> res4: week2.Rational = 10/7
  y.less(y)                                       //> res5: Boolean = false
  x.less(z)                                       //> res6: Boolean = true
  x.max(y).max(z)                                 //> res7: week2.Rational = 3/2
  //new Rational(1, 0)
  new Rational(5)                                 //> res8: week2.Rational = 5/1
}

class Rational(x: Int, y: Int) {
	require(y != 0, "Denominator must be nonzero")
	
	def this(x: Int) = this(x, 1)
	
	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
	private val g = gcd(x, y)

	val numer = x / g
	val denom = y / g
	
	def less(that: Rational) = numer * that.denom < that.numer * denom
	def max(that: Rational) = if (less(that)) that else this
	
	override def toString = numer + "/" + denom
	def neg = new Rational(-numer, denom)
	
	def add(that: Rational) =
		new Rational(
		numer * that.denom + that.numer * denom,
		denom * that.denom)
		
	def sub(that: Rational) = add(that.neg)
}