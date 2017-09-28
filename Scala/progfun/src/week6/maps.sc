package week6

object maps {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val romanNum = Map("I" -> 1, "V" -> 5, "X" -> 10, "L" -> 50, "C" -> 100)
                                                  //> romanNum  : scala.collection.immutable.Map[String,Int] = Map(X -> 10, I -> 1
                                                  //| , V -> 5, L -> 50, C -> 100)
  val capitalCountry = Map("United States" -> "Washington D.C.",
    "China" -> "Beijing", "Japan" -> "Tokyo", "United Kingdom" -> "London")
                                                  //> capitalCountry  : scala.collection.immutable.Map[String,String] = Map(United
                                                  //|  States -> Washington D.C., China -> Beijing, Japan -> Tokyo, United Kingdom
                                                  //|  -> London)
  romanNum("C")                                   //> res0: Int = 100
  capitalCountry("United Kingdom")                //> res1: String = London
  capitalCountry get "China"                      //> res2: Option[String] = Some(Beijing)
  capitalCountry get "Switzerland"                //> res3: Option[String] = None

  val fruit = List("apple", "pear", "orange", "pineapple")
                                                  //> fruit  : List[String] = List(apple, pear, orange, pineapple)
  
  fruit sortWith(_.length < _.length)             //> res4: List[String] = List(pear, apple, orange, pineapple)
  fruit sorted                                    //> res5: List[String] = List(apple, orange, pear, pineapple)
  
  fruit groupBy (_.head)                          //> res6: scala.collection.immutable.Map[Char,List[String]] = Map(p -> List(pear
                                                  //| , pineapple), a -> List(apple), o -> List(orange))
  
  val cap1 = capitalCountry withDefaultValue "Missing Data"
                                                  //> cap1  : scala.collection.immutable.Map[String,String] = Map(United States ->
                                                  //|  Washington D.C., China -> Beijing, Japan -> Tokyo, United Kingdom -> London
                                                  //| )
  cap1("Germany")                                 //> res7: String = Missing Data
	romanNum - "C"                            //> res8: scala.collection.immutable.Map[String,Int] = Map(X -> 10, I -> 1, V ->
                                                  //|  5, L -> 50)
}