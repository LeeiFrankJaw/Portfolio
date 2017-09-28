#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX    100

void init(int v[], int n);
void printv(int v[], int n);
void check(int v[], int n);
void shellsort(int v[], int n);

int main(void)
{
    int v[MAX];

    init(v, MAX);
    shellsort(v, MAX);
    check(v, MAX);
    putchar('\n');
    printv(v, MAX);
    return 0;
}

void init(int v[], int n)
{
    int i;

    srand(time(NULL));
    for (i = 0; i < n; i++)
	v[i] = rand() % 100000;
}

void printv(int v[], int n)
{
    int i;

    for (i = 0; i < n; i++)
	printf("%6d", v[i]);
}

void check(int v[], int n)
{
    int i;

    for (i = n-1; i > 0; --i)
	if (v[i-1] > v[i]) {
	    printf("The array was not properly sorted.");
	    return;
	}
    printf("The array was properly sorted.");
}

void shellsort(int v[], int n)
{
    int gap, i, j, temp;

    for (gap = n/2; gap > 0; gap /= 2)
	for (i = gap; i < n; i++)
	    for (j=i-gap; j>=0 && v[j]>v[j+gap]; j-=gap) {
		temp = v[j];
		v[j] = v[j+gap];
		v[j+gap] = temp;
	    }
}
