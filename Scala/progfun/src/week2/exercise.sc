package week2

object exercise {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def Op(f: (Int, Int) => Int, e: Int)(g: Int => Int)(a: Int, b: Int): Int = {
  	def loop(a: Int, acc: Int): Int =
  		if (a > b) acc else loop(a+1, f(acc, g(a)))
  	loop(a, e)
  }                                               //> Op: (f: (Int, Int) => Int, e: Int)(g: Int => Int)(a: Int, b: Int)Int
  
  def sum = Op((x: Int, y: Int) => x + y, 0)_     //> sum: => (Int => Int) => ((Int, Int) => Int)
 
  def product = Op((x: Int, y: Int) => x * y, 1)_ //> product: => (Int => Int) => ((Int, Int) => Int)
  
  def identity(x: Int) = x                        //> identity: (x: Int)Int
  def square(x: Int) = x * x                      //> square: (x: Int)Int
  def cube(x: Int) = x * x * x                    //> cube: (x: Int)Int
  
  def sumInts = sum(identity)                     //> sumInts: => (Int, Int) => Int
  def sumSquares = sum(square)                    //> sumSquares: => (Int, Int) => Int
  def sumCubes = sum(cube)                        //> sumCubes: => (Int, Int) => Int
  
  def productInts = product(identity)             //> productInts: => (Int, Int) => Int
  def productSquares = product(square)            //> productSquares: => (Int, Int) => Int
  def productCubes = product(cube)                //> productCubes: => (Int, Int) => Int
  
  def factorial(n: Int) = productInts(1, n)       //> factorial: (n: Int)Int
  
  sumInts(1, 5)                                   //> res0: Int = 15
  sumInts(3, 7)                                   //> res1: Int = 25
  sumInts(8, 4)                                   //> res2: Int = 0

	sumSquares(3, 4)                          //> res3: Int = 25
	sumSquares(1, 5)                          //> res4: Int = 55
	sumSquares(7, 10)                         //> res5: Int = 294
	
	sumCubes(3, 4)                            //> res6: Int = 91
	sumCubes(1, 5)                            //> res7: Int = 225
	sumCubes(7, 10)                           //> res8: Int = 2584
	
	productInts(3, 4)                         //> res9: Int = 12
	productInts(1, 5)                         //> res10: Int = 120
	productInts(7, 10)                        //> res11: Int = 5040
	
	productSquares(3, 4)                      //> res12: Int = 144
	productSquares(1, 5)                      //> res13: Int = 14400
	productSquares(7, 10)                     //> res14: Int = 25401600
	
	productCubes(3, 4)                        //> res15: Int = 1728
	productCubes(1, 5)                        //> res16: Int = 1728000
	
	factorial(-2)                             //> res17: Int = 1
	factorial(0)                              //> res18: Int = 1
	factorial(1)                              //> res19: Int = 1
	factorial(2)                              //> res20: Int = 2
	factorial(3)                              //> res21: Int = 6
	factorial(4)                              //> res22: Int = 24
	factorial(5)                              //> res23: Int = 120
	factorial(6)                              //> res24: Int = 720
}