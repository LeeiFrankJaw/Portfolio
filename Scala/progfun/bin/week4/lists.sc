package week4

object lists {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  List()                                          //> res0: week4.Nil.type = week4.Nil$@1ea34f3
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