#include <stdio.h>
#include <math.h>

#define IN  1    /* inside a word */
#define OUT 0    /* outside a word */
#define MAX 50   /* the maximum of possible word length */

/* Print a histogram of the lengths of words from the input */
main()
{
    int c, d, state, len, i, j;
    int nlen[MAX];
    int min;     /* minimum index of nlen that is nonzero */
    int max;     /* maximum element in nlen */
    int dmax;    /* total digits of max */

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
    for (i = 0; i < dmax; ++i) {
	printf("   ");
	for (j = 1; j <= max; ++j) {
	    d = j / (int) pow(10, dmax-i-1);
	    if (d == 0)
		printf(" ");
	    else
		printf("%d", d % 10);
	}
	printf("\n");
    }

    printf("  .");
    for (i = 0; i < max+5; ++i)
	printf("-");
    printf("\n");

    for (i = 0; i <= min; ++i) {
	printf("  |\n%2d|", i+1);
	for (j = 0; j < nlen[i]; ++j)
	    printf("*");
	printf("\n");
    }
}
