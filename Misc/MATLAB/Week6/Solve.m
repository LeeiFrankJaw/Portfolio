function [ A_out, b_out ] = Solve( A, b )

  A_out = LU_unb_var5( A );
  z = Ltrsv_unb_var1( A_out, b );
  b_out = Utrsv_unb_var1( A_out, z );

return