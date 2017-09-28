package week3

object nth {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def Nth[T](n: Int, a: List[T]): T =
    if (a.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) a.head
    else Nth(n - 1, a.tail)                       //> Nth: [T](n: Int, a: List[T])T

  val a1 = List(1, 2, 3)                          //> a1  : List[Int] = List(1, 2, 3)
  Nth(-1, a1)                                     //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week3.nth$$anonfun$main$1.Nth$1(week3.nth.scala:7)
                                                  //| 	at week3.nth$$anonfun$main$1.apply$mcV$sp(week3.nth.scala:12)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.nth$.main(week3.nth.scala:3)
                                                  //| 	at week3.nth.main(week3.nth.scala)
}