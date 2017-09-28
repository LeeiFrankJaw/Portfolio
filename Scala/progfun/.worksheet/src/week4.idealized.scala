package week4

object idealized {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(78); 
  println("Welcome to the Scala worksheet");$skip(19); 

  val a = MyTrue;System.out.println("""a  : week4.MyTrue.type = """ + $show(a ));$skip(18); 
  val b = MyFalse;System.out.println("""b  : week4.MyFalse.type = """ + $show(b ));$skip(8); val res$0 = 
  a.not;System.out.println("""res0: week4.MyBoolean = """ + $show(res$0));$skip(8); val res$1 = 
  b.not;System.out.println("""res1: week4.MyBoolean = """ + $show(res$1));$skip(8); val res$2 = 
  a < b;System.out.println("""res2: week4.MyBoolean = """ + $show(res$2));$skip(8); val res$3 = 
  b < a;System.out.println("""res3: week4.MyBoolean = """ + $show(res$3));$skip(27); 
  val One = new Succ(Zero);System.out.println("""One  : week4.Succ = """ + $show(One ));$skip(26); 
  val Two = new Succ(One);System.out.println("""Two  : week4.Succ = """ + $show(Two ));$skip(28); 
  val Three = new Succ(Two);System.out.println("""Three  : week4.Succ = """ + $show(Three ));$skip(15); val res$4 = 
  Zero < Three;System.out.println("""res4: week4.MyBoolean = """ + $show(res$4));$skip(14); val res$5 = 
  Two < Three;System.out.println("""res5: week4.MyBoolean = """ + $show(res$5));$skip(15); val res$6 = 
  Three < Zero;System.out.println("""res6: week4.MyBoolean = """ + $show(res$6));$skip(14); val res$7 = 
  Three < Two;System.out.println("""res7: week4.MyBoolean = """ + $show(res$7));$skip(16); val res$8 = 
  Three < Three;System.out.println("""res8: week4.MyBoolean = """ + $show(res$8));$skip(17); val res$9 = 
  Three <= Three;System.out.println("""res9: week4.MyBoolean = """ + $show(res$9));$skip(15); val res$10 = 
  One <= Three;System.out.println("""res10: week4.MyBoolean = """ + $show(res$10));$skip(15); val res$11 = 
  Two <= Three;System.out.println("""res11: week4.MyBoolean = """ + $show(res$11));$skip(16); val res$12 = 
  Three <= Zero;System.out.println("""res12: week4.MyBoolean = """ + $show(res$12));$skip(15); val res$13 = 
  Three <= Two;System.out.println("""res13: week4.MyBoolean = """ + $show(res$13));$skip(14); val res$14 = 
  Three < One;System.out.println("""res14: week4.MyBoolean = """ + $show(res$14));$skip(15); val res$15 = 
  Three == One;System.out.println("""res15: week4.MyBoolean = """ + $show(res$15));$skip(15); val res$16 = 
  One == Three;System.out.println("""res16: week4.MyBoolean = """ + $show(res$16));$skip(13); val res$17 = 
  Two == Two;System.out.println("""res17: week4.MyBoolean = """ + $show(res$17));$skip(15); val res$18 = 
  Zero == Zero;System.out.println("""res18: week4.MyBoolean = """ + $show(res$18))}
}

abstract class MyBoolean {
  def ifThenElse[T](t: => T, e: => T): T
  def &&(x: => MyBoolean): MyBoolean = ifThenElse(x, MyFalse)
  def ||(x: => MyBoolean): MyBoolean = ifThenElse(MyTrue, x)
  def not = ifThenElse(MyFalse, MyTrue)
  def <(x: MyBoolean): MyBoolean = ifThenElse(MyFalse, x)
  def ==(x: MyBoolean): MyBoolean = ifThenElse(x, x.not)
  def !=(x: MyBoolean): MyBoolean = (this == x).not
}

object MyTrue extends MyBoolean {
  def ifThenElse[T](t: => T, e: => T) = t
  override def toString = "MyTrue"
}

object MyFalse extends MyBoolean {
  def ifThenElse[T](t: => T, e: => T) = e
  override def toString = "MyFalse"
}

abstract class Nat {
  def isZero: MyBoolean
  def predeccesor: Nat
  def successor: Nat = new Succ(this)
  def +(that: Nat): Nat
  def -(that: Nat): Nat
  def <(that: Nat): MyBoolean =
    (that.isZero) ifThenElse (MyFalse,
      (this.isZero) ifThenElse (MyTrue, this.predeccesor < that.predeccesor))
  def >(that: Nat): MyBoolean =
    (this.isZero) ifThenElse (MyFalse,
      (that.isZero) ifThenElse (MyTrue, this.predeccesor > that.predeccesor))
  def ==(that: Nat): MyBoolean =
    (this.isZero || that.isZero) ifThenElse (this.isZero == that.isZero, (this.predeccesor == that.predeccesor))
  def <=(that: Nat): MyBoolean = (this > that).not
  def >=(that: Nat): MyBoolean = (this < that).not
}

object Zero extends Nat {
  def isZero = MyTrue
  def predeccesor = throw new Error("Zero.predeccesor")
  def +(that: Nat): Nat = that
  def -(that: Nat): Nat = (that.isZero) ifThenElse (this, throw new Error("Zero.predeccesor"))
}

class Succ(n: Nat) extends Nat {
  def isZero = MyFalse
  def predeccesor = n
  def +(that: Nat): Nat = new Succ(n + that)
  def -(that: Nat): Nat = (that.isZero) ifThenElse (this, n - that.predeccesor)
}
