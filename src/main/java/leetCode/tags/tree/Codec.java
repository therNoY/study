package leetCode.tags.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Codec {

    public static void main(String[] args) {
        String s = new Codec().serialize(
                TreeNode.getTreeByArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, null, null, null, null, 19));

        TreeNode node = new Codec().deserialize(s);
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder res = new StringBuilder();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (node != null) {
                    res.append(node.val).append(",");
                    deque.addLast(node.left);
                    deque.addLast(node.right);
                } else {
                    res.append("null").append(",");
                }
            }
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        LinkedList<TreeNode> lastNodes = new LinkedList<>();
        // 二叉堆数组层次遍历得到
        String[] nodes = data.split(",");
        int index = 0;
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        lastNodes.addLast(root);
        index++;
        while (lastNodes.size() > 0) {
            int size = lastNodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = lastNodes.pollFirst();
                node.left = nodes[index].equals("null") ? null : new TreeNode(Integer.valueOf(nodes[index]));
                node.right = nodes[index + 1].equals("null") ? null : new TreeNode(Integer.valueOf(nodes[index + 1]));
                index += 2;
                if (node.left != null) lastNodes.addLast(node.left);
                if (node.right != null) lastNodes.addLast(node.right);
            }
        }
        return root;
    }

    public TreeNode deserialize(String[] nodes, int index) {
        if (index >= nodes.length || nodes[index].equals("null")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(nodes[index]));
        node.left = deserialize(nodes, (index << 1) + 1);
        node.right = deserialize(nodes, (index << 1) + 2);
        return node;
    }
}
