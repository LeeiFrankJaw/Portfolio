package week2

object exercise {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(185); 
  
  def Op(f: (Int, Int) => Int, e: Int)(g: Int => Int)(a: Int, b: Int): Int = {
  	def loop(a: Int, acc: Int): Int =
  		if (a > b) acc else loop(a+1, f(acc, g(a)))
  	loop(a, e)
  };System.out.println("""Op: (f: (Int, Int) => Int, e: Int)(g: Int => Int)(a: Int, b: Int)Int""");$skip(49); 
  
  def sum = Op((x: Int, y: Int) => x + y, 0)_;System.out.println("""sum: => (Int => Int) => ((Int, Int) => Int)""");$skip(52); 
 
  def product = Op((x: Int, y: Int) => x * y, 1)_;System.out.println("""product: => (Int => Int) => ((Int, Int) => Int)""");$skip(30); 
  
  def identity(x: Int) = x;System.out.println("""identity: (x: Int)Int""");$skip(29); 
  def square(x: Int) = x * x;System.out.println("""square: (x: Int)Int""");$skip(31); 
  def cube(x: Int) = x * x * x;System.out.println("""cube: (x: Int)Int""");$skip(34); 
  
  def sumInts = sum(identity);System.out.println("""sumInts: => (Int, Int) => Int""");$skip(31); 
  def sumSquares = sum(square);System.out.println("""sumSquares: => (Int, Int) => Int""");$skip(27); 
  def sumCubes = sum(cube);System.out.println("""sumCubes: => (Int, Int) => Int""");$skip(41); 
  
  def productInts = product(identity);System.out.println("""productInts: => (Int, Int) => Int""");$skip(39); 
  def productSquares = product(square);System.out.println("""productSquares: => (Int, Int) => Int""");$skip(35); 
  def productCubes = product(cube);System.out.println("""productCubes: => (Int, Int) => Int""");$skip(47); 
  
  def factorial(n: Int) = productInts(1, n);System.out.println("""factorial: (n: Int)Int""");$skip(19); val res$0 = 
  
  sumInts(1, 5);System.out.println("""res0: Int = """ + $show(res$0));$skip(16); val res$1 = 
  sumInts(3, 7);System.out.println("""res1: Int = """ + $show(res$1));$skip(16); val res$2 = 
  sumInts(8, 4);System.out.println("""res2: Int = """ + $show(res$2));$skip(19); val res$3 = 

	sumSquares(3, 4);System.out.println("""res3: Int = """ + $show(res$3));$skip(18); val res$4 = 
	sumSquares(1, 5);System.out.println("""res4: Int = """ + $show(res$4));$skip(19); val res$5 = 
	sumSquares(7, 10);System.out.println("""res5: Int = """ + $show(res$5));$skip(18); val res$6 = 
	
	sumCubes(3, 4);System.out.println("""res6: Int = """ + $show(res$6));$skip(16); val res$7 = 
	sumCubes(1, 5);System.out.println("""res7: Int = """ + $show(res$7));$skip(17); val res$8 = 
	sumCubes(7, 10);System.out.println("""res8: Int = """ + $show(res$8));$skip(21); val res$9 = 
	
	productInts(3, 4);System.out.println("""res9: Int = """ + $show(res$9));$skip(19); val res$10 = 
	productInts(1, 5);System.out.println("""res10: Int = """ + $show(res$10));$skip(20); val res$11 = 
	productInts(7, 10);System.out.println("""res11: Int = """ + $show(res$11));$skip(24); val res$12 = 
	
	productSquares(3, 4);System.out.println("""res12: Int = """ + $show(res$12));$skip(22); val res$13 = 
	productSquares(1, 5);System.out.println("""res13: Int = """ + $show(res$13));$skip(23); val res$14 = 
	productSquares(7, 10);System.out.println("""res14: Int = """ + $show(res$14));$skip(22); val res$15 = 
	
	productCubes(3, 4);System.out.println("""res15: Int = """ + $show(res$15));$skip(20); val res$16 = 
	productCubes(1, 5);System.out.println("""res16: Int = """ + $show(res$16));$skip(17); val res$17 = 
	
	factorial(-2);System.out.println("""res17: Int = """ + $show(res$17));$skip(14); val res$18 = 
	factorial(0);System.out.println("""res18: Int = """ + $show(res$18));$skip(14); val res$19 = 
	factorial(1);System.out.println("""res19: Int = """ + $show(res$19));$skip(14); val res$20 = 
	factorial(2);System.out.println("""res20: Int = """ + $show(res$20));$skip(14); val res$21 = 
	factorial(3);System.out.println("""res21: Int = """ + $show(res$21));$skip(14); val res$22 = 
	factorial(4);System.out.println("""res22: Int = """ + $show(res$22));$skip(14); val res$23 = 
	factorial(5);System.out.println("""res23: Int = """ + $show(res$23));$skip(14); val res$24 = 
	factorial(6);System.out.println("""res24: Int = """ + $show(res$24))}
}
