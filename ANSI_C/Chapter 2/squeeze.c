#include <stdio.h>

#define MAX   1000

void squeeze(char s1[], char s2[]);
int cinstr(char c, char str[]);
void getstr(char str[], int maxlen);

main()
{
    char s1[MAX];
    char s2[MAX];

    getstr(s1, MAX);
    getstr(s2, MAX);
    squeeze(s1, s2);
    printf("%s\n", s1);
}

void squeeze(char s1[], char s2[])
{
    int i, j;

    for (i = j = 0; s1[i] != '\0'; i++)
	if (!cinstr(s1[i], s2))
	    s1[j++] = s1[i];
    s1[j] = '\0';
}

/* cinstr: return non-zero if c is in s; otherwise return zero */
int cinstr(char c, char s[])
{
    int in = 0;
    int i;

    for (i = 0; !in && s[i] != '\0'; ++i)
	in = c == s[i];
    return in;
}

void getstr(char s[], int lim)
{
    int i;

    for (i = 0; i < lim && (s[i] = getchar()) != '\n'; ++i)
	;
    s[i] = '\0';
}
