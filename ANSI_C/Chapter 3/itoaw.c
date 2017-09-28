#include <stdio.h>
#include <string.h>

#define MAX    20

void itoa(int n, char s[], int width);
void reverse(char s[]);

int main(void)
{
    int n, width;
    char s[MAX];

    scanf("%d%d", &n, &width);
    itoa(n , s, width);
    puts(s);
    return 0;
}

void itoa(int n, char s[], int width)
{
    int i, sign, d;

    sign = (n < 0) ? -1 : 1;
    i = 0;
    do {
	s[i++] = n % 10 * sign + '0';
    } while ((n /= 10) != 0);
    if (sign < 0)
	s[i++] = '-';
    for (; i < width; s[i++] = ' ')
	;
    s[i] = '\0';
    reverse(s);
}

void reverse(char s[])
{
    int i, j, temp;

    for (i = 0, j = strlen(s)-1; i < j; i++, j--) {
	temp = s[i];
	s[i] = s[j];
	s[j] = temp;
    }
}
