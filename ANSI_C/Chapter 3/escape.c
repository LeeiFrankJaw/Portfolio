#include <stdio.h>

#define MAX   1000
#define LINE  3

void escape(char s[], char t[]);
void getline(char s[]);
void concatenate(char s[], char t[]);
void enter(char s[], char t[]);

int main(void)
{
    char s[MAX], t[MAX], u[MAX];
    int i;

    getline(t);
    for (i = 1; i < LINE; ++i) {
	getline(u);
	concatenate(t, u);
    }
    escape(s, t);
    printf("\n%s\n\n", s);
    enter(u, s);
    printf("%s", u);
    return 0;
}

void escape(char s[], char t[])
{
    int i, j;

    for (i = j = 0; i < MAX && j < MAX-1 && t[i] != '\0'; ++i)
	switch (t[i]) {
	case '\n':
	    s[j++] = '\\';
	    s[j++] = 'n';
	    break;
	case '\t':
	    s[j++] = '\\';
	    s[j++] = 't';
	    break;
	default:
	    s[j++] = t[i];
	    break;
	}
    s[j] = '\0';
}

void enter(char s[], char t[])
{
    int i, j;

    for (i = j = 0; i < MAX && j < MAX-1 && t[i] != '\0'; ++i)
	switch (t[i]) {
	case '\\':
	    if (i+1 < MAX)
		switch (t[i+1]) {
		case 'n':
		    s[j++] = '\n';
		    ++i;
		    break;
		case 't':
		    s[j++] = '\t';
		    ++i;
		    break;
		default:
		    s[j++] = t[i];
		    break;
		}
	    break;
	default:
	    s[j++] = t[i];
	    break;
	}
    s[j] = '\0';
}

void getline(char s[])
{
    int c, i;

    for (i = 0; i < MAX-1 && (c = getchar()) != EOF && c != '\n'; ++i)
	s[i] = c;
    if (c == '\n') {
	s[i] = c;
	++i;
    }
    s[i] = '\0';
}

void concatenate(char s[], char t[])
{
    int i, j;

    for (j = 0; j < MAX && s[j] != '\0'; ++j)
	;

    for (i = 0; i < MAX && j < MAX-1 && t[i] != '\0'; ++i)
	s[j++] = t[i];
    s[j] = '\0';
}
