#include <stdio.h>

#define MAX   1000

int getline(char s[], int lim);

int main(void)
{
    int len, i;
    char s[MAX];

    while ((len = getline(s, MAX)) > 0) {
	if (s[len-1] != '\n')
	    printf("%s", s);
	else {
	    for (i = len-2; i >= 0 && (s[i] == ' ' || s[i] == '\t'); i--)
		;
	    if (i < 0)
		s[i+1] = '\0';
	    else {
		s[i+1] = '\n';
		s[i+2] = '\0';
	    }
	    printf("%s", s);
	}
    }    
    return 0;
}

int getline(char s[], int lim)
{
    int c, i;

    for (i = 0; i < lim-1 && ((c=getchar()) != EOF) && c != '\n'; s[i++] = c)
	;
    if (c == '\n')
	s[i++] = c;
    s[i] = '\0';
    return i;
}
