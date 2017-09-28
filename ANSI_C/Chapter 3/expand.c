#include <stdio.h>

#define MAX   1000

void expand(char s1[], char s2[]);
int getstr(char s[], int lim);
int is_upper(char c);
int is_lower(char c);
int is_digit(char c);
int is_same(char c1, char c2);

int main(void)
{
    char s1[MAX], s2[MAX];

    getstr(s1, MAX);
    expand(s1, s2);
    printf("%s\n", s2);
    return 0;
}

void expand(char s1[], char s2[])
{
    int i, j, inc, c;

    for (i = j = 0; s1[i] != '\0'; i++)
	if (i-2 >= 0 && s1[i-1] == '-' && (inc = is_same(s1[i-2], s1[i]))) {
	    for (j = j - 2, c = s1[i-2];
		 j < MAX-1 && c != s1[i];
		 c += inc)
		s2[j++] = c;
	    s2[j++] = c;
	} else
	    s2[j++] = s1[i];
    s2[j] = '\0';
}

int getstr(char s[], int lim)
{
    int c, i;

    for (i = 0; i < lim-1 && ((c = getchar()) != EOF) && c != '\n'; i++)
	s[i] = c;
    s[i] = '\0';
}

int is_upper(char c)
{
    return c >= 'A' && c <= 'Z';
}

int is_lower(char c)
{
    return c >= 'a' && c <= 'z';
}

int is_digit(char c)
{
    return c >= '0' && c <= '9';
}

int is_same(char c1, char c2)
{
    int res = (is_digit(c1) && is_digit(c2)
	       || is_upper(c1) && is_upper(c2)
	       || is_lower(c1) && is_lower(c2));
    return (c2-c1 > 0) ? res : -res;
}
