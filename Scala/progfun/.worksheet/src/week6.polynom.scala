package week6

object polynom {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet")

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
  };$skip(897); 

  val p1 = new Poly(1 -> 2., 3 -> 4., 5 -> 6.2);System.out.println("""p1  : week6.polynom.Poly = """ + $show(p1 ));$skip(37); 
  val p2 = new Poly(0 -> 3., 3 -> 7);System.out.println("""p2  : week6.polynom.Poly = """ + $show(p2 ));$skip(16); val res$0 = 

  p1.terms(0);System.out.println("""res0: Double = """ + $show(res$0));$skip(12); val res$1 = 

  p1 + p2;System.out.println("""res1: week6.polynom.Poly = """ + $show(res$1))}
}
