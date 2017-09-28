#include <stdio.h>

/* Verify the expression getchar() != EOF */
main()
{
    int c;

    while ((c = getchar()) != EOF)
	printf("%d", c != EOF);
    printf("%d\n", c != EOF);
}
