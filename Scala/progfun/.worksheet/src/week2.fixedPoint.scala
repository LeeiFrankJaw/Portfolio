package week2

object fixedPoint {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(78); 
  println("Welcome to the Scala worksheet");$skip(55); 
  
  def abs(x: Double): Double = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(28); 
  
  val tolerance = 0.0001;System.out.println("""tolerance  : Double = """ + $show(tolerance ));$skip(90); 
  
  def isGoodEnough(x: Double, y: Double): Boolean =
  	abs((x-y) / x) / x < tolerance;System.out.println("""isGoodEnough: (x: Double, y: Double)Boolean""");$skip(239); 
  
  def fixedPoint(f: Double => Double)(guess: Double) = {
  	def loop(guess: Double): Double= {
  		println("guess = " + guess)
  		val next = f(guess)
  		if (isGoodEnough(guess, next)) guess
  		else loop(next)
  	}
  	loop(guess)
  };System.out.println("""fixedPoint: (f: Double => Double)(guess: Double)Double""");$skip(33); val res$0 = 
  
  fixedPoint(x => 1 + x/2)(1);System.out.println("""res0: Double = """ + $show(res$0));$skip(70); 
  
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2;System.out.println("""averageDamp: (f: Double => Double)(x: Double)Double""");$skip(64); 
  
  def sqrt(x: Double) = fixedPoint(averageDamp(y => x/y))(1);System.out.println("""sqrt: (x: Double)Double""");$skip(13); val res$1 = 
  
  sqrt(2);System.out.println("""res1: Double = """ + $show(res$1))}
}
