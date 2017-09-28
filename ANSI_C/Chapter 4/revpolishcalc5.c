#include <stdio.h>
#include <stdlib.h>		/* for atof() */
#include <math.h>		/* for fmod() */

#define MAXOP   100		/* max size of operand or operator */
#define NUMBER  '0'		/* signal that a number was found */
#define LITERAL 'l'		/* signal that a literal was found */

int getop(char []);
void eval(char []);
void push(double);
double pop(void);

/* reverse Polish calculator */
int main(void)
{
    int type;
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
	case '\n':
	    printf("\t%.8g\n", pop());
	    break;
	default:
	    printf("error: unknown command %s\n", s);
	    break;
	}
    }
    return 0;
}

#define MAXVAL  100		/* maximum depth of val stack */

int sp = 0;			/* next free stack position */
double val[MAXVAL];		/* value stack */

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

#include <string.h>

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
    } else
	printf("error, unrecognized function %s\n", s);
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
    if (!isdigit(c) && !isalpha(c) && c != '.' && c != '-' && c != '+')
	    return c;	/* neither a number nor a literal */
    i = 0;
    if (c == '-' || c == '+') {
	c = getch();
	if (!isdigit(c) && c != '.') {
	    ungetch(c);
	    return s[0];
	}
	s[++i] = c;
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
