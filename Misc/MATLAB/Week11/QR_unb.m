
% Copyright 2015 The University of Texas at Austin
%
% For licensing information see
%                http://www.cs.utexas.edu/users/flame/license.html 
%                                                                                 
% Programmed by: Name of author
%                Email of author

function [ Q_out, R_out ] = QR_unb( A, Q, ~ )

  n = size( A, 2 );
  R = zeros( n, n );

  [ AL, AR ] = FLA_Part_1x2( A, ...
                               0, 'FLA_LEFT' );

  [ QL, QR ] = FLA_Part_1x2( Q, ...
                               0, 'FLA_LEFT' );

  [ RTL, RTR, ...
    RBL, RBR ] = FLA_Part_2x2( R, ...
                               0, 0, 'FLA_TL' );

  while ( size( AL, 2 ) < size( A, 2 ) )

    [ A0, a1, A2 ]= FLA_Repart_1x2_to_1x3( AL, AR, ...
                                         1, 'FLA_RIGHT' );

    [ Q0, q1, Q2 ]= FLA_Repart_1x2_to_1x3( QL, QR, ...
                                         1, 'FLA_RIGHT' );

    [ R00,  r01,   R02,  ...
      r10t, rho11, r12t, ...
      R20,  r21,   R22 ] = FLA_Repart_2x2_to_3x3( RTL, RTR, ...
                                                  RBL, RBR, ...
                                                  1, 1, 'FLA_BR' );

    %------------------------------------------------------------%

    r01 = laff_gemv( 'Transpose', 1, Q0, a1, 0, r01 );
    q1 = laff_axpy( -1, ...
                    laff_gemv( 'No transpose', 1, Q0, r01, 0, a1 ), ...
                    a1 );
    rho11 = laff_norm2( q1 );
    q1 = laff_invscal( rho11, q1 );

    %------------------------------------------------------------%

    [ AL, AR ] = FLA_Cont_with_1x3_to_1x2( A0, a1, A2, ...
                                           'FLA_LEFT' );

    [ QL, QR ] = FLA_Cont_with_1x3_to_1x2( Q0, q1, Q2, ...
                                           'FLA_LEFT' );

    [ RTL, RTR, ...
      RBL, RBR ] = FLA_Cont_with_3x3_to_2x2( R00,  r01,   R02,  ...
                                             r10t, rho11, r12t, ...
                                             R20,  r21,   R22, ...
                                             'FLA_TL' );

  end

  Q_out = [ QL, QR ];

  R_out = [ RTL, RTR
            RBL, RBR ];

return

