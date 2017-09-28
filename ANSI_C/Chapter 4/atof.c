#include <stdio.h>
#include <ctype.h>

#define MAX   1000

double atof(char s[]);

int main(void)
{
    char s[MAX];

    scanf("%s", s);
    printf("%f\n", atof(s));
    return 0;
}

double atof(char s[])
{
    double val, power, dir = 10.0;
    int i, sign, exp;

    for (i = 0; isspace(s[i]); i++)
	;
    sign = (s[i] == '-') ? -1 : 1;
    if (s[i] == '+' || s[i] == '-')
	i++;
    for (val = 0.0; isdigit(s[i]); i++)
	val = 10.0 * val + (s[i] - '0');
    if (s[i] == '.')
	i++;
    for (power = 1.0; isdigit(s[i]); i++) {
	val = 10.0 * val + (s[i] - '0');
	power *= dir;
    }
    if (s[i] == 'e' || s[i] == 'E')
	i++;
    dir = (s[i] == '-') ? 10.0 : 0.1;
    if (s[i] == '-' || s[i] == '+')
	i++;
    for (exp = 0; isdigit(s[i]); i++)
	exp = 10 * exp + (s[i] - '0');
    for (i = 0; i < exp; i++)
	power *= dir;
    return sign * val / power;
}
