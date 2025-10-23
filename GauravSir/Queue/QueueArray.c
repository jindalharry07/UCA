#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

typedef struct{
	int *data;
	int front;
	int rear;
	int size;
	int capacity;
}Queue;

Queue* createQueue(int capacity){
	assert(capacity>0);
	Queue *q=(Queue*)malloc(sizeof(Queue));
	assert(q!=NULL);
	q->data=(int*)malloc(capacity *sizeof(int));
	assert(q->data !=NULL);
	q->front=0;
	q->size=0;
	q->rear=-1;
	q->capacity=capacity;
	return q;
}

void resize(Queue *q,int cap){
	assert(cap>=q->size);
	int *newQ=(int*)malloc(sizeof(int)*cap);
	assert(newQ!=NULL);

	for(int i=0;i<q->size;i++){
		newQ[i]=q->data[(q->front+i)% q->capacity];
	}

	free(q->data);
	q->data=newQ;
	q->front=0;
	q->rear=q->size-1;
	q->capacity=cap;
}

void enqueue(Queue*q,int val){
	if(q->size==q->capacity){
		resize(q,q->capacity*2);
	}
	q->rear=(q->rear+1)%q->capacity;
	q->data[q->rear]=val;
	q->size++;
}

int dequeue(Queue*q){
	if(q->size==0)return -1;

	int val=q->data[q->front];
	q->front=(q->front+1)%q->capacity;
	q->size--;

	if(q->size<q->capacity/2&&q->capacity>4){
		int newC=(q->capacity*3)/4;
		resize(q,newC);
	}
	return val;
}

void print(Queue*q){
	for(int i=0;i<q->size;i++){
		printf("%d ",q->data[(q->front+i)%q->capacity]);
		
	}

	printf("\n");
}

void freeQueue(Queue*q){
	free(q->data);
	free(q);
}

int main(){
	Queue *q=createQueue(4);

	enqueue(q,10);
	enqueue(q,20);
	enqueue(q,30);
	enqueue(q,40);
	enqueue(q,50);
	enqueue(q,60);

	print(q);
	dequeue(q);
	dequeue(q);
	print(q);
	freeQueue(q);

}

