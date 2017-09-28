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
	  | TupleP ps         => foldl (fn (p,i) => (r p) + i) 0 ps
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

val only_capitals =
    List.filter (fn s => (Char.isUpper o String.sub) (s, 0)
		handle Subscript => false)

val longest_string1 = foldl (fn (s1, s2) =>
				if String.size s1 > String.size s2
				then s1
				else s2)
			    ""

val longest_string2 = foldl (fn (s1, s2) =>
				if String.size s1 < String.size s2
				then s2
				else s1)
			    ""

(* The following is the alternative code for longest_string2 with
   foldr function *)

(*
val longest_string2 = foldr (fn (s1, s2) =>
				     if String.size s1 > String.size s2
				     then s1
				     else s2)
				 ""
*)

(* The following is another alternative code for longest_string2 with
   the same structure of longest_string1 *)

(*
val longest_string2 = foldl (fn (s1, s2) =>
				     if String.size s1 >= String.size s2
				     then s1
				     else s2)
				 ""
*)

fun longest_string_helper f ss =
    foldl (fn (s1, s2) =>
	      if f(String.size s1, String.size s2)
	      then s1
	      else s2)
	  "" ss

val longest_string3 = longest_string_helper (op >)

val longest_string4 = longest_string_helper (op >=)

val longest_capitalized = longest_string1 o only_capitals

val rev_string = implode o rev o explode

fun first_answer f [] = raise NoAnswer
  | first_answer f (x::xs) = case f x of
				 SOME v => v
			       | NONE => first_answer f xs

fun all_answers f xs =
    let fun helper ([], acc) = SOME acc
	  | helper (x::xs, acc) =
	    case f x of
		NONE => NONE
	      | SOME v => helper (xs, acc @ v)
    in
	helper(xs, [])
    end

val count_wildcards = g (fn _ => 1) (fn _ => 0)

val count_wild_and_variable_lengths = g (fn _ => 1) String.size

fun count_some_var (s, p) =
    g (fn _ => 0) (fn s1 => if s = s1 then 1 else 0) p

fun generate_string (Variable s) = [s]
  | generate_string (TupleP ps) =
    foldl (fn (p1, acc) => acc @ generate_string p1) [] ps
  | generate_string (ConstructorP(_, p)) = generate_string p
  | generate_string _ = []

fun isDuplicate ([] : string list) = false
  | isDuplicate (x::xs) =
    List.exists (fn s => s=x) xs orelse isDuplicate xs

val check_pat = not o isDuplicate o generate_string

fun match (_, Wildcard) = SOME []
  | match (v, Variable s) = SOME [(s, v)]
  | match (Unit, UnitP) = SOME []
  | match (Const x1, ConstP x2) = if x1 = x2 then SOME [] else NONE
  | match (Tuple xs, TupleP ps) =
    (all_answers match (ListPair.zipEq (xs, ps))
     handle UnequalLengths => NONE)
  | match (Constructor (s1, v), ConstructorP (s2, p)) =
    if s1 = s2 then match(v, p) else NONE
  | match _ = NONE

fun first_match v ps =
    (SOME (first_answer (fn p => match(v,p)) ps)
     handle NoAnswer => NONE)

(* fun typ2str Anything = "Anything" *)
(*   | typ2str UnitT = "UnitT" *)
(*   | typ2str IntT = "IntT" *)
(*   | typ2str (TupleT ts) = *)
(*     "TupleT [" ^ String.concatWith ", " (map typ2str ts) ^ "]" *)
(*   | typ2str (Datatype str) = "Datatype " ^ str *)

fun typecheck_patterns (cs, ps) =
    let fun combine (Anything, t) = t
	  | combine (t, Anything) = t
	  | combine (UnitT, UnitT) = UnitT
	  | combine (IntT, IntT) = IntT
	  | combine (TupleT ts1, TupleT ts2) =
	    (* if List.length ts1 = 0 *)
	    (* then raise NoAnswer *)
	    TupleT (map combine (ListPair.zipEq (ts1, ts2)))
	  | combine (Datatype s1, Datatype s2) =
	    if s1 = s2
	    then Datatype s1
	    else raise NoAnswer
	  | combine _ = raise NoAnswer

	fun infer Wildcard = Anything
	  | infer (Variable _) = Anything
	  | infer UnitP = UnitT
	  | infer (ConstP _) = IntT
	  | infer (TupleP ps) = TupleT (map infer ps)
	  | infer (ConstructorP (str, p)) =
	    first_answer (fn (str1, str2, t) =>
			     if str1 = str
			     then (combine (t, infer p);
				   SOME (Datatype str2))
				  handle _ => NONE
			     else NONE)
			 cs
    in
	SOME (foldl combine (infer (hd ps)) (map infer (tl ps)))
	handle _ => NONE
    end
