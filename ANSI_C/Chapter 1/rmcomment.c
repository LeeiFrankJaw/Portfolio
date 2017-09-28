#include <stdio.h>

#define MAX        4
#define TABWITDH   8

enum {NORMAL, CHAR, STRING, COMMENT, ERROR} state;

int col, ln;
int esc;

void position(char c);
void check(char cs[]);
void toggle_char(char cs[]);
void toggle_string(char cs[]);
void toggle_esc(char cs[]);
void toggle_comment(char cs[]);
void update(char cs[], int inc);
int isoct(char c);
int ishex(char c);
void print_pos(void);
void print_state(void);

int main(void)
{
    char cs[MAX];
    int i;

    col = esc = 0;
    ln = 1;
    state = NORMAL;
    for (i = 0; i < MAX; ++i)
	cs[i] = getchar();

    while (cs[0] != EOF && state != ERROR) {
	position(cs[0]);
	check(cs);
	update(cs, 1);
    }
    return 0;
}

void position(char c)
{
    switch (c) {
    case '\t':
	col += TABWITDH - col % TABWITDH;
	break;
    case '\n':
	col = 0;
	++ln;
	break;
    default:
	++col;
	break;
    }
}

void check(char cs[])
{
    switch (cs[0]) {
    case '\'':
	toggle_char(cs);
	break;
    case '\"':
	toggle_string(cs);
	break;
    case '\\':
	toggle_esc(cs);
	break;
    case '/': case '*':
	toggle_comment(cs);
	break;
    default:
    if (state != COMMENT)
	putchar(cs[0]);
    }
    if (esc > 0)
	esc--;
}

void toggle_char(char cs[])
{
    switch (state) {
    case NORMAL:
	state = CHAR;
	break;
    case CHAR:
	if (esc == 0)
	    state = NORMAL;
	break;
    }
    if (state != COMMENT)
	putchar(cs[0]);
}

void toggle_string(char cs[])
{
    switch (state) {
    case NORMAL:
	state = STRING;
	break;
    case STRING:
	if (esc == 0)
	    state = NORMAL;
	break;
    }
    if (state != COMMENT)
	putchar(cs[0]);
}

void toggle_esc(char cs[])
{
    switch (state) {
    case CHAR:
	if (esc == 0)
	    if (isoct(cs[1]) && isoct(cs[2]) && isoct(cs[3])) {
		esc = 4;
	    } else if (cs[1] == 'x' && ishex(cs[2]) && ishex(cs[3])) {
		esc = 4;
	    } else {
		esc = 2;
	    }
	break;
    case STRING:
	if (esc == 0)
	    if (isoct(cs[1]) && isoct(cs[2]) && isoct(cs[3])) {
		esc = 4;
	    } else if (cs[1] == 'x' && ishex(cs[2]) && ishex(cs[3])) {
		esc = 4;
	    } else {
		esc = 2;
	    }
	break;
    }
    if (state != COMMENT)
	putchar(cs[0]);
}

void toggle_comment(char cs[])
{
    switch (state) {
    case NORMAL:
	if (cs[0] == '/' && cs[1] == '*') {
	    update(cs, 1);
	    col++;
	    state = COMMENT;
	} else
	    putchar(cs[0]);
	break;
    case COMMENT:
	if (cs[0] == '*' && cs[1] == '/') {
	    update(cs, 1);
	    col++;
	    state = NORMAL;
	}
	break;
    default:
	putchar(cs[0]);
	break;
    }
}

void update(char cs[], int inc)
{
    int i;

    for (i = 0; i < MAX-inc; ++i)
	cs[i] = cs[i+inc];
    for (i = 0; i < inc; ++i)
	cs[MAX-inc+i] = getchar();
}

int isoct(char c)
{
    return c >= '0' && c <= '7';
}

int ishex(char c)
{
    return (c >= '0' && c <= '9'
	    || c >= 'A' && c <= 'Z'
	    || c >= 'a' && c <= 'z');
}

void print_pos(void)
{
    printf("Ln %d, Col %d:\n", ln, col);
}

void print_state(void)
{
    printf("state = %d\n", state);
}
