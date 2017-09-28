#include <stdio.h>

unsigned setbits(unsigned x, int p, int n, unsigned y);

main()
{
    unsigned x = 0xf0f0;
    unsigned y = 0x7;
    int p = 4;
    int n = 4;

    printf("%x\n", setbits(x, p, n, y));
}

unsigned setbits(unsigned x, int p, int n, unsigned y)
{
    unsigned SET_OFF = ~(~(~0 << n) << p+1-n);
    unsigned SET_ON = (y & ~(~0 << n)) << p+1-n;

    return x & SET_OFF | SET_ON;
}
