package week6

object queryFor {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(77); 
  println("Welcome to the Scala worksheet");$skip(624); 
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
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")));System.out.println("""books  : scala.collection.immutable.Set[week6.Book] = """ + $show(books ));$skip(75); val res$0 = 
  for (p <- books; a <- p.authors if a startsWith "Odersky") yield p.title;System.out.println("""res0: scala.collection.immutable.Set[String] = """ + $show(res$0));$skip(60); val res$1 = 
  for (p <- books if !(p.title contains "Program")) yield p;System.out.println("""res1: scala.collection.immutable.Set[week6.Book] = """ + $show(res$1));$skip(157); val res$2 = 

 
  for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1;System.out.println("""res2: scala.collection.immutable.Set[String] = """ + $show(res$2));$skip(88); val res$3 = 
    
  books flatMap (b => b.authors.withFilter(_ startsWith "Bird").map(a => b.title));System.out.println("""res3: scala.collection.immutable.Set[String] = """ + $show(res$3))}
}

case class Book(title: String, authors: List[String])
