(* Coursera Programming Languages, Homework 3, Provided Code *)

exception NoAnswer

datatype pattern = Wildcard
		 | Variable of string
		 | UnitP
		 | ConstP of int
		 | TupleP of pattern list
		 | ConstructorP of string * pattern

datatype valu = Const of int
	      | Unit
	      | Tuple of valu list
	      | Constructor of string * valu

fun g f1 f2 p =
    let 
	val r = g f1 f2 
    in
	case p of
	    Wildcard          => f1 ()
	  | Variable x        => f2 x
	  | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
	  | ConstructorP(_,p) => r p
	  | _                 => 0
    end

(**** for the challenge problem only ****)

datatype typ = Anything
	     | UnitT
	     | IntT
	     | TupleT of typ list
	     | Datatype of string

(**** you can put all your code here ****)

val only_capitals = List.filter (fn s => (Char.isUpper o String.sub) (s, 0))

val longest_string1 = List.foldl (fn (s1, s2) => if String.size(s1) > String.size(s2) then s1 else s2) ""

val longest_string2 = List.foldl (fn (s1, s2) => if String.size(s1) < String.size(s2) then s2 else s1) ""

(* The following is the alternative code for longest_string2 with List.foldr function

val longest_string2 = List.foldr (fn (s1, s2) => if String.size(s1) > String.size(s2) then s1 else s2 *)

(* The following is another alternative code for longest_string2 with the same structure of longest_string1

val longest_string2 = List.foldl (fn (s1, s2) => if String.size(s1) >= String.size(s2) then s1 else s2 *)

fun longest_string_helper f sl = List.foldl (fn (s1, s2) => if f(String.size(s1), String.size(s2)) then s1 else s2) "" sl

val longest_string3 = longest_string_helper (fn (i, j) => i > j)

val longest_string4 = longest_string_helper (fn (i, j) => i >= j)

val longest_capitalized = longest_string1 o only_capitals

val rev_string = String.implode o List.rev o String.explode

fun first_answer f [] = raise NoAnswer
  | first_answer f (x::xs) = case f x of
			       SOME v => v
			     | NONE => first_answer f xs

(* The following is alternative code for all_answers with valOf function

fun all_answers f l =
    let fun aux ([], acc) = acc
	  | aux ((x::xs), acc) = case f x of
				     NONE => NONE
				   | SOME v => aux(xs, SOME ((valOf acc) @ v))
    in aux(l, SOME []) end *)

(* The following is the code for all_answers without valOf function *)

fun all_answers f l =
    let fun aux ([], acc1, acc2) = (acc1, acc2)
	  | aux ((x::xs), acc1, acc2) = case f x of
					    NONE => (acc1, false)
					 | SOME v => aux(xs, acc1 @ v, acc2)
    in case aux(l, [], true) of
	   (v, true) => SOME v
	| _ => NONE
    end

val count_wildcards = g (fn _ => 1) (fn _ => 0)

val count_wild_and_variable_lengths = g (fn _ => 1) (fn s => String.size(s))

fun count_some_var (s, p) = g (fn _ => 0) (fn s1 => if s=s1 then 1 else 0) p

fun generate_string (Variable s) = [s]
  | generate_string (TupleP ps) = List.foldl (fn (p1, acc) => acc @ generate_string p1) [] ps
  | generate_string (ConstructorP(_, p)) = generate_string p
  | generate_string _ = []

fun isDuplicate [] = false
  | isDuplicate (x::xs) = List.exists (fn s => if s=x then true else false) xs orelse isDuplicate xs

val check_pat = not o isDuplicate o generate_string

fun match (_, Wildcard) = SOME []
  | match (x, Variable s) = SOME [(s, x)]
  | match (Unit, UnitP) = SOME []
  | match (Const x1, ConstP x2) = if x1=x2 then SOME [] else NONE
  | match (Tuple xs, TupleP ps) = (all_answers match (ListPair.zipEq(xs, ps)) handle UnequalLengths => NONE)
  | match (Constructor (s1, v), ConstructorP (s2, p)) = if s1=s2 then match(v, p) else NONE
  | match _ = NONE

fun first_match v ps = SOME (first_answer (fn p => match(v,p)) ps) handle NoAnswer => NONE
