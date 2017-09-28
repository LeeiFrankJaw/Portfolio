#include <stdio.h>

#define MAXLINE 1000

int getline(char line[], int maxline);
void reverse(char s[]);

main()
{
    int len;
    char line[MAXLINE];

    while ((len = getline(line, MAXLINE)) > 0) {
	reverse(line);
	printf("%s", line);
    }
    return 0;
}

int getline(char s[], int lim)
{
    int c, i;

    for (i = 0; i < lim-1; ++i) {
	c = getchar();
	if (c == EOF || c == '\n')
	    break;
	s[i] = c;
    }
    if (c == '\n') {
	s[i] = '\n';
	++i;
    }
    s[i] = '\0';
    return i;
}

/* void reverse(char s[], int len) */
/* { */
/*     int i; */

/*     for (i = 0; i < len / 2; ++i) { */
/* 	s[len-i-1] ^= s[i]; */
/* 	s[i] ^= s[len-i-1]; */
/* 	s[len-i-1] ^= s[i]; */
/*     } */
/* } */

/* void reverse(char s[], int len) */
/* { */
/*     int i, temp; */

/*     for (i = len/2; i < len; ++i) { */
/* 	temp = s[len-i-1]; */
/* 	s[len-i-1] = s[i]; */
/* 	s[i] = temp; */
/*     } */
/* } */

void reverse(char s[])
{
    int i, j, temp;

    for (i = 0; s[i] != '\0'; ++i)
	;
    for (j = i-1, i = 0; i < j; ++i, --j) {
	temp = s[i];
	s[i] = s[j];
	s[j] = temp;
    }
}
