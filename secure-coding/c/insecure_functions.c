#include <stdio.h>

int main() {
    char password[20];
    printf("Enter your password: ");
    gets(password);
    printf("Your password is: %s\n", password);
    return 0;
}
// To compile this code and show how to exploit it, run:
// gcc -o insecure_functions insecure_functions.c -fno-stack-protector -z execstack -no-pie
// ./insecure_functions
// Enter your password: 12345678901234567890ERROR: Segmentation fault



