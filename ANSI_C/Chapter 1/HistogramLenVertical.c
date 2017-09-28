#include <stdio.h>
#include <string.h>
#include <math.h>

#define IN  1    /* inside a word */
#define OUT 0    /* outside a word */
#define MAX 50   /* the maximum of possible word length */

/* Print a histogram of the lengths of words from the input;
   vertical version */
main()
{
    int c, state, len, i, j;
    int nlen[MAX];
    int min;     /* minimum index of nlen that is nonzero */
    int max;     /* maximum in nlen*/
    int dmax;    /* total digits of max */
    char fmt1[10] = "%", fmt2[10];

    for (i = 0; i < MAX; ++i)
	nlen[i] = 0;

    state = OUT;
    len = max = 0;
    while ((c = getchar()) != EOF)
	if (c == ' ' || c == '\n' || c == '\t') {
	    if (state == IN) {
		++nlen[len];
		state = OUT;
		len = -1;
	    }
	} else {
	    ++len;
	    state = IN;
	}

    for (min = MAX-1; nlen[min] == 0; --min)
	;

    for (i = 0; i <= min; ++i)
	if (nlen[i] > max)
	    max = nlen[i];

    dmax = 1 + (int) log10(max);
    sprintf(fmt2, "%d", dmax+1);
    strcat(fmt1, fmt2);
    strcat(fmt1, "d|");

    for (i = 0; i < dmax+1; ++i)
	fmt2[i] = ' ';
    fmt2[i] = '|';
    fmt2[i+1] = '\0';

    for (i = max+5; i >= 1; --i) {
	if (i <= max) {
	    printf(fmt1, i);
	    for (j = 0; j <= min; ++j)
		if (nlen[j] >= i)
		    printf("  *");
		else
		    printf("   ");
	} else
	    printf(fmt2);
	printf("\n");
    }

    printf("   .");
    for (i = 0; i <= min; ++i)
	printf("---");
    printf("\n");

    printf("    ");
    for (i = 0; i <= min; ++i)
	printf("%3d", i+1);
    printf("\n");
}
