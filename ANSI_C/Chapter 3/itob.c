#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX    1000

void itob(int n, char s[], int b);
char todigit(int n);
void reverse(char s[]);

int main(void)
{
    int n, b;
    char s[MAX];

    scanf("%d\n%d", &n, &b);
    itob(n, s, b);
    printf("%s", s);
    return 0;
}

void itob(int n, char s[], int b)
{
    int i, d, sign;

    if (b < 2 || b > 36) {
	printf("b must be an integer between 2 and 36 (inclusive).");
	exit(0);
    }

    sign = (n < 0) ? -1 : 1;
    i = 0;
    do {
	s[i++] = todigit(n % b * sign);
    } while ((n /= b) != 0);
    if (sign < 0)
	s[i++] = '-';
    s[i] = '\0';
    reverse(s);
}

char todigit(int n)
{
    if (n > 36) {
	printf("n must be an integer between 0 and 36 (inclusive).");
	exit(0);
    }

    if (n >=0 && n <= 9)
	return  n + '0';

    if (n >= 10)
	return n - 10 + 'A';
}

void reverse(char s[])
{
    int c, i, j;

    for (i = 0, j = strlen(s)-1; i < j; i++, j--) {
	c = s[i];
	s[i] = s[j];
	s[j] = c;
    }
}
