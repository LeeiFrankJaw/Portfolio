(* Homework1 Simple Test *)
(* These are basic test cases. Passing these tests does not guarantee that your code will pass the actual homework grader *)
(* To run the test, add a new line to the top of this file: use "homeworkname.sml"; *)
(* All the tests should evaluate to true. For example, the REPL should say: val test1 = true : bool *)

use "hw1.sml";

fun is_Month_newer (dt1 : date, dt2 : date) = #2 dt1 > #2 dt2

val sort = ListMergeSort.sort is_Month_newer

val test1 = is_older((1,2,3),(2,3,4)) = true

val test2 = number_in_month([(2012,2,28),(2013,12,1)],2) = 1

val test3 = number_in_months([(2012,2,28), (2013,12,1), (2011,3,31),
			      (2011,4,28)], [2,3,4]) = 3

val test4 = dates_in_month([(2012,2,28), (2013,12,1)], 2) =
	    [(2012,2,28)]

val test5 = dates_in_months([(2012,2,28), (2013,12,1), (2011,3,31),
			     (2011,4,28)], [2,3,4]) =
	    [(2012,2,28), (2011,3,31), (2011,4,28)]

val test6 = get_nth(["hi", "there", "how", "are", "you"], 2) = "there"

val test7 = date_to_string((2013, 6, 1)) = "June 1, 2013"

val test8 = number_before_reaching_sum(10, [1,2,3,4,5]) = 3

val test9 = what_month(70) = 3

val test10 = month_range(31, 34) = [1,2,2,2]

val test11 = oldest([(2012,2,28), (2011,3,31), (2011,4,28)]) =
	     SOME (2011,3,31)

val test12 = is_older((2013,2,13), (2013,1,12)) = false

val test13 = is_older((2013,2,13), (2013,2,15)) = true

val test14 = is_older((2013,2,13), (2013,2,13)) = false

val test15 = is_older((2013,2,13), (2012,2,2)) = false

val test16 = number_in_month([(2013,2,13), (2013,1,12), (2012,2,28),
			      (2013,12,1)], 1) = 1

val test17 = number_in_month([(2013,2,13), (2013,1,12), (2012,2,28),
			      (2013,12,1)], 2) = 2

val test18 = number_in_month([(2013,1,12), (2012,2,28), (2013,12,1)],
			     2) = 1

val test19 = number_in_months([(2013,1,12), (2012,2,27), (2013,12,1),
			       (2012,2,28), (2013,12,1), (2011,3,31),
			       (2011,4,28)], [2,3,4]) = 4

val test20 = number_in_months_challenge([(2013,1,12), (2012,2,27),
			       (2013,12,1), (2012,2,28), (2013,12,1),
			       (2011,3,31), (2011,4,28)], [4,2,3,2,3,4]) = 4

val test21 = dates_in_month([(2013,1,12), (2012,2,27), (2013,12,1),
			       (2012,2,28), (2013,12,1), (2011,3,31),
			       (2011,4,28)], 2) =
	     [(2012,2,27), (2012,2,28)]

val test22 = dates_in_months([(2013,1,12), (2012,2,27), (2013,12,1),
			       (2012,2,28), (2013,12,1), (2011,3,31),
			       (2011,4,28)], [2,3,4]) =
	     [(2012,2,27), (2012,2,28), (2011,3,31), (2011,4,28)]

val test23 = sort (dates_in_months_challenge ([(2013,1,12), (2012,2,27),
			       (2013,12,1), (2012,2,28), (2013,12,1),
			       (2011,3,31), (2011,4,28)], [4,2,3,2,3,4])
		   ) =
	     sort [(2011,4,28), (2012,2,27), (2012,2,28), (2011,3,31)]

val test24 = reasonable_date(2014,10,13) = true

val test25 = reasonable_date(2012,2,29) = true

val test26 = reasonable_date(2010,2,29) = false

val test27 = reasonable_date(0,7,1) = false

val test28 = reasonable_date(2014,12,32) = false
