function [ y_out ] = laff_copy( x, y )
% copy vector y into x

%   get the sizes of x and y vector
[ m_x, n_x ] = size( x );
[ m_y, n_y ] = size( y );

%   check if both x and y are vectors
if ( m_x ~= 1 && n_x ~= 1 ) || ( m_y ~= 1 && n_y ~= 1)
    y_out = 'FAILED';
    return
end

%   check if both x and y have the same size
if ( m_x * n_x ~= m_y * n_y )
    y_out = 'FAILED';
    return
end

%   create output vector with the same shape as vector y
y_out = zeros( m_y, n_y );

% x is row vector
if m_x == 1
    % y is row vector
    if m_y == 1
        for i=1:n_x
            y_out( 1, i ) = x( 1, i );
        end
    % y is column vector
    else 
        for i=1:n_x
            y_out( i, 1 ) = x( 1, i );
        end
    end
% x is column vector    
else
    % y is row vector
    if m_y == 1
        for i=1:m_x
            y_out( 1, i ) = x( i, 1 );
        end
    % y is column vector    
    else
        for i=1:m_x
            y_out( i, 1 ) = x( i, 1 );
        end
    end
end

return
end

