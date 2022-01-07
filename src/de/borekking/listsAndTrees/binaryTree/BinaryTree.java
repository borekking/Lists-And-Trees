package de.borekking.listsAndTrees.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<K extends Comparable<K>, V> {

    private BinaryNode<K, V> root;

    public BinaryTree() {
    }

    public void put(K key, V value) {
        if (this.root == null) {
            this.root = new BinaryNode<>(key, value);
            return;
        }

        this.root.put(key, value);
    }

    public V get(K key) {
        if (this.root == null) return null;

        return this.root.get(key);
    }

    public List<K> keySet() {
        if (this.root == null) return new ArrayList<>();

        return this.root.keys();
    }

    public boolean containsKey(K key) {
        if (this.root == null) return false;

        return this.root.containsKey(key);
    }

    public int size() {
        if (this.root == null) return 0;

        return this.root.size();
    }
}
