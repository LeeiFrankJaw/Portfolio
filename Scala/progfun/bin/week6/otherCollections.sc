package week6

object otherCollections {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val xs = Array(1, 2, 3, 44)                     //> xs  : Array[Int] = Array(1, 2, 3, 44)

  xs map (x => x * x)                             //> res0: Array[Int] = Array(1, 4, 9, 1936)

  val s = "Helle World!"                          //> s  : String = Helle World!

  s filter (c => c.isUpper)                       //> res1: String = HW

  s exists (c => c.isUpper)                       //> res2: Boolean = true
  s forall (c => c.isUpper)                       //> res3: Boolean = false

  val zp = xs.toList zip s                        //> zp  : List[(Int, Char)] = List((1,H), (2,e), (3,l), (44,l))
  zp.unzip                                        //> res4: (List[Int], List[Char]) = (List(1, 2, 3, 44),List(H, e, l, l))

  s flatMap (c => List('.', c))                   //> res5: String = .H.e.l.l.e. .W.o.r.l.d.!

  xs.sum                                          //> res6: Int = 50
  xs.product                                      //> res7: Int = 264
  xs.max                                          //> res8: Int = 44
  xs.min                                          //> res9: Int = 1

  1 to 2 flatMap (x => 1 to 3 map (y => (x, y)))  //> res10: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,
                                                  //| 2), (1,3), (2,1), (2,2), (2,3))
  def ScalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    xs zip ys map { case (x, y) => x * y } sum    //> ScalarProduct: (xs: Vector[Double], ys: Vector[Double])Double

  val x = Vector(1., 2., 3.)                      //> x  : scala.collection.immutable.Vector[Double] = Vector(1.0, 2.0, 3.0)
  val y = Vector(.5, 2., .333)                    //> y  : scala.collection.immutable.Vector[Double] = Vector(0.5, 2.0, 0.333)

  ScalarProduct(x, y)                             //> res11: Double = 5.4990000000000006

  def isPrime(n: Int): Boolean = !(2 to Math.sqrt(n).toInt exists (x => n % x == 0)) && n > 1
                                                  //> isPrime: (n: Int)Boolean
  1 to 20 map isPrime                             //> res12: scala.collection.immutable.IndexedSeq[Boolean] = Vector(false, true, 
                                                  //| true, false, true, false, true, false, false, false, true, false, true, fals
                                                  //| e, false, false, true, false, true, false)

  val pairs = (1 until 10) flatMap (i => (1 until i) map (j => (i, j)))
                                                  //> pairs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (
                                                  //| 3,1), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), 
                                                  //| (6,3), (6,4), (6,5), (7,1), (7,2), (7,3), (7,4), (7,5), (7,6), (8,1), (8,2),
                                                  //|  (8,3), (8,4), (8,5), (8,6), (8,7), (9,1), (9,2), (9,3), (9,4), (9,5), (9,6)
                                                  //| , (9,7), (9,8))

  pairs filter { case (x, y) => isPrime(x + y) }  //> res13: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5), (7,4), (7,6), (8,3), (8,5), (9,2), (9
                                                  //| ,4), (9,8))
  for (p <- pairs if isPrime(p._1 + p._2)) yield p//> res14: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5), (7,4), (7,6), (8,3), (8,5), (9,2), (9
                                                  //| ,4), (9,8))

  for {
    i <- 1 until 10
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j)                                  //> res15: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3
                                                  //| ,2), (4,1), (4,3), (5,2), (6,1), (6,5), (7,4), (7,6), (8,3), (8,5), (9,2), 
                                                  //| (9,4), (9,8))
  def ScalarTimes(xs: Vector[Double], ys: Vector[Double]): Double =
    (for {
      (x, y) <- xs zip ys
    } yield x * y) sum                            //> ScalarTimes: (xs: Vector[Double], ys: Vector[Double])Double

  ScalarTimes(x, y)                               //> res16: Double = 5.4990000000000006
}