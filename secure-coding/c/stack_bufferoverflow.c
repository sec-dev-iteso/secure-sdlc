#include <stdio.h>
#define BUFSIZE 256
// See https://cwe.mitre.org/data/definitions/121.html
int main(int argc, char **argv)
{
    char buf[BUFSIZE];
    strcpy(buf, argv[1]);
}