#include <stdio.h>
#include <math.h>

#define MAX 128  /* the maximum nmber of possible characters */

/* Print the histogram of the frequencies of different characters
   in the input */
main()
{
    int c, d, i, j, max;
    int cs[MAX];
    int vs[MAX]; /* vs is a subset of cs, which has no zero value */
    int dmax;    /* total digits of max */

    max = 0;
    for (i = 0; i < MAX; ++i)
	cs[i] = vs[i] = 0;

    while ((c = getchar()) != EOF)
	++cs[c];

    for (i = 0, j = 0; i < MAX; ++i)
	if (cs[i] != 0) {
	    vs[j] = i;
	    ++j;
	    if (cs[i] > max)
		max = cs[i];
	}

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

    printf("   .");
    for (i = 1; i <= max+5; ++i)
	printf("-");
    printf("\n");

    for (i = 0; cs[vs[i]] != 0; ++i) {
	switch (vs[i]) {
	case ' ':
	    printf("SPC|");
	    break;
	case '\b':
	    printf("DEL|");
	    break;
	case '\n':
	    printf("RET|");
	    break;
	case '\t':
	    printf("TAB|");
	    break;
	default:
	    printf("  %c|", vs[i]);
	}
	for (j = 0; j < cs[vs[i]]; ++j)
	    printf("*");
	printf("\n");
    }
}
