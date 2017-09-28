(* dt means date, dy day, mth month, and yr year *)

type year = int
type month = int
type day = int
type date = year * month * day

fun is_older (dt1 : date, dt2 : date) =
    #1 dt1 < #1 dt2 orelse
    (#1 dt1 = #1 dt2 andalso
     (#2 dt1 < #2 dt2 orelse
       (#2 dt1 = #2 dt2 andalso
	#3 dt1 < #3 dt2)
     )
    )

fun number_in_month (dts : date list, mth : month) =
    let fun helper (dts : date list, acc : int) =
	    if null dts
	    then acc
	    else if #2 (hd dts) = mth
	    then helper(tl dts, acc+1)
	    else helper(tl dts, acc)
    in
	helper(dts, 0)
    end

fun number_in_months (dts : date list, mths : month list) =
    let fun helper (mths : month list, acc : int) =
	    if null mths
	    then acc
	    else helper(tl mths, acc + number_in_month(dts, hd mths))
    in
	helper(mths, 0)
    end

fun dates_in_month (dts : date list, mth : month) =
    let fun helper (dts : date list,
		    acc :  date list) =
	    if null dts
	    then acc
	    else if #2 (hd dts) = mth
	    then helper(tl dts, (hd dts) :: acc)
	    else helper(tl dts, acc)
    in
	rev (helper (dts, []))
    end

fun dates_in_months (dts : date list, mths : month list) =
    let fun helper (mths : month list, acc : date list) =
	    if null mths
	    then acc
	    else helper(tl mths, acc @ dates_in_month(dts, hd mths))
    in
	helper(mths, [])
    end

fun get_nth (strs : string list, n : int) =
    if n = 1
    then hd strs
    else get_nth(tl strs, n-1)

fun date_to_string (dt : date) =
    let val mth_names = ["January", "February", "March", "April",
			 "May", "June", "July", "August",
			 "September", "October", "November", "December"]
    in
	get_nth(mth_names, #2 dt) ^ " " ^ Int.toString(#3 dt) ^ ", " ^
	Int.toString(#1 dt)
    end

fun number_before_reaching_sum (sum : int, xs : int list) =
    let fun helper (sum : int, xs : int list, acc) =
	    if hd xs < sum
	    then helper(sum - hd xs, tl xs, acc+1)
	    else acc
    in
	helper(sum, xs, 0)
    end

(* simple version w/o tail recursion *)
(* fun number_before_reaching_sum (sum : int, xs : int list) = *)
(*     if hd xs < sum *)
(*     then 1 + number_before_reaching_sum(sum - hd xs, tl xs) *)
(*     else 0 *)

fun what_month (day_of_year : day) : month =
    let val days_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    in
	1 + number_before_reaching_sum(day_of_year, days_in_month)
    end

fun month_range (day1 : day, day2 : day) =
    let fun helper (day1 : day, acc : month list) =
	    if day1 > day2
	    then rev acc
	    else helper(day1+1, what_month(day1) :: acc)
    in
	helper (day1, [])
    end

fun oldest (dts : date list) =
    let fun helper (dts : date list, acc : date) =
	    if null dts
	    then acc
	    else if is_older(hd dts, acc)
	    then helper(tl dts, hd dts)
	    else helper(tl dts, acc)
    in
	if null dts
	then NONE
	else SOME (helper (tl dts, hd dts))
    end

fun in_list (mth : month, mths : month list) =
    not (null mths) andalso
    (hd mths = mth orelse in_list(mth, tl mths))

fun unique (mths : month list) =
    let fun helper (mths : month list, acc : month list) =
	    if null mths
	    then rev acc
	    else if in_list(hd mths, acc)
	    then helper(tl mths, acc)
	    else helper(tl mths, (hd mths) :: acc)
    in
	helper (mths, [])
    end

fun sort_mths (mths : month list) =
    let fun merge (mths1 : month list, mths2 : month list,
		  acc : month list) =
	    if null mths1 orelse null mths2
	    then revAppend (acc,  mths1 @ mths2)
	    else if hd mths1 < hd mths2
	    then merge (tl mths1, mths2, (hd mths1) :: acc)
	    else merge (mths1, tl mths2, (hd mths2) :: acc)
	val len = length mths
	val split = len div 2
    in
	if len <= 1
	then mths
	else merge(sort_mths (List.take (mths, split)),
		   sort_mths (List.drop (mths, split)),
		   [])
    end

fun remove_duplicate (mths : month list) =
    let val sorted_mths = sort_mths mths
	fun helper (mths : month list, acc : month list) =
	    if null mths
	    then rev acc
	    else if hd mths = hd acc
	    then helper (tl mths, acc)
	    else helper (tl mths, (hd mths) :: acc)
    in
	if null sorted_mths
	then sorted_mths
	else helper (tl sorted_mths, [hd sorted_mths])
    end
	

fun number_in_months_challenge (dts : date list,
				mths : int list) =
    number_in_months(dts, remove_duplicate mths)

fun dates_in_months_challenge (dts : date list,
			       mths : int list) =
    dates_in_months(dts, remove_duplicate mths)

fun reasonable_date (dt : date) =
    let val yr = #1 dt
	val mth = #2 dt
	val dy = #3 dt
	val isLeapYear =
	    (yr mod 400 = 0) orelse
	    (yr mod 4 = 0 andalso yr mod 100 <>0)
	val feb_len =
	    if isLeapYear then 29 else 28
	val mth_len =
	    [31, feb_len, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
	fun get_nth (days : int list, mth : month) =
	    if mth = 1
	    then hd days
	    else get_nth(tl days, mth - 1)
    in
	yr > 0 andalso
	mth >= 1 andalso mth <= 12 andalso
	dy >= 1 andalso dy <= get_nth(mth_len, mth)
    end
