package week5

import math.Ordering

object MergeSort extends App {
  def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T], acc: List[T]): List[T] =
        (xs, ys) match {
          case (Nil, _) => acc.reverse ++ ys
          case (_, Nil) => acc.reverse ++ xs
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x, y)) merge(xs1, ys, x :: acc)
            else merge(xs, ys1, y :: acc)
        }
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd), Nil)
    }
  }

  /*  val a = List(3,1,0,7,5)
  
  print(msort(a))*/

  import scala.io.Source

  val buf = collection.mutable.ListBuffer.empty[Int]

  for (line <- Source.fromFile("C:/Users/Lei/Documents/Courses/ADA1/Week 1/IntegerArray.txt").getLines()) {
    buf += augmentString(line).toInt
  }

  val res = msort(buf.toList)
  print(res)
}