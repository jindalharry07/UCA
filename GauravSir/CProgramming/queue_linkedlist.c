#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

struct Node {
    int data;
    struct Node* next;
};

struct Queue {
    struct Node* front;
    struct Node* rear;
};

struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    assert(newNode != NULL);
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

void initializeQueue(struct Queue* q) {
    assert(q != NULL);
    q->front = q->rear = NULL;
}

int isEmpty(struct Queue* q) {
    assert(q != NULL);
    return q->front == NULL;
}

void enqueue(struct Queue* q, int data) {
    assert(q != NULL);
    struct Node* newNode = createNode(data);
    if (q->rear == NULL) {
        q->front = q->rear = newNode;
    } else {
        q->rear->next = newNode;
        q->rear = newNode;
    }
}

int dequeue(struct Queue* q) {
    assert(q != NULL);
    assert(!isEmpty(q));
    struct Node* temp = q->front;
    int value = temp->data;
    q->front = q->front->next;
    if (q->front == NULL) {
        q->rear = NULL;
    }
    free(temp);
    return value;
}

int peek(struct Queue* q) {
    assert(q != NULL);
    assert(!isEmpty(q));
    return q->front->data;
}

void display(struct Queue* q) {
    assert(q != NULL);
    struct Node* temp = q->front;
    while (temp != NULL) {
        printf("%d ", temp->data);
        temp = temp->next
    }
    printf("\n");
}

int main() {
    struct Queue q;
    initializeQueue(&q);

    enqueue(&q, 10);
    enqueue(&q, 20);
    enqueue(&q, 30);
    display(&q);

    printf("Dequeued: %d\n", dequeue(&q));
    display(&q);

    printf("Front element: %d\n", peek(&q));

    return 0;
}
