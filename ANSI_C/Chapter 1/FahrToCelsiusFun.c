#include <stdio.h>

#define   LOWER  0       /* lower limit of table */
#define   UPPER  300     /* upper limit */
#define   STEP   20      /* step size */

double convert(double x);

/* print Fahrenheit-Celsius table */
main()
{
    int fahr;

    printf("Fahr Celsius\n");
    for (fahr = LOWER; fahr <= UPPER; fahr = fahr + STEP)
	printf("%3d %6.1f\n", fahr, convert(fahr));
    return 0;
}

/* convert: convert temperature from fahrenheit to celsius */
double convert(double fahr)
{
    return (5.0/9.0) * (fahr-32);
}
