#include <stdio.h>

#define MAX        4
#define TABWITDH   8
#define STACKDEPTH 1000

enum {NORMAL, CHAR, STRING, COMMENT, ERROR} state;

/* OFCHAR:   OverFlow of CHAR
   UFCHAR:   UnderFlow of CHAR
   OFDELIM:  OverFlow of DELIM stack
   MISMATCH: delimiters MISMATCH
   NTCHAR:   Non-Terminating CHAR
   NTSTRING: Non-Terminating STRING */
enum {OFCHAR, UFCHAR, OFDELIM, MISMATCH, NTCHAR, NTSTRING};

int col, ln;    /* integers indicating current line and column number */
int esc;        /* number of char to escape from */
int nc;         /* number of char between single quotes */

void position(char c);
int push(char c, char delim[], int i);
int pop(char c, char delim[], int i);
int check(char cs[], char delim[], int i);
int matchdelim(char a, char b);
void toggle_char(char cs[]);
void toggle_string(void);
void update_esc(char cs[]);
void toggle_comment(char cs[]);
void update(char cs[], int inc);
void set_error(int error);
int isoct(char c);
int ishex(char c);
void print_pos(void);
void print_fchar(void);
void print_delim(char delim[], int n);
void print_state(void);

int main(void)
{
    char cs[MAX];                     /* array of char processed at a time */
    char delim[STACKDEPTH];           /* Stack for holding the delimiters */
    int i;                            /* index of the next available item in the delim */

    col = esc = nc = 0;
    ln = 1;
    state = NORMAL;
    for (i = 0; i < MAX; ++i)
	cs[i] = getchar();

    while (cs[0] != EOF && state != ERROR) {
	position(cs[0]);
	i = check(cs, delim, i);
	update(cs, 1);
    }

    switch (state) {
    case (CHAR):
	set_error(NTCHAR);
	break;
    case (STRING):
	set_error(NTSTRING);
	break;
    }
    if (i > 0)
	set_error(MISMATCH);
    return 0;
}

/* position: update current position, namely the ln and col. */
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

/* push: push a delimiter into stack */
int push(char c, char delim[], int i)
{
    if (i < STACKDEPTH) {
	delim[i] = c;
	++i;
    } else {
	set_error(OFDELIM);
    }
    return i;
}

/* pop: pop a delimiter out of stack */
int pop(char c, char delim[], int i)
{
    if (matchdelim(delim[i-1], c))
	--i;
    else {
	set_error(MISMATCH);
    }
    return i;
}

/* check: update and modify global variables if needed */
int check(char cs[], char delim[], int i)
{
    switch (cs[0]) {
    case '{': case '(': case '[':
	if (state == NORMAL)
	    i = push(cs[0], delim, i);
	break;
    case '}': case ')': case ']':
	if (state == NORMAL)
	    i = pop(cs[0], delim, i);
	break;
    case '\'':
	toggle_char(cs);
	break;
    case '\"':
	toggle_string();
	break;
    case '\\':
	update_esc(cs);
	break;
    case '/':
	if (cs[1] == '*')
	    toggle_comment(cs);
	break;
    case '*':
	if (cs[1] == '/') {
	    toggle_comment(cs);
	}
	break;
    }
    if (esc > 0)
	esc--;
    if (state == CHAR) {
	nc++;
    }
    return i;
}

/* print_delim: print out the delimters in the delim stack */
void print_delim(char delim[], int n)
{
    int i;
    for (i = 0; i < n; ++i)
	putchar(delim[i]);
    putchar('\n');
}

/* matchdelim: check if char a and char b are matched delimiters */
int matchdelim(char a, char b)
{
    return ((a == '{' && b == '}')
	    || (a == '(' && b == ')')
	    || (a == '[' && b == ']'));
}

/* toggle_char: toggle the state into CHAR or NORMAL depending on current state */
void toggle_char(char cs[])
{
    switch (state) {
    case NORMAL:
	state = CHAR;
	break;
    case CHAR:
	if (esc == 0) {
	    state = NORMAL;
	    if (nc < 2)
		set_error(UFCHAR);
	    if (nc > 2)
		set_error(OFCHAR);
	    nc = 0;
	}
	break;
    case COMMENT:
	break;
    case STRING:
	break;
    }
}

/* toggle_string: toggle the state into STRING or NORMAL depending on current state */
void toggle_string(void)
{
    switch (state) {
    case NORMAL:
	state = STRING;
	break;
    case STRING:
	if (esc == 0)
	    state = NORMAL;
	break;
    case COMMENT:
	break;
    case CHAR:
	break;
    }
}

/* update_esc: update esc based on cs and current state */
void update_esc(char cs[])
{
    switch (state) {
    case NORMAL:
	break;
    case CHAR:
	if (esc == 0)
	    if (isoct(cs[1]) && isoct(cs[2]) && isoct(cs[3])) {
		esc = 4;
		nc -= 3;
	    } else if (cs[1] == 'x' && ishex(cs[2]) && ishex(cs[3])) {
		esc = 4;
		nc -= 3;
	    } else {
		esc = 2;
		nc -= 1;
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
}

/* toggle_comment: toggle the state into COMMENT or NORMAL depending on current state */
void toggle_comment(char cs[])
{
    switch (state) {
    case NORMAL:
	update(cs, 1);
	col++;
	state = COMMENT;
	break;
    case COMMENT:
	update(cs, 1);
	col++;
	state = NORMAL;
	break;
    }
}

/* update: update the cs with inc steps */
void update(char cs[], int inc)
{
    int i;

    for (i = 0; i < MAX-inc; ++i)
	cs[i] = cs[i+inc];
    for (i = 0; i < inc; ++i)
	cs[MAX-inc+i] = getchar();
}

/* set_error: print out error message and set error state if necessary */
void set_error(int error)
{
    switch (error) {
    case OFCHAR:
	print_pos();
	printf("  Too many characters was given: ");
	print_fchar();
	break;
    case UFCHAR:
	print_pos();
	printf("  Too few characters was given: ");
	print_fchar();
	break;
    case OFDELIM:
	printf("Stack overflow . . .\n");
	state = ERROR;
	break;
    case MISMATCH:
	print_pos();
	printf("delimiters mismatch.\n\n");
	state = ERROR;
	break;
    case NTCHAR:
	print_pos();
	printf("  Non-terminating char: ");
	print_fchar();
	break;
    }
}

/* isoct: check if c is a valid octal digit */
int isoct(char c)
{
    return c >= '0' && c <= '7';
}

/* ishex: check if c is a valid hexadecimal digit */
int ishex(char c)
{
    return (c >= '0' && c <= '9'
	    || c >= 'A' && c <= 'Z'
	    || c >= 'a' && c <= 'z');
}

/* print_pos: print out current position */
void print_pos(void)
{
    printf("Ln %d, Col %d:\n", ln, col);
}

/* print_fchar: print out char error message */
void print_fchar(void)
{
    printf("char can only hold exactly one character at once.\n\n");
}

/* print_state: print out current state */
void print_state(void)
{
    printf("state = %d\n", state);
}
