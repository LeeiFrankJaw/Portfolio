package week7

object lazyeval {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  (1 to 1000).toStream                            //> res0: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  val level =
  """ST
  	|oo
  	|oo""".stripMargin                        //> level  : String = ST
                                                  //| oo
                                                  //| oo
  
  def isIn = (c: Char) => c match {case 'T' => true; case 'S' => true; case 'o' => true; case _ => false}
                                                  //> isIn: => Char => Boolean
  "TOCSooo".toCharArray map isIn                  //> res1: Array[Boolean] = Array(true, false, false, true, true, true, true)
  val v = Vector(1, 2, 3)                         //> v  : scala.collection.immutable.Vector[Int] = Vector(1, 2, 3)
  case class Test(x: Int, y: Int)
  val a = Test(1, 2)                              //> a  : week7.lazyeval.Test = Test(1,2)
  val b = Test(1, 3)                              //> b  : week7.lazyeval.Test = Test(1,3)
  a == b                                          //> res2: Boolean = false
  val c = Stream(1, 2, 3) ++ Stream(5, 6, 7)      //> c  : scala.collection.immutable.Stream[Int] = Stream(1, ?)
}