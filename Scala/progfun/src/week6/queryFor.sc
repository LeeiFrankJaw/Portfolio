package week6

object queryFor {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val books = Set(
    Book(title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),
    Book(title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java",
      authors = List("Bloch, Joshua")),
    Book(title = "Effective Java 2",
      authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")),
    Book(title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")))
                                                  //> books  : scala.collection.immutable.Set[week6.Book] = Set(Book(Effective Jav
                                                  //| a 2,List(Bloch, Joshua)), Book(Programming in Scala,List(Odersky, Martin, Sp
                                                  //| oon, Lex, Venners, Bill)), Book(Structure and Interpretation of Computer Pro
                                                  //| grams,List(Abelson, Harald, Sussman, Gerald J.)), Book(Effective Java,List(B
                                                  //| loch, Joshua)), Book(Introduction to Functional Programming,List(Bird, Richa
                                                  //| rd, Wadler, Phil)), Book(Java Puzzlers,List(Bloch, Joshua, Gafter, Neal)))
  for (p <- books; a <- p.authors if a startsWith "Odersky") yield p.title
                                                  //> res0: scala.collection.immutable.Set[String] = Set(Programming in Scala)
  for (p <- books if !(p.title contains "Program")) yield p
                                                  //> res1: scala.collection.immutable.Set[week6.Book] = Set(Book(Effective Java 2
                                                  //| ,List(Bloch, Joshua)), Book(Effective Java,List(Bloch, Joshua)), Book(Java P
                                                  //| uzzlers,List(Bloch, Joshua, Gafter, Neal)))

 
  for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1                                    //> res2: scala.collection.immutable.Set[String] = Set(Bloch, Joshua)
    
  books flatMap (b => b.authors.withFilter(_ startsWith "Bird").map(a => b.title))
                                                  //> res3: scala.collection.immutable.Set[String] = Set(Introduction to Function
                                                  //| al Programming)
}

case class Book(title: String, authors: List[String])