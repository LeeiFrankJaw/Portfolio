#include <stdio.h>

#define TABWIDTH 8

void prntspc(int n);

main()
{
    int c, col, inc;

    col = 0;
    while ((c = getchar()) != EOF) {
	if (c == '\t') {
	    inc = TABWIDTH - col % TABWIDTH;
	    prntspc(inc);
	    col += inc;
	} else {
	    putchar(c);
	    if (c == '\n')
		col = 0;
	    else
		++col;
	}
    }
    return 0;
}

void prntspc(int n)
{
    for (; n > 0; --n)
	printf(" ");
}
