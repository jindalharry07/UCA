#include<stdio.h>

/**
 * Function to calculate sum of first n even fibonacci numbers
 *
 *  @return sum of first n even fibonacci numbers
 */
int sumOfEvenFibonacci(int n) {
  if (n <= 0 ) return 0;
  if (n == 1 ) return 2;
  if (n == 2 ) return 2+8;

  int sum = 0;
  int first = 2, second = 8;
  sum += first + second;

  for (int i = 0; i < n - 2; i++) {
    int third = first + 4 * second;
    sum += third;
    first = second;
    second = third;
  }
  return sum;
}

int main() {
  int n;
  scanf("%d", &n);

  int sum=sumOfEvenFibonacci(n);
  printf("%d", sum);
  return 0;
}
