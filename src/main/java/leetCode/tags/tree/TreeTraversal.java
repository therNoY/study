package leetCode.tags.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 栈的四种便利
 */
public class TreeTraversal {

    public static void main(String[] args) {
        System.out.println(new TreeTraversal().inorderTraversalByStack(
                TreeNode.getTreeByArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, null, null, null, null, 19)));
    }

    /**
     * 144. 二叉树的前序遍历
     * 给定一个二叉树，返回它的 前序 遍历。
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [1,2,3]
     */
    public List<Integer> preOrderTraversalByStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (stack.size() > 0) {
            TreeNode first = stack.pollLast();
            res.add(first.val);
            if (first.right != null) stack.addLast(first.right);
            if (first.left != null) stack.addLast(first.left);
        }
        return res;
    }

    // 统一格式
    public List<Integer> preOrderByStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                if (node.right != null) deque.add(node.right);
                if (node.left != null) deque.addLast(node.left);
                deque.add(node);
                deque.add(null);
            } else {
                node = deque.pollLast();
                res.add(node.val);
            }
        }
        return res;
    }

    // 递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    public void preOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preOrder(root.left, list);
            preOrder(root.right, list);
        }
    }

    // =============================================================================


    /**
     * 94. 二叉树的中序遍历
     * 给定一个二叉树，返回它的中序 遍历。
     * 示例:
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * 通过次数201,032提交次数278,315
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalByStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode currNode = root;
        while (currNode != null || deque.size() > 0) {
            while (currNode != null) {
                deque.addLast(currNode);
                currNode = currNode.left;
            }
            currNode = deque.pollLast();
            res.add(currNode.val);
            currNode = currNode.right;
        }
        return res;
    }

    public List<Integer> inOrderByStack(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode tail = deque.pollLast();
            if (tail != null) {
                if (tail.right != null) deque.addLast(tail.right);
                deque.addLast(tail);
                deque.addLast(null);
                if (tail.left != null) deque.addLast(tail.left);
            } else {
                res.add(deque.pollLast().val);
            }
        }
        return res;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }


    public void inOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
    }


    // ==============================================================


    /**
     * 145. 二叉树的后序遍历
     * 给定一个二叉树，返回它的 后序 遍历。
     * 示例:
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [3,2,1]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */
    // 使用模板
    public List<Integer> postOrderByStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                deque.add(node);
                deque.add(null);
                if (node.right != null) deque.add(node.right);
                if (node.left != null) deque.addLast(node.left);
            } else {
                node = deque.pollLast();
                res.add(node.val);
            }
        }
        return res;
    }

    public List<Integer> postOrderByStack2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode node = deque.pollLast();
            if (node.left != null) deque.addLast(node.left);
            if (node.right != null) deque.addLast(node.right);
            res.addFirst(node.val);
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    public void postOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postOrder(root.left, list);
            postOrder(root.right, list);
            list.add(root.val);
        }
    }


    /**
     * 102.给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<TreeNode> treeNodes = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        treeNodes.add(root);

        while (treeNodes.size() > 0) {
            treeNodes = add(lists, treeNodes);
        }
        return lists;
    }

    /**
     * 标准的二叉树的层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderByStack(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int size;
        while ((size = deque.size()) > 0) {
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                list.add(node.val);
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            res.addLast(list);
        }
        return res;
    }


    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    /**
     * 使用队列进行层次遍历
     *
     * @param root
     */
    public void dfs(TreeNode root, List<Integer> res) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode treeNode = deque.poll();
            res.add(treeNode.val);
            if (treeNode.left != null) deque.addLast(treeNode.left);
            if (treeNode.right != null) deque.addLast(treeNode.right);
        }
    }

    public List<TreeNode> add(List<List<Integer>> lists, List<TreeNode> treeNodes) {
        List<Integer> list = new ArrayList<>();
        List<TreeNode> newNode = new ArrayList<>();
        treeNodes.forEach(treeNode -> {
            list.add(treeNode.val);
            if (treeNode.left != null) newNode.add(treeNode.left);
            if (treeNode.right != null) newNode.add(treeNode.right);
        });
        lists.add(list);
        return newNode;
    }


}

