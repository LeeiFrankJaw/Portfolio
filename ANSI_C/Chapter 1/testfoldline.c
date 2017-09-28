#include <stdio.h>

#define MAXLINE 70
#define TABWIDTH 8

main()
{
    int c, col, row;

    col = row = 0;
    while ((c = getchar()) != EOF) {
	switch (c) {
	case '\n':
	    if (col > MAXLINE)
		printf("Error occurs at line %d.\n", row+1);
	    col = 0;
	    ++row;
	    break;
	case '\t':
	    col += TABWIDTH - col % TABWIDTH;
	    break;
	default:
	    ++col;
	    break;
	}
    }
    return 0;
}
