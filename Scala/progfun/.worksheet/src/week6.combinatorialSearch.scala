package week6

object combinatorialSearch {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(88); 
  println("Welcome to the Scala worksheet");$skip(48); 

  val fruits = Set("apple", "banana", "pear");System.out.println("""fruits  : scala.collection.immutable.Set[String] = """ + $show(fruits ));$skip(27); 

  val s = (1 to 6).toSet;System.out.println("""s  : scala.collection.immutable.Set[Int] = """ + $show(s ));$skip(39); val res$0 = 

  fruits filter (_.startsWith("ba"));System.out.println("""res0: scala.collection.immutable.Set[String] = """ + $show(res$0));$skip(13); val res$1 = 
  s.nonEmpty;System.out.println("""res1: Boolean = """ + $show(res$1));$skip(18); val res$2 = 

  s map (_ / 2);System.out.println("""res2: scala.collection.immutable.Set[Int] = """ + $show(res$2));$skip(15); val res$3 = 
  s contains 5;System.out.println("""res3: Boolean = """ + $show(res$3));$skip(591); 

  def nqueen(n: Int): Set[List[Int]] = {
  	def isSafe(col: Int, queens: List[Int]): Boolean = {
  		def safeAcc(col: Int, queens: List[Int])(acc: Int): Boolean = queens match {
  			case Nil => true
  			case x::xs =>
  				if (col==x || Math.abs(x-col) == acc) false
  				else safeAcc(col, xs)(acc+1)
  		}
  		safeAcc(col, queens)(1)
  	}
    def placeQueen(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else
      	for {
      		queens <- placeQueen(k-1)
      		col <- 0 until n
      		if isSafe(col, queens)
      	} yield col::queens
    }
    placeQueen(n)
  };System.out.println("""nqueen: (n: Int)Set[List[Int]]""");$skip(20); val res$4 = 
  
  nqueen(8).size;System.out.println("""res4: Int = """ + $show(res$4))}
}
