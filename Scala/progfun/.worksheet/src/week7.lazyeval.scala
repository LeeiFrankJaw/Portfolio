package week7

object lazyeval {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(23); val res$0 = 
  (1 to 1000).toStream;System.out.println("""res0: scala.collection.immutable.Stream[Int] = """ + $show(res$0));$skip(51); 
  val level =
  """ST
  	|oo
  	|oo""".stripMargin;System.out.println("""level  : String = """ + $show(level ));$skip(110); 
  
  def isIn = (c: Char) => c match {case 'T' => true; case 'S' => true; case 'o' => true; case _ => false};System.out.println("""isIn: => Char => Boolean""");$skip(33); val res$1 = 
  "TOCSooo".toCharArray map isIn;System.out.println("""res1: Array[Boolean] = """ + $show(res$1));$skip(26); 
  val v = Vector(1, 2, 3)
  case class Test(x: Int, y: Int);System.out.println("""v  : scala.collection.immutable.Vector[Int] = """ + $show(v ));$skip(55); 
  val a = Test(1, 2);System.out.println("""a  : week7.lazyeval.Test = """ + $show(a ));$skip(21); 
  val b = Test(1, 3);System.out.println("""b  : week7.lazyeval.Test = """ + $show(b ));$skip(9); val res$2 = 
  a == b;System.out.println("""res2: Boolean = """ + $show(res$2));$skip(45); 
  val c = Stream(1, 2, 3) ++ Stream(5, 6, 7);System.out.println("""c  : scala.collection.immutable.Stream[Int] = """ + $show(c ))}
}
