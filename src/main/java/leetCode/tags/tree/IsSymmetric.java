package leetCode.tags.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class IsSymmetric {

    public static void main(String[] args) {
        System.out.println(new IsSymmetric().isSymmetric(TreeNode.getTreeByArray(new Integer[]{1,2,2,null,3,null,3})));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (deque.size() != 0) {
            Deque<TreeNode> backDeque = new LinkedList<>();
            if (deque.size() == 1) {
                TreeNode node = deque.poll();
                if (node.left == null && node.right == null) {
                    return true;
                }else if (node.left != null && node.right != null) {
                    backDeque.addFirst(node.left);
                    backDeque.addLast(node.right);
                }else {
                    return false;
                }
            } else {
                int size = deque.size();
                if (size % 2 != 0) return false;
                for (int i = 0; i < size / 2; i++) {
                    TreeNode leftNode = deque.poll();
                    TreeNode rightNode = deque.pollLast();
                    if (leftNode.val != rightNode.val) {
                        return false;
                    } else {
                        if (leftNode.left == null && rightNode.right == null) {
                        }else if (leftNode.left != null && rightNode.right != null) {
                            backDeque.addFirst(leftNode.left);
                            backDeque.addLast(rightNode.right);
                        }else {
                            return false;
                        }

                        if (leftNode.right == null && rightNode.left == null) {
                        }else if (leftNode.right != null && rightNode.left != null) {
                            backDeque.addFirst(leftNode.right);
                            backDeque.addLast(rightNode.left);
                        }else {
                            return false;
                        }
                    }
                }
            }
            deque = backDeque;
        }
        return true;
    }


    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return checkTree(root.right, root.left);
    }

    public boolean checkTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left !=null && right != null) {
            if (left.val == right.val) {
                return checkTree(left.right, right.left) && checkTree(left.left, right.right);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
