package data_structure.tree;


/**
 * 树的基本抽象类
 */
public interface Tree<T, V> {
    void insert(T t);

    void remove(T t);

    void show();

    V search(T t);

    int size();
}
