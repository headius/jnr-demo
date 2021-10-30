#include <stdlib.h>
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

typedef struct {
    large_s *large;
} opaque_s;
// Well it isn't exactly opaque, but you can pretend that -.-'

void *get_opaque_pointer() {
    opaque_s *ptr = (opaque_s *) malloc(sizeof(opaque_s));
    ptr->large = (large_s *) malloc(sizeof(large_s));
    ptr->large->small = get_small_struct();
    ptr->large->small->value = 420.69;
    return ptr;
}

double some_opaque_extraction(void *opaque) {
    return ((opaque_s *) opaque)->large->small->value;
}

typedef int (*unary)(int);

int apply_unary_function(int arg, unary f) {
    return f(arg);
}
