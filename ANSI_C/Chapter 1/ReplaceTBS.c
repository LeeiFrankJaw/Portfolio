#include <stdio.h>

/* Copy the input to output, replacing each tab with \t, each
 * backspace by \b, and each backslash by \\ */
main()
{
    int c;

    while ((c = getchar()) != EOF)
	switch (c) {
	case '\t':
	    printf("\\t");
	    break;
	case '\b':
	    printf("\\b");
	    break;
	case '\\':
	    printf("\\\\");
	    break;
	default:
	    putchar(c);
	    break;
	}
}
