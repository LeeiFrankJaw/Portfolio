#include <stdio.h>

#define IN  1    /* inside a word */
#define OUT 0    /* outside a word */

/* count lines, words, and characters in input */
main()
{
    int c, state;

    state = OUT;
    while ((c = getchar()) != EOF) {
	if ((c == ' ' || c == '\n' || c == '\t') && state == IN) {
	    state = OUT;
	    putchar('\n');
	} else if (state == OUT)
	    state = IN;
	if (state == IN)
	    putchar(c);
    }
}
