
public class ValidParenthesis {
  /**
   * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
   * determine if the input string is valid.
   *
   * An input string is valid if:
   * 1. Open brackets must be closed by the same type of brackets.
   * 2. Open brackets must be closed in the correct order.
   * 3. Every close bracket has a corresponding open bracket of the same type.
   * 
   * @param s - Input string containing parentheses. 1 <= s.length <= 10^4
   * @returns boolean - True if the string is valid, false otherwise.
   */

  private int helper(String s, int idx) {
    if(idx == s.length()) {
      return idx;
    }

    char ch = s.charAt(idx);
    if(ch == ')' || ch == '}' || ch == ']') {
      return -1;
    }

    char expected;
    if(ch == '(') {
      expected = ')';
    } else if(ch == '[') {
      expected = ']';
    } else {
      expected = '}';
    }

    for(int i = idx + 1; i < s.length(); i++) {
      if(s.charAt(i) == expected) {
        return i + 1;
      } else {
        int next = helper(s, i);
        if(next == -1) {
          return -1;
        }
        i = next;
      }
    }
    return -1;
  }

  public boolean isValid(String s) {
    return helper(s, 0) == s.length();
  }

  /**
   * Main method for testing the ValidParenthesis class.
   */
  public static void main(String[] args) {
    ValidParenthesis vp = new ValidParenthesis();
    String test1 = "()";
    String test2 = "()[]{}";
    String test3 = "(]";
    String test4 = "([)]";
    String test5 = "{[]}";

    assert vp.isValid(test1) == true : "Test case 1 failed";
    assert vp.isValid(test2) == true : "Test case 2 failed";
    assert vp.isValid(test3) == false : "Test case 3 failed";
    assert vp.isValid(test4) == false : "Test case 4 failed";
    assert vp.isValid(test5) == true : "Test case 5 failed";
  }
}