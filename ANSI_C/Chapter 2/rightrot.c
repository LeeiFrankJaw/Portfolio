#include <stdio.h>

unsigned rightrot(unsigned x, int n);
int bitsize(void);

main()
{
    unsigned x = 0xff;
    int n = 4;
    printf("%x\n", rightrot(x, n));
}

unsigned rightrot(unsigned x, int n)
{
    int i;
    unsigned b;

    for (i = 0; i < n; x = x >> 1 | b, ++i)
	b = (x & 01) << bitsize()-1;
    return x;
}

int bitsize(void)
{
    int n;
    unsigned x = (unsigned) ~0;

    for (n = 0; x != 0; x = x >> 1, n++)
	;
    return n;
}
