/*
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
 

Example 1:


Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.


class Solution {
  public boolean isValidSudoku(char[][] board) {
    
  }
}

*/

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

 

Example 1:

Input: board = 
[["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]]

Output: 
[["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]]

Explanation: The input board is shown above and the only valid solution is shown below:



Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.




class Solution {
  public void solveSudoku(char[][] board) {
    
  }
}

 */

public class Day7 {
  public static boolean isValidSudoku(char[][] board) {
    boolean[][] rows = new boolean[9][9];
    boolean[][] cols = new boolean[9][9];
    boolean[][] boxes = new boolean[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char ch = board[i][j];

        if (ch == '.') {
          continue;
        }

        int curr = ch - '1';
        int boxIdx = (i / 3) * 3 + (j / 3);

        if (rows[i][curr] || cols[j][curr] || boxes[boxIdx][curr]) {
          return false;
        }
        rows[i][curr] = true;
        cols[j][curr] = true;
        boxes[boxIdx][curr] = true;
      }
    }
    return true;
  }

  public static void solveSudoku(char[][] board) {
    solve(board);
  }

  public static boolean isValid(char[][] board, int row, int col, char ch) {
    for (int i = 0; i < 9; i++) {
      if (board[row][i] == ch) {
        return false;
      }

      if (board[i][col] == ch) {
        return false;
      }

      int subBoxRow = (row / 3) * 3 + i / 3;
      int subBoxCol = (col / 3) * 3 + i % 3;
      if (board[subBoxRow][subBoxCol] == ch) {
        return false;
      }
    }
    return true;
  }

  public static boolean solve(char[][] board) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (board[row][col] == '.') {
          for (char ch = '1'; ch <= '9'; ch++) {
            if (isValid(board, row, col, ch)) {
              board[row][col] = ch;

              if (solve(board)) {
                return true;
              } else {
                board[row][col] = '.';
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    char[][] board1 = {
        { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };

    System.out.println(isValidSudoku(board1)); // true

    char[][] board2 = {
        { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };

    System.out.println(isValidSudoku(board2)); // false

    // char[][] board = {
    // { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
    // { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
    // { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
    // { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
    // { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
    // { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
    // { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
    // { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
    // { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    // };
    char[][] board = {
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
      { '.', '.', '.', '.', '.', '.', '.', '.', '.' }
    };

    solveSudoku(board);

    for (char[] row : board) {
      for (char c : row) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
  }
}
