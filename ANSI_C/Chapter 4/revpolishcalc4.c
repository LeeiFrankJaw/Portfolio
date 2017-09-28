#include <stdio.h>
#include <stdlib.h>		/* for atof() */
#include <math.h>		/* for fmod() */

#define MAXOP   100		/* max size of operand or operator */
#define NUMBER  '0'		/* signal that a number was found */

int getop(char []);
void push(double);
double pop(void);
void clear(void);
void dup(void);
void swap(void);
double top(void);

/* reverse Polish calculator */
main()
{
    int type;
    double op2;
    char s[MAXOP];

    while ((type = getop(s)) != EOF) {
	switch (type) {
	case NUMBER:
	    push(atof(s));
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
	case 'c': case 'C':
	    clear();
	    break;
	case 'd': case 'D':
	    dup();
	    break;
	case 'p': case 'P':
	    printf("\t%.8g\n", top());
	    break;
	case 's': case 'S':
	    swap();
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

void clear(void)
{
    sp = 0;
}

double top(void)
{
    if (sp > 0)
	return val[sp-1];
    else {
	printf("error: stack empty\n");
	return 0.0;
    }
}

void dup(void)
{
    if (sp < MAXVAL)
	if (sp > 0) {
	    val[sp] = val[sp-1];
	    sp++;
	} else
	    printf("error: stack empty, can't duplicate\n");
    else
	printf("error: stack full, can't duplicate\n");
}

void swap(void)
{
    double temp;

    if (sp > 1) {
	temp = val[sp-2];
	val[sp-2] = val[sp-1];
	val[sp-1] = temp;
    } else
	printf("error: too few elements in stack, can't swap\n");
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
    if (!isdigit(c) && c != '.' && c != '-' && c != '+')
	    return c;	/* not a number */
    i = 0;
    if (c == '-' || c == '+') {
	s[++i] = c = getch();
	if (!isdigit(c) && c != '.') {
	    ungetch(c);
	    s[i] = '\0';
	    return s[0];
	}
    }
    if (isdigit(c))		/* collect integer part */
	while (isdigit(s[++i] = c = getch()))
	    ;
    if (c == '.')		/* collect fraction part */
	while (isdigit(s[++i] = c = getch()))
	    ;
    if (c != EOF)
	ungetch(c);
    s[i] = '\0';
    return NUMBER;
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
