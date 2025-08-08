#include<stdio.h>
#include<assert.h>
#include<string.h>
#include<stdbool.h>
#include<ctype.h>

#define Max 100 
typedef struct{
    char items[Max];
    int top;
}stack;

void init(stack *s){
    s->top=-1;
}

bool isEmpty(stack *s){
    return s->top==-1;
}

bool isFull(stack *s){
    return s->top==Max-1;
}

void push(stack *s,char ch){
    if(!isFull(s)){
	s->items[++(s->top)]=ch;
    }
}

char pop(stack *s){
    if(!isEmpty(s)){
	return s->items[(s->top)--];
    }
    return '\0';
}

char peek(stack *s){
    if(!isEmpty(s)){
        return s->items[s->top];
    }
    return '\0';
}


int priority(char ch){
    if(ch=='^')return 3;
    else if(ch=='*' || ch=='/')return 2;
    else if(ch=='+' || ch=='-')return 1;
    return 0;
}

char* InfixToPostfix(const char *infix){
    static char postfix[Max];
    stack st;
    init(&st);

    char ch;
    int idx=0;

    for(int i=0;(ch=infix[i])!='\0';i++){
        if(isalnum(ch)){
	    postfix[idx++]=ch;
	}else if(ch=='('){
	    push(&st,ch);
	}else if(ch==')'){
	    while(!isEmpty(&st) && peek(&st)!='('){
		postfix[idx++]=pop(&st);
	    }
	    if(!isEmpty(&st))pop(&st);
	}else{
	    while(!isEmpty(&st) && priority(ch)<=priority(peek(&st))){
	        postfix[idx++]=pop(&st);
	    }
	    push(&st,ch);
	}

    }

    while(!isEmpty(&st)){
    	postfix[idx++]=pop(&st);
    }

    postfix[idx]='\0';
    return postfix;
    
}

void test(){
    char expr[]="a+b*(c^d-e)^(f+g*h)-i";
    char *postfix=InfixToPostfix(expr);
    printf("%s \n",expr);
    printf("%s \n",postfix);
}

int main(){
    test();
    printf("All working fine!");
    
    return 0;
}
