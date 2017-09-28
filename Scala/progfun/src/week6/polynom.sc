package week6

object polynom {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class Poly(terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue .0
    /* def +(other: Poly) = new Poly(terms ++ (other.terms map adjust))
    def adjust(term: (Int, Double)): (Int, Double) = {
    	val (exp, coeff) = term
    	exp -> (coeff + terms(exp))
    	/* terms get exp match {
    		case Some(coeff1) => exp -> (coeff + coeff1)
    		case None => term
    	} */
    } */
    def +(other: Poly) = new Poly((other.terms foldLeft terms)(addTerm))

    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }
    override def toString =
      (for (
        (exp, coeff) <- terms.toList.sorted.reverse
      ) yield coeff + " x^" + exp) mkString " + "
  }

  val p1 = new Poly(1 -> 2., 3 -> 4., 5 -> 6.2)   //> p1  : week6.polynom.Poly = 6.2 x^5 + 4.0 x^3 + 2.0 x^1
  val p2 = new Poly(0 -> 3., 3 -> 7)              //> p2  : week6.polynom.Poly = 7.0 x^3 + 3.0 x^0

  p1.terms(0)                                     //> res0: Double = 0.0

  p1 + p2                                         //> res1: week6.polynom.Poly = 6.2 x^5 + 11.0 x^3 + 2.0 x^1 + 3.0 x^0
}