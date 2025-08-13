#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

typedef struct {
    int size;
    int childSize;
} Doll;

typedef struct {
    Doll *data;
    int top;
    int capacity;
} Stack;

void initStack(Stack *stack) {
    stack->capacity = 10;
    stack->top = -1;
    stack->data = (Doll *)malloc(stack->capacity * sizeof(Doll));
    assert(stack->data != NULL);
}

void push(Stack *stack, Doll doll) {
    if (stack->top == stack->capacity - 1) {
        stack->capacity *= 2;
        stack->data = (Doll *)realloc(stack->data, stack->capacity * sizeof(Doll));
        assert(stack->data != NULL);
    }
    stack->data[++stack->top] = doll;
}

Doll pop(Stack *stack) {
    assert(stack->top >= 0);
    return stack->data[stack->top--];
}

Doll *peek(Stack *stack) {
    if (stack->top < 0) return NULL;
    return &stack->data[stack->top];
}

int isEmpty(Stack *stack) {
    return stack->top < 0;
}

void freeStack(Stack *stack) {
    free(stack->data);
    stack->data = NULL;
    stack->capacity = 0;
    stack->top = -1;
}

const char *isMatrioshka(char *line) {
    Stack stack;
    initStack(&stack);

    char *token = strtok(line, " \t\n");
    while (token != NULL) {
        int size = atoi(token);

        if (isEmpty(&stack) && size < 0) {
            Doll newDoll = {-size, 0};
            push(&stack, newDoll);
        }
        else if (isEmpty(&stack) && size > 0) {
            freeStack(&stack);
            return "Invalid";
        }
        else if (size > 0 && peek(&stack)->size == size) {
            pop(&stack);
        }
        else if (size > 0) {
            freeStack(&stack);
            return "Invalid";
        }
        else if (size < 0) {
            Doll *topDoll = peek(&stack);
            topDoll->childSize += -size;
            if (topDoll->childSize >= topDoll->size) {
                freeStack(&stack);
                return "Invalid";
            }
            Doll newDoll = {-size, 0};
            push(&stack, newDoll);
        }
        token = strtok(NULL, " \t\n");
    }

    const char *result = isEmpty(&stack) ? "Valid" : "Invalid";
    freeStack(&stack);
    return result;
}

void testMatrioshka() {
    char case1[] = "-9 -7 -2 2 -3 -2 2 3 7 9"; 
    char case2[] = "-9 -7 -2 2 -3 -2 2 4 7 9"; 
    char case3[] = "-100 -50 -6 6 50 100";               
    char case4[] = "-100 -50 -6 6 45 100";                     

    char *cases[] = {case1, case2, case3, case4};
    int numCases = 4;

    for (int i = 0; i < numCases; i++) {
        char temp[256];
        strcpy(temp, cases[i]); // Copy because strtok modifies string
        printf("Input: %s => %s\n", cases[i], isMatrioshka(temp));
    }
}

int main() {
    testMatrioshka();
    return 0;
}
