#include <bits/stdc++.h>
using namespace std;

/*
 * Tower Of Hanoi
 *
 * Problem: Move n disks from source peg to destination peg using a helper (aux) peg,
 *
 * Rules:
 *   1. Only one disk can be moved once at one time.
 *   2. Only the top disk of a stack can be moved.
 *   3. No bigger disk may be placed on top of smaller disk.
 *
 * Approach: 
 *   1. Move n-1 disks from source to helper.
 *   2. Move the nth disk to destination.
 *   3. Move n-1 disks from helper to destination.
 *
 *   Time Complexity: O(2^n)
 *   Space Complexity: O(n) -- due to recursion stack
 */

void towerOfHanoi (int n, char s, char h, char d) {
  if (n == 0) {
    return;
  }

  if (n == 1) {
    cout << "Move " << n << " from " << s << " to " << d << "\n";
    return;
  }

  towerOfHanoi(n-1,s,d,h);
  cout << "Move " << n << " from " << s << " to " << d << "\n";
  towerOfHanoi(n-1,h,s,d);
}

int main() {
  int n;
  cin >> n;

  towerOfHanoi(n,'S','H','D');

  return 0;
}

