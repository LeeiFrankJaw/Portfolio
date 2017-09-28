#include <stdio.h>

main()
{
    f = -0.0;
    printf("char_max = %d\n", (char) ((unsigned char) ~0 >> 1));
    printf("char_min = %d\n", ~(char) ((unsigned char) ~0 >> 1));
    printf("signed_char_max = %d\n", (signed char) ((unsigned char) ~0 >> 1));
    printf("signed_char_min = %d\n", ~(signed char) ((unsigned char) ~0 >> 1));
    printf("unsigned_char_max = %u\n", (unsigned char) ~0);
    printf("short_max = %d\n", (short) ((unsigned short) ~0 >> 1));
    printf("short_min = %d\n", ~(short) ((unsigned short) ~0 >> 1));
    printf("unsigned_short_max = %u\n", (unsigned short) ~0);
    printf("int_max = %d\n", (int) ((unsigned int) ~0 >> 1));
    printf("int_min = %d\n", ~(int) ((unsigned int) ~0 >> 1));
    printf("unsigned_int_max = %u\n", (unsigned int) ~0);
    printf("long_max = %d\n", (long) ((unsigned long) ~0 >> 1));
    printf("long_min = %d\n", ~((long) ((unsigned long) ~0 >> 1)));
    printf("unsigned_long_max = %u\n",(unsigned long) ~0);
}
