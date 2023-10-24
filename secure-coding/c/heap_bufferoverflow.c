#include <stdio.h>
#define BUFSIZE 256
// See https://cwe.mitre.org/data/definitions/122.html
int main(int argc, char **argv)
{
    char *buf;
    buf = (char *)malloc(sizeof(char) * BUFSIZE);
    strcpy(buf, argv[1]);
}