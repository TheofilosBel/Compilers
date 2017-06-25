#include <stdio.h>
#include <string.h>


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
void grc_puts(char *c) 
{
	printf("%s", c);
}

/* fun geti() : int; */
int grc_geti()
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

/* fun gets() : char[]; */
char *grc_gets()
{
	char n[256];
	scanf("%s", n);
	return n;
}


/* fun acs(n :int) : int; */
int grc_abs(int* np, int n)
{
	printf("N is:%d\n", n);
	if (n > 0) {
		*np = n;
		return n;
	}
	else {
		*np = n*(-1);
		return n*(-1);
	}
}

/* fun ord(c :char) : int; */
int grc_ord(char c)
{
	int casted = (int) c;
	return casted;
}

/* fun chr(n :int) : char; */
char grc_chr(int n)
{
	char casted = '0' + n;
	return casted;
}

/* STRING FUNCTIONS */

/* fun strlen (ref s : char[]) : int; */
int grc_strlen(char *s)
{
	return strlen(s);
}

/* fun strcmp (ref s1, s2 : char[]) : int; */
int grc_strcmp(char *s1, char *s2)
{
	strcmp(s1, s2);
}

/* fun strcpy (ref trg, src : char[]) : nothing; */
void grc_strcpy(char *trg, char *src)
{
	strcpy(trg, src);
}


/* fun strcat (ref trg, src : char[]) : nothing; */
void grc_strcat(char *trg, char *src)
{
	strcat(trg, src);
}





