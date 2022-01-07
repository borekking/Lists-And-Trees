package de.borekking.listsAndTrees.binaryTree;

import de.borekking.listsAndTrees.util.ComparatorUtils;

import java.util.ArrayList;
import java.util.List;

public class BinaryNode<K extends Comparable<K>, V> {

    final K key;

    final V value;

    BinaryNode<K, V> left, right;

    public BinaryNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void pp() {
        String prefix = "                    ";
        this.ppImp(prefix, 0);
    }

    public void put(K key, V value) {
        // This
        if (ComparatorUtils.isEqual(key, this.key)) return;

        // Left
        if (ComparatorUtils.isLess(key, this.key)) {
            if (this.left == null) this.left = new BinaryNode<>(key, value);
            else this.left.put(key, value);
        }
        // Right
        else { // ComparatorUtils.isGreater(key, this.key)
            if (this.right == null) this.right = new BinaryNode<>(key, value);
            else this.right.put(key, value);
        }
    }

    public V get(K key) {
        // This
        if (ComparatorUtils.isEqual(key, this.key)) return this.value;

        // Left
        if (this.left != null && ComparatorUtils.isLess(key, this.key)) {
            return this.left.get(key);
        }
        // Right
        else if (this.right != null && ComparatorUtils.isGreater(key, this.key)) {
            return this.right.get(key);
        }

        return null;
    }

    public int size() {
        // This
        int sum = 1;

        // Left
        if (this.left != null)
            sum += this.left.size();

        // Right
        if (this.right != null)
            sum += this.right.size();

        return sum;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public List<K> keys() {
        return this.keysImp(new ArrayList<>());
    }

    private List<K> keysImp(List<K> keys) {
        keys.add(this.key);

        if (this.left != null) this.left.keysImp(keys);
        if (this.right != null) this.right.keysImp(keys);

        return keys;
    }

    public boolean containsKey(K key) {
        if (this.isLeaf()) return false;

        if (ComparatorUtils.isEqual(this.key, key)) return true;

        return this.left != null && this.left.containsKey(key) || this.right != null && this.right.containsKey(key);
    }

    private void ppImp(String prefix, int amount) {
        // Actual prefix
        String prefix1 = prefix.repeat(amount);

        // Left
        if (this.left != null) this.left.ppImp(prefix, amount + 1);
        else System.out.println();

        // This
        System.out.printf("%sdepth_%d(%s = %s)%n", prefix1, amount, this.key, this.value);

        // Right
        if (this.right != null) this.right.ppImp(prefix, amount + 1);
        else System.out.println();
    }
}

