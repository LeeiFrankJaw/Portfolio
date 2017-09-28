#include <stdio.h>

#define IN  1    /* inside blanks */
#define OUT 0    /* outside blanks */

/* copy the input to output, replacing each string of one or more
   blanks by a single blank */
main()
{
    int c, state;

    state = OUT;
    while ((c = getchar()) != EOF)
	if (c == ' ') {
	    if (state == OUT) {
	    state = IN;
	    putchar(c);
	    }
	}
	else {
	    state = OUT;
	    putchar(c);
	}
}
