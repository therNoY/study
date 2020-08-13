package data_structure.tree;


import java.util.*;

/**
 * B Tree 的实现
 */
public class BTree<K, V> {

    private Node root = null;
    private Comparator<K> comparator = null;

    private int degree;

    // 节点的高度
    private int height = 0;
    // 节点数量
    private int size = 0;

    public BTree() {
    }

    public BTree(int degree) {
        Node.degree = degree;
        this.degree = degree;
    }

    public BTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    /**
     * 插入值
     * @param k
     * @param v
     */
    public void insert(K k, V v) {
        if (root == null) {
            root = new Node(k, v);
            return;
        }
        Node node = getInsertNode(k);

        insertNode(node, k, v);

        if (isNeedSplit(node)) {
            split(node);
            root.flushHeight();
        }

        size ++;
    }

    /**
     * 向一个节点插入一个元素
     *
     * @param node
     * @param k
     */
    private void insertNode(Node node, K k, V v) {

        Object[] keys = node.keys;
        Object[] values = node.values;

        // 现获取要插入节点的index
        int index = getCompareNodeIndex(k, node);

        int moveNum;
        /**
         * 将元素向后移动
         */
        if ((moveNum = node.elementSize - index) != 0) {
            System.arraycopy(keys, index, keys, index + 1, moveNum);
            System.arraycopy(values, index, values, index + 1, moveNum);
        }

//        for (int i = node.elementSize; i > index; i--) {
//            keys[i] = keys[i - 1];
//            values[i] = values[i - 1];
//        }

        keys[index] = k;
        values[index] = v;

        node.elementSize++;
    }

    /**
     * 获取需要插入的节点
     *
     * @param k
     * @return
     */
    private Node getInsertNode(K k) {
        Node currentNode = root;
        /**
         * 当有子节点的时候找到要插入的子节点
         */
        while (currentNode.leftNode() != null) {
            Node point = currentNode.nodes[getCompareNodeIndex(k, currentNode)];
            currentNode = point;
        }
        return currentNode;
    }

    /**
     * 找到应该在node 元素中插入的index
     *
     * @param k
     * @param node
     * @return
     */
    private int getCompareNodeIndex(K k, Node node) {
        Object[] elements = node.keys;
        for (int i = 0; i < node.elementSize; i++) {
            int cr = compare(k, elements[i]);
            if (cr == 0)
                return i + 1;
            else if (cr < 0)
                return i;
        }

        return node.elementSize;
    }

    /**
     * k 大返回 正数 t小返回负整数
     *
     * @param k
     * @param o
     * @return
     */
    public int compare(K k, Object o) {
        if (k instanceof Comparable) {
            Comparable c = (Comparable) k;
            return c.compareTo(o);
        } else if (comparator != null) {
            return comparator.compare(k, (K) o);
        } else {
            throw new RuntimeException("错误的比较器");
        }
    }

    /**
     * 删除元素
     * @param k
     */
    public void remove(K k) {

        Node dNode = null;
        Node parNode;
        int resIndex = -1;
        Node node = root;
        int nodeIndex;
        for (;node != null;node = node.nodes[nodeIndex]){

            nodeIndex = getValueIndex(node,k);

            if (node.keys[nodeIndex] != null && node.keys[nodeIndex].equals(k)) {
                dNode = node;
                resIndex = nodeIndex;
            }
        }

        if (dNode == null)
            return;

        if (dNode == root){
            // 删除的节点如果是根节点就从

        }else if (dNode.leftNode() == null) {
            // 删除的节点是根节点
            if (dNode.elementSize > 1){
                // 要删除的节点上有多个节点 直接删除
                int d = resIndex;
                while (d < dNode.keys.length - 1) {
                    dNode.keys[d] = dNode.keys[d + 1];
                    dNode.keys[d] = dNode.values[d + 1];
                    d ++;
                }
                dNode.elementSize --;
                return;
            }

            // 根节点只有一个 看看有没有左兄弟 或者右兄弟
            parNode = dNode.parentNode;

        }else {

        }

    }

    /**
     * 打印
     */
    void show() {
        System.out.println("========================");
        Node c = root;
        List<Node> showNode = new ArrayList();
        showNode.add(c);
        for (List<Node> newNode;showNode.size() > 0 && showNode.get(0) != null;showNode = newNode){
            newNode = new ArrayList<>();
            List<Node> finalNewNode = newNode;
            showNode.forEach(node -> {
                if (node != null) {
                    printNode(node);
                    finalNewNode.addAll(Arrays.asList(node.nodes));
                }
            });
            System.out.println();
        }
        System.out.println("========================\n");
    }

    private void printNode(Node node) {
        for (int i = 0; i < node.keys.length; i++) {
            if (node.keys[i] != null)
                System.out.print(node.keys[i] + " ");
        }
        System.out.print("  | ");
    }

    V search(K k) {
        Node node = root;
        int nodeIndex;
        for (;node != null;node = node.nodes[nodeIndex]){

            System.out.println("查询" + node.toString());

            nodeIndex = getValueIndex(node,k);

            if (node.keys[nodeIndex] != null && node.keys[nodeIndex].equals(k)) {
                return (V) node.values[nodeIndex];
            }
        }

        return null;
    }


