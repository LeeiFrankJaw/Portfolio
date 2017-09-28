package week6

object combinatorialSearch {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val fruits = Set("apple", "banana", "pear")     //> fruits  : scala.collection.immutable.Set[String] = Set(apple, banana, pear)
                                                  //| 

  val s = (1 to 6).toSet                          //> s  : scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 3, 4)

  fruits filter (_.startsWith("ba"))              //> res0: scala.collection.immutable.Set[String] = Set(banana)
  s.nonEmpty                                      //> res1: Boolean = true

  s map (_ / 2)                                   //> res2: scala.collection.immutable.Set[Int] = Set(2, 0, 3, 1)
  s contains 5                                    //> res3: Boolean = true

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
  }                                               //> nqueen: (n: Int)Set[List[Int]]
  
  nqueen(8).size                                  //> res4: Int = 92
}