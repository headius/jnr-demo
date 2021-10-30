#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int get_integer() {
    return 42;
}

long int get_sum(unsigned int first, unsigned int second) {
    return first + second;
}

char *get_string() {
    char *str = (char *) malloc(6 * sizeof(char));      // Creates string in heap
    strcpy(str, "ASCII");
    return str;
}

void mutate_string(char *input) {
    int length = strlen(input);
    int i = 0;
    while (i < length/2) {                              // Reverses string
        char tmp = input[length-1-i];
        input[length-1-i] = input[i];
        input[i] = tmp;
        i++;
    }
}

typedef struct {
    unsigned int index;
    double value;
} small_s;

small_s *get_small_struct() {
    small_s *ptr = (small_s *) malloc(sizeof(small_s));
    ptr->index = 12;
    ptr->value = 34.567;
    return ptr;
}

typedef struct {
    char name[32];
    small_s *small;
} large_s;

void fill_large_struct(large_s *large) {
    strcpy(large->name, "LARGE");
    large->small->index = 98;
    large->small->value = 76.543;
}

typedef union {
    unsigned char l;
    unsigned int v;
} letter_u;

void fill_letter_union(letter_u *letter) {
    letter->v = 70;
}

typedef enum {
    SUNNY  = 1,
    CLOUDY = 2,
    RAINY  = 3
} weather_e;

weather_e get_weather() {
    return RAINY;
}
