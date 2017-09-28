#include <stdio.h>

#define THRESHOLD 80

int getline(char line[], int threshold);
int printchar();

main()
{
    int len;
    char line[THRESHOLD+1];

    while ((len = getline(line, THRESHOLD)) > 0) {
	if (line[len-1] != '\n') {
	    printf("%s", line);
	    if (printchar() == EOF)
		break;
	}
    }
}

int getline(char line[], int lim)
{
    int c, i;

    i = 0;
    while (i < lim) {
	c = getchar();
	if (c != EOF && c != '\n') {
	    line[i] = c;
	    ++i;
	} else
	    break;
    }
    if (c == '\n') {
	line[i] = '\n';
	++i;
    }
    line[i] = '\0';
    return i;
}

int printchar()
{
    int c;

    while ((c = getchar()) != EOF && c != '\n')
	putchar(c);
    if (c == '\n')
	putchar(c);
    return c;
}
