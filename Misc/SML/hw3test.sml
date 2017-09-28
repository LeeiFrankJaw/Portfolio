(* Homework3 Simple Test*)
(* These are basic test cases. Passing these tests does not guarantee that your code will pass the actual homework grader *)
(* To run the test, add a new line to the top of this file: use "homeworkname.sml"; *)
(* All the tests should evaluate to true. For example, the REPL should say: val test1 = true : bool *)

use "hw3provided.sml";

val cs = [("Num", "rank", IntT),
	  ("getInt", "LinkedList", IntT),
	  ("nextInt", "Linkedlist", Datatype "Linkedlist"),
	  ("getElem", "Tree", IntT),
	  ("leftChild", "Tree", Datatype "Tree"),
	  ("rightChild", "Tree", Datatype "Tree")]

val color = [("Red", "Color", UnitT),
	     ("Green", "Color", UnitT),
	     ("Blue", "Color", UnitT)]

val auto = [("Sedan", "Auto", Datatype "Color"),
	    ("Truck", "Auto", TupleT [IntT, Datatype "Color"]),
	    ("SUV", "Auto", UnitT)]

val alist = [("Empty", "Alist", UnitT),
	     ("List", "Alist", TupleT [Anything, Datatype "Alist"])]

val ps1 = [Variable("x"), Variable("y")]

val ps2 = [Wildcard, Wildcard]

val ps3 = [TupleP ps1, TupleP ps2]

val ans3 = TupleT [Anything, Anything]

val ps4 = [TupleP ps2, TupleP [Wildcard, TupleP ps2]]

val ans4 = TupleT [Anything, ans3]

val ps5 = [ConstP 1, ConstP 3]

val ps6 = [ConstructorP ("getInt", ConstP 5),
	   ConstructorP ("leftChild", ConstructorP ("Num", ConstP 5))]

val ps7 = [ConstructorP ("getElem", ConstP 5),
	   ConstructorP ("leftChild", ConstructorP ("getElem", ConstP 5))]

val ps8 = [TupleP ps7, TupleP ps5]

val ps9 = [ConstructorP("Red", UnitP), Wildcard]

val ans9 = Datatype "Color"

val ps10 = [ConstructorP("Sedan", Variable "a"),
	    ConstructorP("Truck", TupleP [Variable "b", Wildcard]),
	    Wildcard]

val ans10 = Datatype "Auto"

val ps11 = [ConstructorP("Sedan", ConstP 5),
	    ConstructorP("Truck", TupleP [Variable "b", Wildcard]),
	    Wildcard]

val ps12 = [ConstructorP("Coupe", ConstP 5),
	    ConstructorP("Truck", TupleP [Variable "b", Wildcard]),
	    Wildcard]

val ps13 = ConstructorP("List",TupleP[ConstP 10, ConstructorP("Empty",UnitP)]) :: []

val ps14 = [ConstructorP("Empty",UnitP),
	    ConstructorP("List",TupleP[ConstP 10, ConstructorP("Empty",UnitP)]),
	    Wildcard]

val ps15 = [ConstructorP("Empty",UnitP),
	    ConstructorP("List",TupleP[Variable "x", Wildcard])]

val ps16 = [ConstructorP("Empty",UnitP),
	    ConstructorP("List",TupleP[ConstructorP("SUV", UnitP), Wildcard])]

val ans_alist = Datatype "Alist"

val test1 = only_capitals ["A","B","C"] = ["A","B","C"]

val test2 = longest_string1 ["A","bc","C"] = "bc"

val test3 = longest_string2 ["A","bc","C"] = "bc"

val test4a= longest_string3 ["A","bc","C"] = "bc"

val test4b= longest_string4 ["A","B","C"] = "C"

val test5 = longest_capitalized ["A","bc","C"] = "A";

val test6 = rev_string "abc" = "cba";

val test7 = first_answer (fn x => if x > 3 then SOME x else NONE) [1,2,3,4,5] = 4

val test8 = all_answers (fn x => if x = 1 then SOME [x] else NONE) [2,3,4,5,6,7] = NONE

val test9a = count_wildcards Wildcard = 1

val test9b = count_wild_and_variable_lengths (Variable("a")) = 1

val test9c = count_some_var ("x", Variable("x")) = 1;

val test10 = check_pat (Variable("x")) = true

val test11 = match (Const(1), UnitP) = NONE

val test12 = first_match Unit [UnitP] = SOME []

val user_test0 = typecheck_patterns (cs, []) = NONE

val user_test1 = typecheck_patterns ([], ps1) = SOME Anything

val user_test2 = typecheck_patterns (cs, ps2) = SOME Anything

val user_test3 = typecheck_patterns (cs, ps3) = SOME ans3

val user_test4 = typecheck_patterns (cs, ps4) = SOME ans4

val user_test5 = typecheck_patterns (cs, ps5) = SOME IntT

val user_test6 = typecheck_patterns (cs, ps6) = NONE

val user_test7 = typecheck_patterns (cs, ps7) = SOME (Datatype "Tree")

val user_test8 = typecheck_patterns (cs, ps8) = NONE

val user_test9 = typecheck_patterns (color, ps9) = SOME ans9

val user_test10 = typecheck_patterns (color @ auto, ps10) = SOME ans10

val user_test11 = typecheck_patterns (color @ auto, ps11) = NONE

val user_test12 = typecheck_patterns (color @ auto, ps12) = NONE

val user_test13 = typecheck_patterns (alist, ps13) = SOME ans_alist

val user_test14 = typecheck_patterns (alist, ps14) = SOME ans_alist

val user_test15 = typecheck_patterns (alist, ps15) = SOME ans_alist

val user_test16 = typecheck_patterns (auto @ alist, ps16) = SOME ans_alist