    public int getValueIndex(Node node, K k) {

        for (int i = 0; i < node.elementSize; i++) {

            if (i == node.keys.length - 1)
                break;

            if (compare(k, node.keys[i]) <= 0)
                return i;

            if (node.keys[i + 1] == null)
                break;

            if (compare(k, node.keys[i]) * compare(k, node.keys[i + 1]) < 0){
                return i + 1;
            }
        }
        return node.elementSize;
    }

    /**
     * 是否需要分割节点
     * @param node
     * @return
     */
    private boolean isNeedSplit(Node node) {
        return node.elementSize >= degree;
    }

    /**
     * 准备分裂过多的Node
     * @param node
     */
    private void split(Node node) {

        // 1.获取要分裂的位置
        int splitIndex = (node.elementSize + 1) / 2 - 1;
        Object splitKey = node.keys[splitIndex];
        Object splitValue = node.values[splitIndex];

        // 2.创建两个子节点 并保存分裂前的数据和指针
        Node leftNode = new Node();
        // 保存原来的节点数据
        System.arraycopy(node.keys, 0, leftNode.keys, 0, splitIndex);
        System.arraycopy(node.values, 0, leftNode.values, 0, splitIndex);
        leftNode.elementSize = splitIndex;

        Node rightNode = new Node();
        System.arraycopy(node.keys, splitIndex + 1, rightNode.keys, 0, node.elementSize - 1 - splitIndex);
        System.arraycopy(node.values, splitIndex + 1, rightNode.values, 0, node.elementSize - 1 - splitIndex);
        rightNode.elementSize = node.elementSize - 1 - splitIndex;

        // 保存原来的节点的指针
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] == null)
                break;
            if (i <= splitIndex)
                leftNode.addNodePoint(node.nodes[i]);
            else
                rightNode.addNodePoint(node.nodes[i]);
        }

        // 3. 将原来的父节点作为新的父节点
        Node parentNode;
        if ((parentNode = node.parentNode) != null) {
            // 3.1 将中间节点插入原来的父节点
            insertNode(parentNode, (K) splitKey, (V) splitValue);

            // 3.2 将原来的父节点的子节点指针修改为两个新的孩子
            // 获取原来的位置
            int pointIndex = -1;
            for (int i = 0; i < parentNode.nodes.length; i++) {
                if (parentNode.nodes[i] == node) {
                    pointIndex = i;
                    break;
                }
            }

            // 移动指针 可以使新的指针可以插入
            int movePointNum = parentNode.elementSize - pointIndex;
            System.arraycopy(parentNode.nodes, pointIndex, parentNode.nodes, pointIndex + 1, movePointNum);
            parentNode.nodes[pointIndex] = leftNode;
            parentNode.nodes[pointIndex + 1] = rightNode;
        } else {
            // 这里会增加数高度
            height ++;
            parentNode = new Node(splitKey, splitValue);
            root = parentNode;
            parentNode.nodes[0] = leftNode;
            parentNode.nodes[1] = rightNode;
        }
        leftNode.parentNode = parentNode;
        rightNode.parentNode = parentNode;

        node = null;
        // 4. 判断是否需要分割节点
        if (isNeedSplit(parentNode))
            split(parentNode);
    }


    static class Node {

        // 指向最右边的子节点
        Node rightNode;

        Node parentNode;

        // 当前节点的高度
        int height;

        int elementSize;

        static int degree;

        public static int getDegree() {
            return degree;
        }

        // 指向下一个节点的指针集合
        Node[] nodes;

        Object[] keys;
        Object[] values;

        public Node() {
            nodes = new Node[degree + 1];
            keys = new Object[degree];
            values = new Object[degree];
            parentNode = null;
        }

        public Node(Object k, Object v) {
            nodes = new Node[degree + 1];
            keys = new Object[degree];
            values = new Object[degree];
            keys[0] = k;
            values[0] = v;
            elementSize = 1;
            parentNode = null;
        }

        public void addNodePoint(Node node) {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] == null) {
                    nodes[i] = node;
                    node.parentNode = this;
                    break;
                }
            }
        }



        /**
         * 从根节点开始 刷新高度
         * @return
         */
        public int flushHeight() {
            if (isHasNode()) {
                for (int i = 0; i < nodes.length; i++) {
                    if (nodes[i] != null)
                        nodes[i].flushHeight();
                }
                height = leftNode().height + 1;
            }else {
                height = 0;
            }
            return height;
        }

        private boolean isHasNode (){
            return leftNode() != null;
        }

        public Node leftNode() {
            return nodes[0];
        }

        @Override
        public String toString() {
            return "Node{" +
                    "keys=" + Arrays.toString(keys) +
                    '}';
        }
    }


    public static void main(String[] args) {
        BTree<Integer, Object> bTree = new BTree<>(3);


        bTree.insert(20, 20);
        bTree.insert(40, 40);
        bTree.insert(30, 30);

        bTree.insert(80, 80);
        bTree.insert(50, 50);
        bTree.insert(60, 60);
        bTree.insert(70, 70);
        bTree.insert(55, 55);
        bTree.insert(65, 65);
        bTree.show();
        bTree.insert(45, 45);
        bTree.insert(15, 15);
        bTree.insert(10, 10);
        bTree.insert(43, 43);
        bTree.insert(75, 75);
        bTree.show();

        bTree.remove(80);

        bTree.search(45);
    }


}
