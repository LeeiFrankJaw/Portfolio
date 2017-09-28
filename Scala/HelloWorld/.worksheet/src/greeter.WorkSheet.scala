package greeter

object WorkSheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(79); 
  println("Welcome to the Scala worksheet");$skip(15); 
  
  val x = 1;System.out.println("""x  : Int = """ + $show(x ));$skip(31); 
  def increase(i: Int) = i + 1;System.out.println("""increase: (i: Int)Int""");$skip(14); val res$0 = 
  increase(x);System.out.println("""res0: Int = """ + $show(res$0));$skip(65); 
  
  def and(x: Boolean, y: => Boolean) =
  	if (x) y else false;System.out.println("""and: (x: Boolean, y: => Boolean)Boolean""");$skip(64); 
  	
  def or(x: Boolean, y: => Boolean) =
  	if (x) true else y;System.out.println("""or: (x: Boolean, y: => Boolean)Boolean""");$skip(23); val res$1 = 
  
  and(false, false);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(19); val res$2 = 
  and(false, true);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(19); val res$3 = 
  and(true, false);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(18); val res$4 = 
  and(true, true);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(22); val res$5 = 
  
  or(false, false);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(18); val res$6 = 
  or(false, true);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(18); val res$7 = 
  or(true, false);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(17); val res$8 = 
  or(true, true);System.out.println("""res8: Boolean = """ + $show(res$8));$skip(30); 
  
  def loop: Boolean = loop;System.out.println("""loop: => Boolean""");$skip(22); val res$9 = 
  
  and(false, loop);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(17); val res$10 = 
  or(true, loop);System.out.println("""res10: Boolean = """ + $show(res$10));$skip(165); 
  
  def fib(n: Int): Int = {
	  assert(n >= 0)
	  def fib_Iter(i: Int, a: Int, b: Int): Int =
	  	if (i == n) a else fib_Iter(i+1, b, a+b)
	  fib_Iter(0, 0, 1)
  };System.out.println("""fib: (n: Int)Int""");$skip(12); val res$11 = 
  
  fib(0);System.out.println("""res11: Int = """ + $show(res$11));$skip(9); val res$12 = 
  fib(1);System.out.println("""res12: Int = """ + $show(res$12));$skip(9); val res$13 = 
  fib(2);System.out.println("""res13: Int = """ + $show(res$13));$skip(9); val res$14 = 
  fib(3);System.out.println("""res14: Int = """ + $show(res$14));$skip(9); val res$15 = 
  fib(4);System.out.println("""res15: Int = """ + $show(res$15));$skip(9); val res$16 = 
  fib(5);System.out.println("""res16: Int = """ + $show(res$16));$skip(9); val res$17 = 
  fib(6);System.out.println("""res17: Int = """ + $show(res$17));$skip(32); 
  
  def sqr(x: Double) = x * x;System.out.println("""sqr: (x: Double)Double""");$skip(47); 
  
  def abs(x: Double) = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(171); 
  
  def sqrt(n: Double): Double = {
  	def sqrt_Iter(x_0: Double): Double =
  		if (abs(sqr(x_0) - n) <= 0.001) x_0 else sqrt_Iter((x_0 + n/x_0) / 2)
  	sqrt_Iter(1)
  };System.out.println("""sqrt: (n: Double)Double""");$skip(18); val res$18 = 
  
  sqr(sqrt(2));System.out.println("""res18: Double = """ + $show(res$18));$skip(15); val res$19 = 
  sqr(sqrt(3));System.out.println("""res19: Double = """ + $show(res$19))}
}
