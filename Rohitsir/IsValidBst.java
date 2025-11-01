class Solution {
  class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
      left = null;
      right = null;
    }
  }
  private static boolean isValidBSTCheck(TreeNode root, int min, int max) {
    if(root == null) {
      return true;
    }

    if(root.val <= min || root.val >= max) {
      return false;
    }

    return isValidBSTCheck(root.left, min, root.val) && isValidBSTCheck(root.right, root.val, max);
  }
  public static boolean isValidBST(TreeNode root) {
    if(root == null) {
      return true;
    }

    return isValidBSTCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static void main(String[] arg) {
    
  }
}