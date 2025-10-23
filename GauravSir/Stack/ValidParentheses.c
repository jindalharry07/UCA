#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX 10000

typedef struct
{
    char items[MAX];
    int top;
} Stack;

void init(Stack *s)
{
    s->top = -1;
}

bool isEmpty(Stack *s)
{
    return s->top == -1;
}

bool isFull(Stack *s)
{
    return s->top == MAX - 1;
}

void push(Stack *s, char ch)
{
    if (!isFull(s))
    {
        s->items[++(s->top)] = ch;
    }
}

char pop(Stack *s)
{
    if (!isEmpty(s))
    {
        return s->items[(s->top)--];
    }
    return '\0';
}

char peek(Stack *s)
{
    if (!isEmpty(s))
    {
        return s->items[s->top];
    }
    return '\0';
}

bool isValid(const char *s)
{
    Stack st;
    init(&st);

    for (int i = 0; s[i] != '\0'; i++)
    {
        char ch = s[i];
        if (ch == '(' || ch == '[' || ch == '{')
        {
            push(&st, ch);
        }
        else
        {
            if (isEmpty(&st))
            {
                return false;
            }
            char top = peek(&st);
            if ((ch == ')' && top == '(') || (ch == ']' && top == '[') || (ch == '}' && top == '{'))
            {
                pop(&st);
            }
            else
            {
                return false;
            }
        }
    }
    return isEmpty(&st);
}

void test(const char *input)
{
    printf("Input: %s\n", input);
    printf("Output: %s\n\n", isValid(input) ? "true" : "false");
}

int main()
{
    test("()");
    test("()[]{}");
    test("(]");
    test("([])");
    test("([)]");
    return 0;
}
