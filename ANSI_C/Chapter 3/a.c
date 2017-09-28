#include <stdio.h>
#include <limits.h>

main()
{
    int i = 0;

    for (i = 0; i < 5; i++) {
	printf("%d\n", i);
	if (i == 100)
	    goto out;
    }

    printf("xixi\n");
out:
    printf("out:\n");

    printf("hehe:\n");
}
