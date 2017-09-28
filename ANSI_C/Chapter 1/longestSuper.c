#include <stdio.h>

#define MAXLINE 1000     /* maximum input line size */

int getline(char line[], int maxline);
void copy(char to[], char from[]);

/* print longest input line; super version that take arbitrarily long
 * input lines */
main()
{
    int len;            /* current line length */
    int tmplen;         /* temporary variable for building up len */
    int max;            /* maximum length seen so far */
    char line[MAXLINE];     /* current input line */
    char tmpline[MAXLINE];  /* temporary char array if necessary */
    char longestHead[MAXLINE];  /* head of longest line */
    char longestTail[MAXLINE];  /* tail of longest line */
    int iHead, iTail;   /* maximum index of longestHead and longestTail */

    max = len = 0;
    len = tmplen = getline(tmpline, MAXLINE);
    while (len > 0) {
	copy(line, tmpline);
	while (tmpline[tmplen-1] != '\n') {
	    if (tmplen == 0)
		break;
	    tmplen = getline(tmpline, MAXLINE);
	    len += tmplen;
	}
	if (len > max) {
	    max = len;
	    copy(longestHead, line);
	    iTail = tmplen;
	    if (max >= MAXLINE) {
		iHead = MAXLINE - 1;
		copy(longestTail, tmpline);
	    } else
		iHead = max;
	}
	if (tmplen == 0)
	    break;
	len = tmplen = getline(tmpline, MAXLINE);
    }
    printf("longest line:\n");
    if (max > 0)    /* there was a line */
	printf("%s", longestHead);
    if (max >= MAXLINE) {
	if (max > iHead + iTail)
	    printf(" ... ");
	printf("%s", longestTail);
    }
    if (iTail == 0)
	printf("\n");
    printf("length = %d\n", max);
    return 0;
}

/* getline: read a line into s, return length */
int getline(char s[], int lim)
{
    int c, i;

    for (i = 0; i<lim-1 && (c=getchar())!=EOF && c!='\n'; ++i)
	s[i] = c;
    if (c == '\n') {
	s[i] = c;
	++i;
    }
    s[i] = '\0';
    return i;
}

/* copy: copy 'from' into 'to'; assume to is big enough */
void copy(char to[], char from[])
{
    int i;

    i = 0;
    while ((to[i] = from[i]) != '\0')
	++i;
}
