package week4

object idealized {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val a = MyTrue                                  //> a  : week4.MyTrue.type = MyTrue
  val b = MyFalse                                 //> b  : week4.MyFalse.type = MyFalse
  a.not                                           //> res0: week4.MyBoolean = MyFalse
  b.not                                           //> res1: week4.MyBoolean = MyTrue
  a < b                                           //> res2: week4.MyBoolean = MyFalse
  b < a                                           //> res3: week4.MyBoolean = MyTrue
  val One = new Succ(Zero)                        //> One  : week4.Succ = week4.Succ@5b38d6
  val Two = new Succ(One)                         //> Two  : week4.Succ = week4.Succ@f0a755
  val Three = new Succ(Two)                       //> Three  : week4.Succ = week4.Succ@179d47f
  Zero < Three                                    //> res4: week4.MyBoolean = MyTrue
  Two < Three                                     //> res5: week4.MyBoolean = MyTrue
  Three < Zero                                    //> res6: week4.MyBoolean = MyFalse
  Three < Two                                     //> res7: week4.MyBoolean = MyFalse
  Three < Three                                   //> res8: week4.MyBoolean = MyFalse
  Three <= Three                                  //> res9: week4.MyBoolean = MyTrue
  One <= Three                                    //> res10: week4.MyBoolean = MyTrue
  Two <= Three                                    //> res11: week4.MyBoolean = MyTrue
  Three <= Zero                                   //> res12: week4.MyBoolean = MyFalse
  Three <= Two                                    //> res13: week4.MyBoolean = MyFalse
  Three < One                                     //> res14: week4.MyBoolean = MyFalse
  Three == One                                    //> res15: week4.MyBoolean = MyFalse
  One == Three                                    //> res16: week4.MyBoolean = MyFalse
  Two == Two                                      //> res17: week4.MyBoolean = MyTrue
  Zero == Zero                                    //> res18: week4.MyBoolean = MyTrue
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