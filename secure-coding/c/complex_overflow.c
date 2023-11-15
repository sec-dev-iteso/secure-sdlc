#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void print_time() {
    time_t now;
    time(&now);
    printf("Current time: %s", ctime(&now));
}

void secret_function() {
    printf("Secret function activated!\n");
}

void vulnerable_function(char *user_input) {
    char buffer[100];
    int value = 5;
    char format_string[60];
    sprintf(format_string, "Received input: %%s");
    sprintf(buffer, format_string, user_input);

    printf("Buffer content: %s\n", buffer);
    printf("Value: %d\n", value);
}

int main(int argc, char **argv) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <input>\n", argv[0]);
        return 1;
    }
    print_time();
    vulnerable_function(argv[1]);
    return 0;
}
