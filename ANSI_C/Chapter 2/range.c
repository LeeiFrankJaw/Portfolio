#include <stdio.h>
#include <limits.h>
#include <float.h>

main()
{
    printf("char_bit = %d\n", CHAR_BIT);
    printf("char_max = %d\n", CHAR_MAX);
    printf("char_min = %d\n", CHAR_MIN);
    printf("signed_char_max = %d\n", SCHAR_MAX);
    printf("signed_char_min = %d\n", SCHAR_MIN);
    printf("unsigned_char_max = %u\n", UCHAR_MAX);
    printf("short_max = %d\n", SHRT_MAX);
    printf("short_min = %d\n", SHRT_MIN);
    printf("unsigned_short_max = %u\n", USHRT_MAX);
    printf("int_max = %d\n", INT_MAX);
    printf("int_min = %d\n", INT_MIN);
    printf("unsigned_int_max = %u\n", UINT_MAX);
    printf("long_max = %ld\n", LONG_MAX);
    printf("long_min = %ld\n", LONG_MIN);
    printf("unsigned_long_max = %lu\n", ULONG_MAX);
    printf("float_max = %e\n", FLT_MAX);
    printf("float_min = %e\n", FLT_MIN);
    printf("double_max = %e\n", DBL_MAX);
    printf("double_min = %e\n", DBL_MIN);
}
