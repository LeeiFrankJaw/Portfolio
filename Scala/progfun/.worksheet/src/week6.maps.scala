package week6

object maps {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(73); 
  println("Welcome to the Scala worksheet");$skip(77); 

  val romanNum = Map("I" -> 1, "V" -> 5, "X" -> 10, "L" -> 50, "C" -> 100);System.out.println("""romanNum  : scala.collection.immutable.Map[String,Int] = """ + $show(romanNum ));$skip(141); 
  val capitalCountry = Map("United States" -> "Washington D.C.",
    "China" -> "Beijing", "Japan" -> "Tokyo", "United Kingdom" -> "London");System.out.println("""capitalCountry  : scala.collection.immutable.Map[String,String] = """ + $show(capitalCountry ));$skip(16); val res$0 = 
  romanNum("C");System.out.println("""res0: Int = """ + $show(res$0));$skip(35); val res$1 = 
  capitalCountry("United Kingdom");System.out.println("""res1: String = """ + $show(res$1));$skip(29); val res$2 = 
  capitalCountry get "China";System.out.println("""res2: Option[String] = """ + $show(res$2));$skip(35); val res$3 = 
  capitalCountry get "Switzerland";System.out.println("""res3: Option[String] = """ + $show(res$3));$skip(61); 

  val fruit = List("apple", "pear", "orange", "pineapple");System.out.println("""fruit  : List[String] = """ + $show(fruit ));$skip(41); val res$4 = 
  
  fruit sortWith(_.length < _.length);System.out.println("""res4: List[String] = """ + $show(res$4));$skip(15); val res$5 = 
  fruit sorted;System.out.println("""res5: List[String] = """ + $show(res$5));$skip(28); val res$6 = 
  
  fruit groupBy (_.head);System.out.println("""res6: scala.collection.immutable.Map[Char,List[String]] = """ + $show(res$6));$skip(63); 
  
  val cap1 = capitalCountry withDefaultValue "Missing Data";System.out.println("""cap1  : scala.collection.immutable.Map[String,String] = """ + $show(cap1 ));$skip(18); val res$7 = 
  cap1("Germany");System.out.println("""res7: String = """ + $show(res$7));$skip(16); val res$8 = 
	romanNum - "C";System.out.println("""res8: scala.collection.immutable.Map[String,Int] = """ + $show(res$8))}
}
