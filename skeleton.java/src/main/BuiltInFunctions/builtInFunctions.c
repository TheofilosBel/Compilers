#include <stdio.h>
#include <string.h>

/*########### IO FUNCTIONS ################*/

/* fun puti (n : int) : nothing; */
void grc_puti(int n)
{
	printf("%d", n);
}

/* fun putc (n : char) : nothing; */
void grc_putc(char c) 
{
	printf("%c", c);
}

/* fun puts (ref s : char[]) : nothing; */
void grc_staticPuts(char *c) 
{
	printf("%s", c);
}

/* fun geti() : int; */
int grc_geti(int *np)
{
	int n;
	scanf("%d", &n);
	return n;
}

/* fun getc() : char; */
char grc_getc()
{
	char n;
	scanf("%c", &n);
	return n;
}

/*######################################*/

/*######### Other Functions ############*/

/* fun abs(n :int) : int; */
int grc_abs(int* np, int n)
{
	if (n > 0) {
		return n;
	}
	else {
		return n*(-1);
	}
}

/* fun ord(c :char) : int; */
int grc_ord(int* np, char c)
{
	int casted = (int) c;
	return casted;
}

/* fun chr(n :int) : char; */
char grc_chr(int* np, int n)
{

	char casted = (char)n;
	return casted;
}
/*######################################*/