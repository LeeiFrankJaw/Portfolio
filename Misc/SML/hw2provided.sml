(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* put your solutions for problem 1 here *)

(* Even if the SML compiler can figure out the types of function
   arguments, I prefer to specify them explicitly. *)

fun all_except_option (s : string, ss : string list) =
    let fun helper ([], acc) = NONE
	  | helper (hs::ss, acc) =
	    if same_string(s, hs)
	    then SOME (List.revAppend (acc, ss))
	    else helper(ss, hs::acc)
    in
	helper(ss, [])
    end

fun get_substitutions1 ([], s : string) = []
  | get_substitutions1 (ss::sss, s) =
    let val x = case all_except_option(s, ss) of
		    NONE => []
		  | SOME v => v
    in
	x @ get_substitutions1(sss, s)
    end

fun get_substitutions2 (sss : string list list, s : string) =
    let fun helper ([], acc : string list) = acc
	  | helper (ss::sss, acc) =
	    let val x = case all_except_option(s, ss) of
			    NONE => []
			  | SOME v => v
	    in
		helper(sss, acc @ x)
	    end
    in
	helper(sss, [])
    end

fun similar_names (sss : string list list,
		   {first=x : string, middle=y : string, last=z : string}) =
    let val substitutions = get_substitutions2(sss, x)
	fun helper ([], acc) = rev acc
	  | helper (s::ss, acc) =
	    helper (ss, {first=s, middle=y, last=z} :: acc)
    in
	helper (substitutions, [{first=x, middle=y, last=z}])
    end

(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

(* put your solutions for problem 2 here *)

(* Just let the compiler check if it's exhaustive *)
fun card_color ((Clubs, _) : card) = Black
  | card_color (Spades, _) = Black
  | card_color (Diamonds, _) = Red
  | card_color (Hearts, _) = Red

fun card_value ((_, Ace) : card) = 11
  | card_value (_, Num x) = x
  | card_value _ = 10

fun remove_card (cs : card list, c : card, e : exn) =
    let fun helper ([], acc) = raise e
	  | helper (hc::cs, acc) =
	    if hc = c
	    then List.revAppend (acc, cs) 
	    else helper(cs, hc::acc)
    in
	helper (cs, [])
    end

fun all_same_color ([]) = true
  | all_same_color ([_ : card]) = true
  | all_same_color (head::neck::rest) =
    card_color(head) = card_color(neck)
    andalso all_same_color(neck::rest)

fun sum_cards_core (cs : card list, getValue : card -> int) =
    let fun helper ([], acc) = acc
	  | helper (hc::cs, acc) = helper(cs, acc + getValue(hc))
    in
	helper(cs, 0)
    end

fun sum_cards (cs : card list) = sum_cards_core(cs, card_value)

fun sum2pscore (sum : int, goal : int) =
    if sum > goal
    then 3 * (sum - goal)
    else goal - sum

fun pscore2score (cs : card list, pscore : int) =
    if all_same_color(cs)
    then pscore div 2
    else pscore

fun score_core (cs : card list, goal : int, getSum : card list -> int) =
    let val pscore = sum2pscore(getSum(cs), goal)
    in
	pscore2score(cs, pscore)
    end

fun score (cs : card list, goal : int) = score_core(cs, goal, sum_cards)

fun officiate_core (cs : card list, ms : move list, goal : int,
		    getSum : card list -> int,
		    getScore : card list * int -> int) =
    let fun helper (cs, (Discard c) :: ms, held) =
	    helper (cs, ms, remove_card (held, c, IllegalMove))
	  | helper (hc::cs, Draw :: ms, held) =
	    if getSum(hc::held) > goal
	    then getScore(hc::held, goal)
	    else helper(cs, ms, hc::held)
	  | helper (_, _, held) = getScore(held, goal)
    in
	helper(cs, ms, [])
    end

fun officiate (cs : card list, ms : move list, goal : int) =
    officiate_core(cs, ms, goal, sum_cards, score)

fun card_value_alt ((_, Ace) : card) = 1
  | card_value_alt (_, Num x) = x
  | card_value_alt _ = 10

fun sum_cards_alt (cs : card list) = sum_cards_core(cs, card_value_alt)

(* fun sum_cards_challenge (cs : card list) =
    Int.min(sum_cards(cs), sum_cards_alt(cs)) *)

(* fun score_alt (cs : card list, goal : int) = *)
(*     score_core(cs, goal, sum_cards_alt) *)

(* fun count (cs : card list) =  *)
(*     let fun helper ([], acc) = acc *)
(* 	  | helper ((_, Ace) :: cs, (b, n)) = helper (cs, (b, n+1)) *)
(* 	  | helper (hc::cs, (b, n)) =  helper (cs, (b+card_value(hc), n)) *)
(*     in *)
(* 	helper (cs, (0,0)) *)
(*     end *)

fun score_challenge (cs : card list, goal : int) =
    let val min = sum_cards_alt(cs)
	val r = (goal - min) mod 10
	val max = sum_cards(cs)
	val pscore =
	    if max <= goal
	    then goal - max
	    else if min >= goal
	    then 3 * (min - goal)
	    else Int.min(r, 3*(10-r))
    in
	pscore2score(cs, pscore)
    end

fun officiate_challenge (cs : card list, ms : move list, goal : int) =
    officiate_core(cs, ms, goal, sum_cards_alt, score_challenge)

fun check (diff : int, [] : card list, v : int) = NONE
  | check (diff, hc::held, v) =
    if diff + card_value(hc) = v
    then SOME hc
    else check(diff, held, v)

fun careful_player (cs : card list, goal : int) : move list =
    let fun helper (cs : card list, ms : move list,
		    held : card list, diff : int) =
	    if diff > 10
	    then case cs of
		     [] => Draw :: ms
		   | hc::tcs => helper(tcs, Draw :: ms, hc::held,
				       diff - card_value(hc))
	    else if diff = 0
	    then ms
	    else case cs of
		     [] => ms
		   | hc::tcs =>
		     let val v = card_value(hc)
		     in
			 case check(diff, held, v) of
			     NONE =>
			     if diff >= v
			     then helper(tcs, Draw :: ms, hc :: held, diff-v)
			     else ms
			   | SOME c => Draw :: (Discard c) :: ms
		     end
    in
	rev (helper(cs, [], [], goal))
    end
