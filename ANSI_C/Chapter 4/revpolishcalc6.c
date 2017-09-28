#include <stdio.h>
#include <stdlib.h>		/* for atof() */
#include <math.h>		/* for fmod() */
#include <string.h>

#define MAXOP	100		/* max size of operand or operator */
#define MAXVAR	50		/* maximum number of variable */
#define HIST	'H'		/* signal that a history was found */
#define NUMBER	'0'		/* signal that a number was found */
#define LITERAL	'L'		/* signal that a literal was found */
#define VAR	'V'		/* signal that a variable was found */
#define VARLEN  20		/* maximum variable name length */

int getop(char []);
void eval(char []);
void push(double);
double pop(void);
double getvar(char [], int []);
int setvar(char [], double);
void resetvar(int, double);

/* reverse Polish calculator */
int main(void)
{
    int type;		/* current type */
    int last;		/* last type */
    int index[1];	/* index of last variable */
    double op2;
    char s[MAXOP];

    while ((type = getop(s)) != EOF) {
	switch (type) {
	case NUMBER:
	    push(atof(s));
	    break;
	case LITERAL:
	    eval(s);
	    break;
	case VAR:
	    push(getvar(s, index));
	    break;
	case '+':
	    push(pop() + pop());
	    break;
	case '*':
	    push(pop() * pop());
	    break;
	case '-':
	    op2 = pop();
	    push(pop() - op2);
	    break;
	case '/':
	    op2 = pop();
	    if (op2 != 0.0)
		push(pop() / op2);
	    else
		printf("error: zero divisor\n");
	    break;
	case '%':
	    op2 = pop();
	    if (op2 != 0.0)
		push(fmod(pop(), op2));
	    else
		printf("error: zero divisor\n");
	    break;
	case '=':
	    if (last == VAR)
		if (index[0] == MAXVAR+1)
		    printf("error: the varialbe name 'last' is reserverd\n");
		else {
		    pop();
		    op2 = pop();
		    resetvar(index[0], op2);
		    push(op2);
		}
	    else
		printf("error: = must be used after a variable\n");
	    break;
	case '\n':
	    resetvar(MAXVAR+1, op2 = pop());
	    printf("\t%.8g\n", op2);
	    break;
	default:
	    printf("error: unknown command %s\n", s);
	    break;
	}
	last = type;
    }
    return 0;
}

#define MAXVAL	100		/* maximum depth of val stack */

int sp = 0;			/* next free stack position */
int vp = 0;			/* next free variable position */
double val[MAXVAL];		/* value stack */
double vars[MAXVAR+1];		/* array of variables */
char vindex[MAXVAR][VARLEN];	/* index to find the variable */

/* push: push f onto value stack */
void push(double f)
{
    if (sp < MAXVAL)
	val[sp++] = f;
    else
	printf("error: stack full, can't push %g\n", f);
}

/* pop: pop and return top value from stack */
double pop(void)
{
    if (sp > 0)
	return val[--sp];
    else {
	printf("error: stack empty\n");
	return 0.0;
    }
}

void eval(char s[])
{
    double op1, op2;
    int tolower(int);
    int i;

    for (i = 0; s[i] != '\0'; i++)
	s[i] = tolower(s[i]);

    if (strcmp(s, "sin") == 0)
	push(sin(pop()));
    else if (strcmp(s, "exp") == 0)
	push(exp(pop()));
    else if (strcmp(s, "pow") == 0) {
	op2 = pop();
	push(pow(pop(), op2));
    } else if (strcmp(s, "c") == 0)
	sp = 0;
    else if (strcmp(s, "cv") == 0)
	vp = 0;
    else if (strcmp(s, "d") == 0) {
	op2 = pop();
	push(op2);
	push(op2);
    } else if (strcmp(s, "s") == 0) {
	op1 = pop();
	op2 = pop();
	push(op1);
	push(op2);
    } else if (strcmp(s, "p") == 0) {
	printf("\t%.8g\n", op2 = pop());
	push(op2);
	resetvar(MAXVAR+1, op2);
    } else
	printf("error, unrecognized routine %s\n", s);
}

int find(char s[])
{
    int high, low, mid, len;

    if ((len = strlen(s)) == 0)
	printf("error: variable name can't be empty\n");
    else if (len > VARLEN)
	printf("error: variable name can't be longer than %d\n", VARLEN);
    else if (strcmp(s, "last") == 0)
	return MAXVAR+1;
    else {
	high = vp - 1;
	low = 0;
	while (low <= high && strcmp(s, vindex[mid = (low+high) / 2]) != 0) {
	    if (strcmp(s, vindex[mid]) < 0)
		high = mid - 1;
	    else
		low = mid + 1;
	}
	if (strcmp(s, vindex[mid]) == 0)
	    return mid;
    }
    return -1;
}

double getvar(char s[], int index[])
{
    index[0] = find(s);
    if (index[0] == -1) {
	index[0] = setvar(s, 0.0);
	return 0.0;
    } else
	return vars[index[0]];
}

void resetvar(int i, double val)
{
    if (i != -1)
	vars[i] = val;
}

int setvar(char s[], double val)
{
    int i;

    if (vp >= MAXVAR) {
	printf("error: this little program can only hold %d variables\n",
	       MAXVAR);
	return -1;
    } else {
	for (i = vp-1; i >= 0 && strcmp(vindex[i], s) > 0; i--) {
	    strcpy(vindex[i+1], vindex[i]);
	    vars[i+1] = vars[i];
	}
	strcpy(vindex[i+1], s);
	vars[i+1] = val;
	vp++;
	return i+1;
    }
}

#include <ctype.h>

int getch(void);
void ungetch(int c);

/* getop: get next operator or numeric operand */
int getop(char s[])
{
    int i, c;

    while ((s[0] = c = getch()) == ' ' || c == '\t')
	;
    s[1] = '\0';

    /* neither a number, a history nor a literal */
    if (!isdigit(c) && !isalpha(c) && c != '.' && c != '-' && c != '+'
	&& c != '$')
	    return c;
    i = 0;
    if (c == '-' || c == '+') {
	s[++i] = c = getch();
	if (!isdigit(c) && c != '.') {
	    ungetch(c);
	    return s[0];
	}
    }

    /* it is a number */
    if (isdigit(c) || c == '.') {

	/* collect integer part */
	if (isdigit(c))
	    while (isdigit(s[++i] = c = getch()))
	    ;

	/* collect fraction part */
	if (c == '.')
	    while (isdigit(s[++i] = c = getch()))
		;
	if (c != EOF)
	    ungetch(c);
	s[i] = '\0';
	return NUMBER;
    }

    /* it is a variable */
    if (c == '$') {
	s[0] = c = getch();
	i = 0;
	if (isalpha(c) || c == '_')
	    while (isalpha(s[++i] = c = getch()) || isdigit(c))
		;
	s[i] = '\0';
	if (c != EOF)
	    ungetch(c);
	return VAR;
    }

    /* it is a literal */
    if (isalpha(c))
	while (isalpha(s[++i] = c = getch()) || isdigit(c))
	    ;
    if (c != EOF)
	ungetch(c);
    s[i] = '\0';
    return LITERAL;
}

#define BUFSIZE 100

char buf[BUFSIZE];		/* buffer for ungetch */
int  bufp = 0;			/* next free position in buf */

/* getch: get a (possibly pushed back) character */
int getch(void)
{
    return (bufp > 0) ? buf[--bufp] : getchar();
}

/* ungetch: push character back on input */
void ungetch(int c)
{
    if (bufp < BUFSIZE)
	buf[bufp++] = c;
    else
	printf("ungetch: too many characters\n");
}
