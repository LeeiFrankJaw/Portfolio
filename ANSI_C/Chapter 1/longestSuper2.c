#include <stdio.h>

#define MAX    1000

int getline(char s[], int lim);
void copy(char to[], char from[]);

int main(void)
{
    int len, tmplen;
    int max;
    char line[MAX], tmpline[MAX];
    char longest[MAX];

    max = 0;
    while ((len = getline(line, MAX)) > 0) {
	if (line[len-1] != '\n') {
	    while ((tmplen = getline(tmpline, MAX)) > 0
		   && tmpline[tmplen-1] != '\n')
		len += tmplen;
	    len += tmplen;
	}
	if (len > max) {
	    max = len;
	    copy(longest, line);
	}
    }
    if (max > 0)
	printf("length = %d\n%s", max, longest);
}

int getline(char s[], int lim)
{
    int c, i;

    for (i = 0; i < lim-1 && ((c = getchar()) != '\n') && c != EOF; s[i++] = c)
	;
    if (c == '\n')
	s[i++] = c;
    s[i] = '\0';
    return i;
}

void copy(char to[], char from[])
{
    int i;

    for (i = 0; from[i] != '\0'; to[i] = from[i], i++)
	;
    to[i] = from[i];
}
