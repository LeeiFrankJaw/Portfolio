package week3

object nth {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(72); 
  println("Welcome to the Scala worksheet");$skip(151); 

  def Nth[T](n: Int, a: List[T]): T =
    if (a.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) a.head
    else Nth(n - 1, a.tail);System.out.println("""Nth: [T](n: Int, a: List[T])T""");$skip(27); 

  val a1 = List(1, 2, 3);System.out.println("""a1  : List[Int] = """ + $show(a1 ));$skip(14); val res$0 = 
  Nth(-1, a1);System.out.println("""res0: Int = """ + $show(res$0))}
}
