#include <stdio.h>
#include <ctype.h>

#define MAX   1000

int htoi(char hex[]);
int hexdigit(char digit);
int ishex(char digit);

main()
{
    int i;
    char s[MAX];

    for (i = 0; (s[i] = getchar()) != '\n'; ++i)
	;
    s[i] = '\0';

    printf("%d\n", htoi(s));
}

int htoi(char hex[])
{
    int i, n;

    i = n = 0;
    if (hex[0] == '0' && (hex[1] == 'x' || hex[1] == 'X'))
	i = 2;

    for (; ishex(hex[i]); ++i)
	n = 16*n + hexdigit(hex[i]);

    return n;
}

/* hexdigit: return the corresponding decimal value of the hexadecimal
 * digit */
int hexdigit(char d)
{
    d = toupper(d);
    if (isdigit(d))
	return d - '0';
    else
	return d - 'A' + 10;
}

int ishex(char d)
{
    d = toupper(d);
    return isdigit(d) || d >= 'A' && d <= 'F';
}
