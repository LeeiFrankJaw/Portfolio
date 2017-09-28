signature SAFE_MATH =
sig
    (* val pi : real *)
    val getPi : unit -> real
end

structure Safe_Math :> SAFE_MATH =
struct

val pi = 3.141592

fun getPi () = pi

end
