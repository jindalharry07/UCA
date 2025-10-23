#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

int *stack = NULL;
int capacity = 4;
int top = -1;

void resize(int newCapacity) {
	int *newstack = (int *)realloc(stack, newCapacity * sizeof(int));
	if (newstack == NULL) {
		printf("memory allocation failed");
		exit(EXIT_FAILURE);
	}
	stack = newstack;
	capacity = newCapacity;
}

void push(int value) {
	if (top == capacity - 1)
	{
		resize(capacity * 2);
	}
	stack[++top] = value;
}

int pop()
{
	if (top == -1)
	{
		printf("Stack underflow! Can't pop \n");
		return -1;
	}

	int val = stack[top--];
	if ((top + 1) < capacity / 2 && capacity > 4)
	{
		int newCapacity = 3 * capacity / 4;
		if (newCapacity < 4)
			newCapacity = 4; // keep minimum capacity 4
		resize(newCapacity);
	}
	return val;
}

int peek()
{
	if (top == -1)
	{
		printf("Stack is Empty");
		return -1;
	}
	return stack[top];
}

int isEmpty()
{
	return top == -1;
}

void freeStack()
{
	free(stack);
	stack = NULL;
	capacity = 0;
	top = -1;
}

void testStack()
{
	stack = (int *)malloc(capacity * sizeof(int));
	assert(stack != NULL);

	assert(isEmpty() == 1);

	push(10);
	assert(isEmpty() == 0);
	assert(peek() == 10);

	push(20);
	assert(peek() == 20);

	int val = pop();
	assert(val == 20);
	assert(peek() == 10);

	val = pop();
	assert(val == 10);
	assert(isEmpty() == 1);

	val = pop();
	assert(val == -1);

	for (int i = 0; i < 100; i++)
	{
		push(i);
	}

	assert(capacity >= 100);
	assert(peek() == 99);

	for (int i = 0; i < 95; i++)
	{
		pop();
	}

	assert(capacity < 20);

	freeStack();
}

int main()
{
	testStack();
	printf("All workingfine!\n");
	return 0;
}
