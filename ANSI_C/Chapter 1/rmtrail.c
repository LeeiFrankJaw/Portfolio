#include <stdio.h>
#include <stdlib.h>

#define MAX     1000
#define TRAIL   0
#define NOTRAIL 1
#define EXCEED  2

int findn(char line[], int max);
void writeln(char line[], int n);

main()
{
    int state, c, exceed;
    char line[MAX];

    exceed = ~EXCEED;
    while ((c = getchar()) != EOF) {
	if (c == ' ' || c == '\t') {
	    line[0] = c;
	    while ((state = findn(line, MAX)) == EXCEED) {
		exceed = EXCEED;
		line[0] = getchar();
	    }
	    if (exceed == EXCEED && state == NOTRAIL)
		return EXCEED;
	    exceed = ~EXCEED;
	} else
	    putchar(c);
    }

    return 0;
}

int findn(char line[], int max)
{
    int i;

    i = 1;
    line[i] = getchar();
    while (i < max && line[i] != EOF && line[i] != '\n') {
	if (line[i] != ' ' && line[i] != '\t') {
	    writeln(line, i+1);
	    return NOTRAIL;
	}
	++i;
	if (i < max)
	    line[i] = getchar();
    }

    if (i == MAX) {
	return EXCEED;
    }
    if (i == EOF)
	return EOF;
    printf("\n");
    return TRAIL;
}

void writeln(char line[], int lim)
{
    int i;

    for (i = 0; i < lim; ++i)
	putchar(line[i]);
}
