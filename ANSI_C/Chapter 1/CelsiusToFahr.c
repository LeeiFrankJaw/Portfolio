#include <stdio.h>

#define   LOWER  -20     /* lower limit of table */
#define   UPPER  100     /* upper limit */
#define   STEP   10      /* step size */

/* print Fahrenheit-Celsius table */
main()
{
    int celsius;

    for (celsius = LOWER; celsius <= UPPER; celsius = celsius + STEP)
	printf("%3d %6.1f\n", celsius, (9.0/5.0) * celsius - 32);
}
