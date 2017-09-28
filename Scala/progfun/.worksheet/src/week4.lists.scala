package week4

object lists {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(73); 
  println("Welcome to the Scala worksheet");$skip(9); val res$0 = 
  List();System.out.println("""res0: week4.Nil.type = """ + $show(res$0))}
}

abstract class List[+T] {
	def head: T
	def tail: List[T]
	def isEmpty: Boolean
}

object Nil extends List {
	def isEmpty = true
	def head = throw new NoSuchElementException("Nil.head")
	def tail = throw new NoSuchElementException("Nil.tail")
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
	def isEmpty = false
}

object List {
	def apply() = Nil
	def apply[T](x1: T): List[T] = new Cons(x1, Nil)
	def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, Nil))
}
