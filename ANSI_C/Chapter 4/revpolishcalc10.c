#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

#define MAXLINE	200
#define DELIM	" \t\n"
#define MAXVAR	50
#define VARLEN	20
#define MAXVAL	100

int getline(char []);
int gettoken(char [], char [], char [], int);
void push(double);
double pop(void);
void eval(char []);
void evalvar(char token[]);
void evalfun(char token[]);
int findvar(char []);
int newvar(char [], double);

int last = -1;			/* last variable index */
int lvsp = -1;			/* last variable position in stack */
char lvnm[VARLEN];		/* last variable name */
int vp = 0;
double vars[MAXVAR];		/* variable value stored here */
char vnames[MAXVAR][VARLEN];	/* variable name stored here */
int lindex[MAXVAR];		/* logical index of vars and vnames */
int sp = 0;
double val[MAXVAL];

int main(void)
{
    int len;			/* length of the line */
    int cur;			/* current position in the line */
    char line[MAXLINE];
    char token[MAXLINE];
    char delim[] = DELIM;

    while ((len = getline(line)) != 0) {
	cur = 0;
	while (cur < len) {
	    cur = gettoken(line, delim, token, cur);
	    if (token[0] != '\0')
	    	eval(token);
	    else
		printf("\t%.8g\n", pop());
	}
    }
    return 0;
}

int getline(char s[])
{
    int i, c;

    for (i = 0;
	 i < MAXLINE-1 && (s[i] = c = getchar()) != '\n' && c != EOF;
	 i++)
	;
    if (c == '\n')
	s[i++] = c;
    s[i] = '\0';
    return i;
}

int gettoken(char line[], char delim[], char token[], int i)
{
    int j = 0;			/* index of token */

    /* skip beginning delimiters */
    while (line[i] != '\0' && strchr(delim, line[i]) != NULL)
	i++;
    while (line[i] != '\0' && strchr(delim, line[i]) == NULL)
	token[j++] = line[i++];
    token[j] = '\0';
    return i;
}

void eval(char token[])
{
    double op2;

    if (strlen(token) == 1) {
	if (token[0] == '+')
	    push(pop() + pop());
	else if (token[0] == '-') {
	    op2 = pop();
	    push(pop() - op2);
	} else if (token[0] == '*')
	    push(pop() * pop());
	else if (token[0] == '/') {
	    op2 = pop();
	    if (op2 != 0.0)
		push(pop() / op2);
	    else
		printf("error: division by zero\n");
	} else if (token[0] == '%') {
	    op2 = pop();
	    if (op2 != 0.0)
		push(fmod(pop(), op2));
	    else
		printf("error: division by zero\n");
	} else if (token[0] == '$')
	    printf("error: variable name can't be empty\n");
	else if (isupper(token[0]))
	    evalvar(token);
	else if (islower(token[0]))
	    evalfun(token);
	else if (isdigit(token[0]))
	    push(atof(token));
	else if (token[0] == '=') {
	    if (last == -1)
		printf("error: you must specify a variable"
		       " before assigning value to it\n");
	    else if (sp - lvsp > 2)
		printf("error: ambiguous value"
		       " to be assigned to varialbe %s\n",
		       lvnm);
	    else if (sp - lvsp < 2)
		printf("error: you must specify a value"
		       " to be assigned to variable %s\n",
		       lvnm);
	    else {
		vars[last] = pop();
		pop();
		push(vars[last]);
	    }
	}
    } else
	if (isdigit(token[0]) || token[0] == '+'
	    || token[0] == '-' || token[0] == '.')
	    push(atof(token));
	else if (token[0] == '$')
	    evalvar(token);
	else if (isalpha(token[0]))
	    evalfun(token);
}

void evalvar(char token[])
{
    int i;

    if (token[0] == '$')	/* remove $ */
	for (i = 0; (token[i] = token[i+1]) != '\0'; i++)
	    ;
    i = findvar(token);
    if (i != -1)
	push(vars[i]);
    else {
	i = newvar(token, 0.0);
	push(0.0);
    }
    last = i;
    lvsp = sp - 1;
    strcpy(lvnm, token);
}

