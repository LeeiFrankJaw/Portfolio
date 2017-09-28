package week2

object Rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(77); 
  println("Welcome to the Scala worksheet");$skip(29); 
  val x = new Rational(1, 3);System.out.println("""x  : week2.Rational = """ + $show(x ));$skip(29); 
  val y = new Rational(5, 7);System.out.println("""y  : week2.Rational = """ + $show(y ));$skip(29); 
  val z = new Rational(3, 2);System.out.println("""z  : week2.Rational = """ + $show(z ));$skip(11); val res$0 = 
  x.add(y);System.out.println("""res0: week2.Rational = """ + $show(res$0));$skip(8); val res$1 = 
  x.neg;System.out.println("""res1: week2.Rational = """ + $show(res$1));$skip(11); val res$2 = 
  x.sub(y);System.out.println("""res2: week2.Rational = """ + $show(res$2));$skip(18); val res$3 = 
  x.sub(y).sub(z);System.out.println("""res3: week2.Rational = """ + $show(res$3));$skip(11); val res$4 = 
  y.add(y);System.out.println("""res4: week2.Rational = """ + $show(res$4));$skip(12); val res$5 = 
  y.less(y);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(12); val res$6 = 
  x.less(z);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(18); val res$7 = 
  x.max(y).max(z);System.out.println("""res7: week2.Rational = """ + $show(res$7));$skip(41); val res$8 = 
  //new Rational(1, 0)
  new Rational(5);System.out.println("""res8: week2.Rational = """ + $show(res$8))}
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
