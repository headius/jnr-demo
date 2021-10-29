#include <stdlib.h>
#include <string.h>

int get_integer() {
    return 42;
}

unsigned int get_sum(unsigned int first, unsigned int second) {
    return first + second;
}

char *get_string() {
    // Create string in heap
    char *str = (char *) malloc(6 * sizeof(char));
    strcpy(str, "ASCII");
    return str;
}