int findvar(char token[])
{
    int high = vp - 1;
    int low = 0;
    int mid;

    while (low <= high
	   && strcmp(vnames[lindex[mid = (low+high) / 2]], token) != 0)
	if (strcmp(vnames[lindex[mid]], token) < 0)
	    low = mid + 1;
	else
	    high = mid - 1;
    if (strcmp(vnames[lindex[mid]], token) == 0)
	return lindex[mid];
    return -1;
}

int newvar(char token[], double f)
{
    if (vp < MAXVAR) {
	int i = vp-1;
	while (i >= 0 && strcmp(vnames[lindex[i]], token) > 0)
	    lindex[i+1] = lindex[i];
	vars[vp] = f;
	strcpy(vnames[vp], token);
	lindex[i+1] = vp++;
	return vp-1;
    } else
	printf("error: this little calculator can only hold %d variables.\n",
	       MAXVAR);
    return -1;
}

void evalfun(char token[])
{
    double op1, op2;
    int i;

    for (i = 0; token[i] != '\0'; i++)
	token[i] = tolower(token[i]);

    if (strcmp(token, "c") == 0)
	sp = 0;
    else if (strcmp(token, "d") == 0) {
	push(op2 = pop());
	push(op2);
    } else if (strcmp(token, "p") == 0) {
	printf("\t%.8g\n", op2 = pop());
	push(op2);
    } else if (strcmp(token, "s") == 0) {
	op2 = pop();
	op1 = pop();
	push(op2);
	push(op1);
    } else if (strcmp(token, "sin") == 0)
	push(sin(pop()));
    else if (strcmp(token, "cos") == 0)
	push(cos(pop()));
    else if (strcmp(token, "tan") == 0)
	push(tan(pop()));
    else if (strcmp(token, "asin") == 0) {
	if ((op2 = pop()) >= -1 && op2 <= 1)
	    push(asin(op2));
	else
	    printf("error: x is out of domain\n");
    } else if (strcmp(token, "acos") == 0)
	if ((op2 = pop()) >= -1 && op2 <= 1)
	    push(acos(op2));
	else
	    printf("error: x is out of domain\n");
    else if (strcmp(token, "atan") == 0)
	push(atan(pop()));
    else if (strcmp(token, "atan2") == 0) {
	op2 = pop();
	push(atan2(pop(), op2));
    } else if (strcmp(token, "sinh") == 0)
	push(sinh(pop()));
    else if (strcmp(token, "cosh") == 0)
	push(cosh(pop()));
    else if (strcmp(token, "tanh") == 0)
	push(tanh(pop()));
    else if (strcmp(token, "exp") == 0)
	push(exp(pop()));
    else if (strcmp(token, "log") == 0)
	if ((op2 = pop()) > 0)
	    push(log(op2));
	else
	    printf("error: x is out of domain\n");
    else if (strcmp(token, "log10") == 0)
	if ((op2 = pop()) > 0)
	    push(log10(op2));
	else
	    printf("error: x is out of domain\n");
    else if (strcmp(token, "pow") == 0) {
	if ((op2 = pop()) <= 0 && (op1 = pop()) == 0
	    || op1 < 0 && fmod(op2, 1) != 0)
	    printf("error: the pair (x, y) is out of domain\n");
	else
	    push(pow(op1, op2));
    } else if (strcmp(token, "sqrt") == 0)
	if ((op2 = pop()) >= 0)
	    push(sqrt(op2));
	else
	    printf("error: x is out of domain\n");
    else
	printf("error: unknown routine %s\n", token);
}

void push(double f)
{
    if (sp < MAXVAL)
	val[sp++] = f;
    else
	printf("error: stack full, can't push %g\n", f);
}

double pop(void)
{
    if (sp > 0)
	return val[--sp];
    else {
	printf("error: stack empty\n");
	return 0.0;
    }
}
