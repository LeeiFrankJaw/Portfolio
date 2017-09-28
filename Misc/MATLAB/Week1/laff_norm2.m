function [ alpha ] = laff_norm2( x )
% calculate the length of vector x

if ~isvector( x )
    alpha = 'FAILED';
    return
end

alpha = sqrt( laff_dot( x, x ) );

return
end

