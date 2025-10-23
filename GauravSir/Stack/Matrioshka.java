import java.util.*;

class Doll {
  int size;
  int childSize;

  Doll(int size) {
    this.size = size;
    this.childSize = 0;
  }
}

public class Matrioshka {

  public static String isMatrioshka(String line) {
    Stack<Doll> st = new Stack<>();
    String[] token = line.trim().split("\\s+");

    for (String t : token) {
      int size = Integer.parseInt(t);

      if (st.isEmpty() && size < 0) {
        st.push(new Doll(-size));
      } else if (st.isEmpty() && size > 0) {
        return "Invalid!";
      } else if (size > 0) {
        Doll top = st.pop();

        if (top.size != size) {
          return "Invalid!";
        }
      } else if (size < 0) {
        Doll top = st.peek();
        top.childSize += -size;

        if (top.childSize >= top.size) {
          return "Invalid!";
        }
        st.push(new Doll(-size));
      }
    }
    return st.isEmpty() ? "Valid!" : "Invalid!";
  }

  public static void testMatrioshka() {
    String[] cases = {
        "-9 -7 -2 2 -3 -2 2 3 7 9",
        "-9 -7 -2 2 -3 -2 2 4 7 9",
        "-100 -50 -6 6 50 100",
        "-100 -50 -6 6 45 100"
    };

    for (String testCase : cases) {
      System.out.println("Input: " + testCase + " => " + isMatrioshka(testCase));
    }
  }

  public static void main(String[] args) {
    testMatrioshka();
  }
}
