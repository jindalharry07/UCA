#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

struct Node {
  int data;
  struct Node* next;
};

struct Node* top = NULL;

void push(int value) {
  struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
  if(!newNode) {
    printf("Memory allocation failed!\n");
    return;
  }

  newNode->data = value;
  newNode->next = top;
  top = newNode;

  printf("%d pushed to stack\n", value);
}

void pop() {
  if (top == NULL) {
    printf("Stack Underflow\n");
    return;
  }

  struct Node* temp = top;
  printf("%d popped from stack\n", top->data);
  top = top->next;
  free(temp);
}

void peek() {
  if (top == NULL) {
    printf("Stack is Empty!");
  } else {
    printf("Top element is %d\n", top->data);
  }
}

void display() {
  struct Node* temp = top;
  if (temp == NULL) {
    printf("Stack is Empty");
    return;
  }

  while (temp != NULL) {
    printf("%d ", temp->data);
    temp = temp->next;
  }
}

void test() {
  assert(top == NULL);

  push(10);
  assert(top != NULL && top->data == 10);

  push(20);
  assert(top != NULL && top->data == 20);

  push(30);
  assert(top != NULL && top->data == 30);

  printf("Stack elements: ");
  display();
  printf("\n");

  peek();

  // Pop element (should pop 30)
  pop();
  assert(top != NULL && top->data == 20);

  // Peek again (should be 20)
  peek();

  pop(); // pops 20
  pop(); // pops 10

  // Now stack should be empty
  assert(top == NULL);

  // Pop on empty stack (should print underflow)
  pop();

  // Peek on empty stack (should print stack empty)
  peek();

  printf("All tests passed!\n");
}

int main() {
  test();
  return 0;
}
