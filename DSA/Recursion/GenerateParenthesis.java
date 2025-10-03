import java.util.*;
public class GenerateParenthesis {
  /**
   * Given n pairs of parentheses, write a function to generate all combinations
   * of well-formed parentheses.
   * 
   * Constraints:
   * 1. 1 <= n <= 8
   * 2. The solution set must not contain duplicate combinations.
   * 
   * Example:
   * Input: n = 3
   * Output: ["((()))","(()())","(())()","()(())","()()()"]
   * 
   * @param n - Number of pairs of parentheses.
   * @returns List<String> - A list of all combinations of well-formed
   *          parentheses.
   */

  private void backtrack(List<String> res, StringBuilder curr, int open, int close, int max) {
    if (curr.length() == 2 * max) {
      res.add(curr.toString());
      System.out.println(curr);
      return;
    }

    if (open < max) {
      curr.append('(');
      backtrack(res, curr, open + 1, close, max);
      // System.out.println(curr);
      curr.deleteCharAt(curr.length() - 1);
    }

    if (close < open) {
      curr.append(')');
      backtrack(res, curr, open, close + 1, max);
      // System.out.println(curr);
      curr.deleteCharAt(curr.length() - 1);
    }
  }
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    backtrack(res, new StringBuilder(), 0, 0, n);
    System.out.println(res);
    return res;
  }

  /**
   * Main method for testing the GenerateParenthesis class.
   */
  public static void main(String[] args) {
    GenerateParenthesis gp = new GenerateParenthesis();
    int n = 3;
    List<String> result = gp.generateParenthesis(n);
    List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");


    assert result.size() == expected.size() && result.containsAll(expected) : "Test case failed";
  }
}
