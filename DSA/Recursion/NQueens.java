import java.util.*;

public class NQueens {
  /**
   * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
   * such that no two queens attack each other.
   * Given an integer n, return the number of distinct solutions to the n-queens
   * puzzle.
   * 
   * Example:
   * Input n = 4
   * Output: 2
   * Explanation: There are two distinct solutions to the 4-queens puzzle as shown
   * below.
   *
   * [
   * [".Q..", // Solution 1
   * "...Q",
   * "Q...",
   * "..Q."],
   * ["..Q.", // Solution 2
   * "Q...",
   * "...Q",
   * ".Q.."
   * ]
   * ]
   * 
   * Constraints:
   * 1. 1 <= n <= 9
   * 2. You may assume that n is a positive integer.
   *
   * @param n - The size of the chessboard and the number of queens to place.
   * @returns int - The number of distinct solutions to the n-queens puzzle.
   **/

  private void saveBoard(List<List<String>> allBoards, char[][] board) {
    StringBuilder sb = new StringBuilder();
    List<String> newBoard = new ArrayList<>();
    int n = board.length;

    for (int i = 0; i < n; i++) {
      sb.setLength(0);
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'Q') {
          sb.append('Q');
        } else {
          sb.append('.');
        }
      }
      newBoard.add(sb.toString());
    }
    allBoards.add(newBoard);
  }

  private boolean isSafe(int row, int col, char[][] board) {
    int n = board.length;
    for (int i = 0; i < n; i++) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }

    for (int j = 0; j < n; j++) {
      if (board[row][j] == 'Q') {
        return false;
      }
    }

    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }

    for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }

    for (int i = row, j = col; i < n && j >= 0; i++, j--) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }

    for (int i = row, j = col; i < n && j < n; i++, j++) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }
    return true;
  }

  private int helper(int row, List<List<String>> allBoards, char[][] board) {
    int n = board.length;
    if (row == board.length) {
      saveBoard(allBoards, board);
      return 1;
    }

    int cnt = 0;
    for (int col = 0; col < n; col++) {
      if (isSafe(row, col, board)) {
        board[row][col] = 'Q';
        cnt += helper(row + 1, allBoards, board);
        board[row][col] = '.';
      }
    }

    return cnt;
  }

  public int totalNQueens(int n) {
    List<List<String>> allBoards = new ArrayList<>();
    char[][] board = new char[n][n];

    return helper(0, allBoards, board);
  }

  /**
   * Main method for testing the NQueens class.
   */
  public static void main(String[] args) {
    NQueens nQueens = new NQueens();
    assert nQueens.totalNQueens(4) == 2 : "Test case 1 failed";
    assert nQueens.totalNQueens(1) == 1 : "Test case 2 failed";
    assert nQueens.totalNQueens(5) == 10 : "Test case 3 failed";
  }
}
