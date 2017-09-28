function [ x_out ] = laff_scal( alpha, x )
% scale vector y with scalar alpha

[ m_x, n_x ] = size( x );

if ~isscalar( alpha )
    x_out = 'FAILED';
    return
end

if ~isvector( x )
    x_out = 'FAILED';
    return
end

x_out = zeros( m_x, n_x );

% x is row vector
if m_x == 1
    for i=1:n_x
        x_out( 1, i ) = alpha * x( 1, i );
    end
% x is column vector    
else
    for i=1:m_x
        x_out( i, 1 ) = alpha * x( i, 1 );
    end
end

return
end

