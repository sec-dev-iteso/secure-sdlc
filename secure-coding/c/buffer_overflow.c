#include <stdio.h>
#include <string.h>

void vulnerable_function(char *string) {
    char buffer[50];
    strcpy(buffer, string);
}

int main(int argc, char **argv) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <input>\n", argv[0]);
        return 1;
    }
    vulnerable_function(argv[1]);
    printf("Returned safely to main function.\n");
    return 0;
}
