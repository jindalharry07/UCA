
import java.util.Stack;

public class InfixToPostfix {
  static int priority(char ch) {
    switch (ch) {
      case '^':
        return 3;
      case '*':
      case '/':
        return 2;
      case '+':
      case '-':
        return 1;
      default:
        return 0;
    }
  }

  private static String infixToPostfix(String infix) {
    StringBuilder postfix = new StringBuilder();
    Stack<Character> st = new Stack<>();

    for (char ch : infix.toCharArray()) {
      if (Character.isLetterOrDigit(ch)) {
        postfix.append(ch);
      } else if (ch == '(') {
        st.add(ch);
      } else if (ch == ')') {
        while (!st.isEmpty() && st.peek() != '(') {
          postfix.append(st.pop());
        }

        if (!st.isEmpty() && st.peek() == '(') {
          st.pop();
        }
      } else {
        while (!st.isEmpty() && priority(ch) <= priority(st.peek())) {
          postfix.append(st.pop());
        }
        st.add(ch);
      }
    }

    while (!st.isEmpty()) {
      postfix.append(st.pop());
    }
    return postfix.toString();
  }

  public static void test() {
    String expr = "a+b*(c^d-e)^(f+g*h)-i";
    String postfix = infixToPostfix(expr);
    System.out.println("Infix   : " + expr);
    System.out.println("Postfix : " + postfix);
  }

  public static void main(String[] args) {
    test();
    System.out.println("All working fine!");
  }
}
