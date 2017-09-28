#include <stdio.h>

main()
{
    printf("Audible or visual alert. \a\n");
    printf("Form feed. \f\n");
    printf("This escape, \r, moves the active position to the initial position"
	   " of the current line.\n");
    printf("Vertical tab \v is tricky, as its behavior is unspecified under"
	   " certain condiions.\n");
}
