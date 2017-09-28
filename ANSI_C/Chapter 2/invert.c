#include <stdio.h>

unsigned invert(unsigned x, int p, int n);

main()
{
    unsigned x = 0xf0f0;
    int p = 4;
    int n = 4;

    printf("%x\n", invert(x, p, n));
}

unsigned invert(unsigned x, int p, int n)
{
    unsigned INVERT = ~(~0 << n) << p+1-n;

    return x ^ INVERT;
}
