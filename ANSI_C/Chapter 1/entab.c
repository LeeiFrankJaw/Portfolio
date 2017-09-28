#include <stdio.h>

#define TABWIDTH 8

void prntspc(int n);

main()
{
    int c, col, nspc;

    col = 0;
    nspc = 0;
    while ((c = getchar()) != EOF)
	if (c == ' ') {
	    ++nspc;
	    ++col;
	    if (col % TABWIDTH == 0) {
		printf("\t");
		nspc = 0;
	    }
	} else {
	    prntspc(nspc);
	    nspc = 0;
	    putchar(c);
	    if (c == '\n')
		col = 0;
	    else
		++col;
	}
    return 0;
}

void prntspc(int n)
{
    for (; n > 0; --n)
	printf(" ");
}
