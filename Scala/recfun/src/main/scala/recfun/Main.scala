package recfun
import common._

object Main {
  def main(args: Array[String]) {
    /*println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }*/
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 || c == r) 1 else pascal(c-1, r-1) + pascal(c, r-1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
      def aux(acc: Int, chars: List[Char]): Int =
        if (chars.isEmpty || acc < 0)
          acc
        else if (chars.head == '(')
          aux(acc+1, chars.tail)
        else if (chars.head == ')')
          aux(acc-1, chars.tail)
        else aux(acc, chars.tail)
      aux(0, chars) == 0
    }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def aux(coins: List[Int]): Int =
      if (coins.isEmpty) 0
      else countChange(money - coins.head, coins) + aux(coins.tail)
    
    if (money == 0) 1
    else if (money < 0) 0
    /*else if (!coins.isEmpty && coins.tail.isEmpty)
      (if (money % coins.head == 0) 1 else 0)*/
    else aux(coins)
  }
}
