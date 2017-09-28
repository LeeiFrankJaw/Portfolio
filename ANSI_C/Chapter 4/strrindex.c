#include <stdio.h>
#include <string.h>

#define MAX    1000

int strrindex(char s[], char t[]);

int main(void)
{
    char s[MAX], t[MAX];

    gets(s);
    gets(t);
    printf("%d\n", strrindex(s, t));
    return 0;
}

int strrindex(char s[], char t[])
{
    int i, j, k;

    for (i = strlen(s)-1; i >= 0; i--) {
	for (j = strlen(t)-1, k = i;
	     j >= 0 && k >= 0 && t[j] == s[k];
	     j--, k--)
	    ;
	if (j < 0)
	    return k+1;
    }
    return -1;
}
