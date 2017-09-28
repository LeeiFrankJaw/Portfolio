function [ y_out ] = laff_axpy( alpha, x, y )
% scale vector x with scalar alpha, and then added it to vector y

[ m_x, n_x ] = size( x );
[ m_y, n_y ] = size( y );

if ~isscalar( alpha )
    y_out = 'FAILED';
    return
end

if ~( isvector( x ) && isvector( y ) )
    y_out = 'FAILED';
    return
end

if m_x * n_x ~= m_y * n_y
    y_out = 'FAILED';
    return
end

y_out = zeros( m_y, n_y );

% x is row vector
if m_x == 1
    % y is row vector
    if m_y == 1
        for i=1:n_x
            y_out( 1, i ) = alpha * x( 1, i ) + y( 1, i );
        end
    % y is column vector    
    else
        for i=1:n_x
            y_out( i, 1 ) = alpha * x( 1, i ) + y( i, 1 );
        end
    end
% x is column vector    
else
    % y is row vector
    if m_y == 1
        for i=1:m_x
            y_out( 1, i ) = alpha * x( i, 1 ) + y( 1, i );
        end
    % y is column vector    
    else
        for i=1:m_x
            y_out( i, 1 ) = alpha * x( i, 1 ) + y( i, 1 );
        end
    end
end

return
end

