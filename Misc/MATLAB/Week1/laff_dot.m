function [ alpha ] = laff_dot( x, y )
% dot product of vector x and y

[ m_x, n_x ] = size( x );
[ m_y, n_y ] = size( y );

if ~( isvector( x ) && isvector( y ) )
    alpha = 'FAILED';
    return
end

if m_x * n_x ~= m_y * n_y
    alpha = 'FAILED';
    return
end

alpha = 0;

% x is row vector
if m_x == 1
    % y is row vector
    if m_y == 1
        for i=1:n_x
            alpha = alpha + x( 1, i ) * y( 1, i );
        end
    % y is column vector    
    else
        for i=1:n_x
            alpha = alpha + x( 1, i ) * y( i, 1 );
        end
    end
% x is column vector    
else
    % y is row vector
    if m_y == 1
        for i=1:m_x
            alpha = alpha + x( i, 1 ) * y( 1, i );
        end
    % y is column vector    
    else
        for i=1:m_x
            alpha = alpha + x( i, 1 ) * y( i, 1 );
        end
    end
end

return
end

