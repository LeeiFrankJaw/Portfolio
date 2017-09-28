#include <stdio.h>

#define MAX   1000

int any(char s1[], char s2[]);
int cinstr(char c, char str[]);
void getstr(char str[], int maxlen);

main()
{
    char s1[MAX];
    char s2[MAX];

    getstr(s1, MAX);
    getstr(s2, MAX);

    printf("%d\n", any(s1, s2));
}

int any(char s1[], char s2[])
{
    int i;

    for (i = 0; s1[i] != '\0'; ++i)
	if (cinstr(s1[i], s2))
	    return i;
    return -1;
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
