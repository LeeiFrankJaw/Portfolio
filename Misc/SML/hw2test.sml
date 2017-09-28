(* Homework2 Simple Test *)
(* These are basic test cases. Passing these tests does not guarantee that your code will pass the actual homework grader *)
(* To run the test, add a new line to the top of this file: use "homeworkname.sml"; *)
(* All the tests should evaluate to true. For example, the REPL should say: val test1 = true : bool *)

use "hw2provided.sml";

val ss1 = ["Fred","Fredrick"]

val ss2 = ["Elizabeth","Betty"]

val ss3 = ["Freddie","Fred","F"]

val sss = [ss1, ss2, ss3]

val ss1c = ["Fredrick"]

val ss3c = ["Freddie", "F"]

val cs = [(Clubs,Ace),(Spades,Ace),(Clubs,Ace),(Spades,Ace)]

val ms = [Draw,Draw,Draw,Draw,Draw]

val test1 = all_except_option("string", ["string"]) = SOME []

val test2 = get_substitutions1([["foo"],["there"]], "foo") = []

val test3 = get_substitutions2([["foo"],["there"]], "foo") = []

val test4 = similar_names(sss, {first="Fred", middle="W", last="Smith"}) =
	    [{first="Fred", last="Smith", middle="W"},
	    {first="Fredrick", last="Smith", middle="W"},
	    {first="Freddie", last="Smith", middle="W"},
	    {first="F", last="Smith", middle="W"}]

val test5 = card_color((Clubs, Num 2)) = Black

val test6 = card_value((Clubs, Num 2)) = 2

val test7 = remove_card([(Hearts, Ace)], (Hearts, Ace), IllegalMove) = []

val test8 = all_same_color([(Hearts, Ace), (Hearts, Ace)]) = true

val test9 = sum_cards([(Clubs, Num 2),(Clubs, Num 2)]) = 4

val test10 = score([(Hearts, Num 2),(Clubs, Num 4)],10) = 4

val test11 = officiate([(Hearts, Num 2),(Clubs, Num 4)],[Draw], 15) = 6

val test12 = officiate(cs, ms, 42)
             = 3

val test13 = ((officiate([(Clubs,Jack),(Spades,Num(8))],
                         [Draw,Discard(Hearts,Jack)], 42);
               false)
              handle IllegalMove => true)

val provided_test1 = (* correct behavior: raise IllegalMove *)
    (let val cards = [(Clubs,Jack),(Spades,Num(8))]
	 val moves = [Draw,Discard(Hearts,Jack)]
     in
	 officiate(cards,moves,42)
     end; false) handle IllegalMove => true

val provided_test2 = (* correct behavior: return 3 *)
    let val cards = [(Clubs,Ace),(Spades,Ace),(Clubs,Ace),(Spades,Ace)]
	val moves = [Draw,Draw,Draw,Draw,Draw]
    in
 	officiate(cards,moves,42)
    end = 3

val user_test1 = get_substitutions1(sss, "Fred") = ss1c @ ss3c

val user_test2 = all_except_option("Fred", ss1) = SOME ss1c

val user_test3 = all_except_option("Fred", ss2) = NONE

val user_test4 = all_except_option("Fred", ss3) = SOME ss3c

val user_test5 = score_challenge(cs, 20) = 3

val user_test6 = officiate_challenge(cs, ms, 40) = 3

val user_test7 = careful_player(cs, 40) = [Draw,Draw,Draw]

val user_test8 = careful_player(cs, 44) = [Draw,Draw,Draw,Draw]

val user_test9 =
    let val ms = careful_player(cs, 45) 
    in
	ms = [Draw,Draw,Draw,Draw] orelse
	ms = [Draw,Draw,Draw,Draw,Draw]
    end

val user_test10 = careful_player(cs, 55) = [Draw,Draw,Draw,Draw,Draw]

val user_test11 = careful_player([(Spades,Num 7),(Hearts,King),(Clubs,Ace),
				  (Diamonds,Num 2)], 18)
		  = [Draw,Draw,Discard (Hearts,King),Draw]

val user_test12 = careful_player([(Diamonds,Num 2),(Clubs,Ace)], 11)
		  = [Draw,Discard (Diamonds,Num 2),Draw]
