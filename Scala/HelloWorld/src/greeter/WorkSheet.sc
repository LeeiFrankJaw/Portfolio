package greeter

object WorkSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val x = 1                                       //> x  : Int = 1
  def increase(i: Int) = i + 1                    //> increase: (i: Int)Int
  increase(x)                                     //> res0: Int = 2
  
  def and(x: Boolean, y: => Boolean) =
  	if (x) y else false                       //> and: (x: Boolean, y: => Boolean)Boolean
  	
  def or(x: Boolean, y: => Boolean) =
  	if (x) true else y                        //> or: (x: Boolean, y: => Boolean)Boolean
  
  and(false, false)                               //> res1: Boolean = false
  and(false, true)                                //> res2: Boolean = false
  and(true, false)                                //> res3: Boolean = false
  and(true, true)                                 //> res4: Boolean = true
  
  or(false, false)                                //> res5: Boolean = false
  or(false, true)                                 //> res6: Boolean = true
  or(true, false)                                 //> res7: Boolean = true
  or(true, true)                                  //> res8: Boolean = true
  
  def loop: Boolean = loop                        //> loop: => Boolean
  
  and(false, loop)                                //> res9: Boolean = false
  or(true, loop)                                  //> res10: Boolean = true
  
  def fib(n: Int): Int = {
	  assert(n >= 0)
	  def fib_Iter(i: Int, a: Int, b: Int): Int =
	  	if (i == n) a else fib_Iter(i+1, b, a+b)
	  fib_Iter(0, 0, 1)
  }                                               //> fib: (n: Int)Int
  
  fib(0)                                          //> res11: Int = 0
  fib(1)                                          //> res12: Int = 1
  fib(2)                                          //> res13: Int = 1
  fib(3)                                          //> res14: Int = 2
  fib(4)                                          //> res15: Int = 3
  fib(5)                                          //> res16: Int = 5
  fib(6)                                          //> res17: Int = 8
  
  def sqr(x: Double) = x * x                      //> sqr: (x: Double)Double
  
  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double
  
  def sqrt(n: Double): Double = {
  	def sqrt_Iter(x_0: Double): Double =
  		if (abs(sqr(x_0) - n) <= 0.001) x_0 else sqrt_Iter((x_0 + n/x_0) / 2)
  	sqrt_Iter(1)
  }                                               //> sqrt: (n: Double)Double
  
  sqr(sqrt(2))                                    //> res18: Double = 2.0000060073048824
  sqr(sqrt(3))                                    //> res19: Double = 3.000318877551021
}