#include <stdio.h>

#define MAXLINE  70
#define OUT      0
#define IN       1
#define TABWIDTH 8

void prntret(int n);
void prntwhitespc(char whitespc[], int nret);

main()
{
    int c, col, i, j, nret, state;
    char word[MAXLINE], whitespc[MAXLINE];

    col = i = j = nret = 0;
    state = OUT;
    while ((c = getchar()) != EOF) {
	if (c != ' ' && c != '\t' && c != '\n') {
	    state = IN;
	    word[i] = c;
	    ++i;
	    ++col;
	} else {
	    word[i] = '\0';
	    if (state == IN) {
		if (col > MAXLINE) {
		    putchar('\n');
		    col = i;
		} else {
		    whitespc[j] = '\0';
		    prntwhitespc(whitespc, nret);
		}
		j = nret = 0;
	    }
	    state = OUT;
	    printf("%s", word);
	    i = 0;
	    whitespc[j] = c;
	    ++j;
	    switch (c) {
	    case ' ':
		++col;
		break;
	    case '\t':
		col += TABWIDTH - col % TABWIDTH;
		break;
	    case '\n':
		++nret;
		if (nret > 1)
		    col = 0;
		else
		    ++col;
	    }
	}	    
    }
    whitespc[j] = '\0';
    printf("%s", whitespc);
    if (i != 0) {
	word[i] = '\0';
	printf("%s", word);
    }
    return 0;
}

void prntret(int n)
{
    for (; n > 0; --n)
	printf("\n");
}

void prntwhitespc(char whitespc[], int nret)
{
    int i;

    if (nret >1)
	prntret(nret);
    else
	for (i = 0; whitespc[i] != '\0'; ++i)
	    if (whitespc[i] == '\n')
		putchar(' ');
	    else
		putchar(whitespc[i]);
}
