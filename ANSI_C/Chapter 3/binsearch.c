#include <stdio.h>
#include <time.h>
#include <limits.h>

#define MAX    SHRT_MAX+1

int get_ints(int v[], int maxlength);
void print_ints(int v[], int length);
int binsearch(int x, int v[], int length);

int main(void)
{
    int v[MAX];
    int n, x, i;
    clock_t start, end;
    double cpu_time_used;

    scanf("%d", &x);
    n = get_ints(v, MAX);

    start = clock();
    i = binsearch(x, v, n);
    end = clock();
    cpu_time_used = ((double) (end - start) / CLOCKS_PER_SEC);

    printf("%d\n%f\n", i, cpu_time_used);
}

int get_ints(int v[], int lim)
{
    int i;

    for (i = 0; i < lim && scanf("%d", &v[i]) != EOF; ++i)
	;
    if (i == lim)
	printf("Too many integers! Only the first %d integers was taken.\n",
	       MAX);
    return i;
}

void print_ints(int v[], int n)
{
    int i;

    for (i = 0; i < n; ++i)
	printf("%6d", v[i]);
    putchar('\n');
}

int binsearch(int x, int v[], int n)
{
    int low, high, mid;

    low = 0;
    high = n - 1;
    while (low <= high && x != v[mid = (low+high) / 2])
	if (x < v[mid])
	    high = mid -1;
	else
	    low = mid + 1;
    if (x == v[mid])
	return mid;
    return -1;
}
