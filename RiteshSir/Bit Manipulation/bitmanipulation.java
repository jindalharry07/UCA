/*
1. sign function – return 1 if positive, 0 if zero, and –1 if negative.  Examples: sign(130) = 1, sign(-23) = -1
You are allowed to use only one of these : ! ~ & ^ | + << >>
 Max ops : 10

2. conditional or ternary function – same as x ? y : z 
conditional (2, 4, 5) = 4      
conditional (0, 4, 5) = 5
legal ops: ! ~ & ^ | + << >>

3. bang – Compute !x without using !
Examples: bang(3)=0, bang(0)=1
Legal ops: ~ & ^ | + << >>

4. Return x with the n bits that begin at position p inverted (i.e. turn 0 into 1 and vice versa)
int invert (int x, int p, int n) {
    //TODO   -- flip n bits of x starting from p posion

}

*/

// Expression	In C result (int)	In JS result (boolean)
// !x	    1 if zero, else 0	true if falsy, else false
// !!x	  0 if zero, else 1	false if falsy, else true


import java.util.*;
public class bitmanipulation {

  public static int sign(int x) {
    return (x >> 31) | ((x != 0) ? 1 : 0);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    System.out.println(sign(n));


    // 2. conditional or ternary function – same as x ? y : z 
    // conditional (2, 4, 5) = 4      
    // conditional (0, 4, 5) = 5
    // legal ops: ! ~ & ^ | + << >>

    /*
     *  let
     */
  }
}