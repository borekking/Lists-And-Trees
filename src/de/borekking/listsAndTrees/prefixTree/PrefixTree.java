package de.borekking.listsAndTrees.prefixTree;

import de.borekking.listsAndTrees.binaryTree.BinaryTree;

import java.util.Map;
import java.util.Objects;

public class PrefixTree<T> {

    /*
     * Implementation of a prefix tree without using Maps.
     *
     */

    // Object to link to
    private T value;

    // This Nodes Character
    private char character;

    // List of PrefixTree
    private final BinaryTree<Character, PrefixTree<T>> nodes;

    // Constructor for first PrefixTree without a character
    public PrefixTree() {
        this.nodes = new BinaryTree<>();
    }

    // Private constructor setting a given character
    private PrefixTree(char character) {
        this();
        this.character = character;
    }

    // Static methode to get a PrefixTree from a Map
    public static <T> PrefixTree<T> fromMap(Map<String, T> map) {
        // Initialize tree
        PrefixTree<T> tree = new PrefixTree<>();

        // Fill tree with map's content
        for (String key : map.keySet()) {
            // Current value
            T value = map.get(key);
            // Put key and value into tree
            tree.put(key, value);
        }

        // Return tree.
        return tree;
    }

    // Returns if the Strings value changed taking a char-array key and a value of type T - "value".
    public void put(char[] key, T value) {
        PrefixTree<T> node = this;

        for (char c : key) {
            PrefixTree<T> node1 = node.nodes.get(c);

            if (node1 == null) {
                node1 = new PrefixTree<>(c);
                node.nodes.put(c, node1);
            }

            node = node1;
        }

        node.value = value;
    }

    // Returns if the Strings value changed taking a string str (key) and a value t.
    public void put(String key, T value) {
        this.put(key.toCharArray(), value);
    }

    // Gets the value of a string. If str is null or the string is not found, null is returned.
    public T get(String str) {
        return this.get(str.toCharArray());
    }

    // Gets the value of a character-array. If the array is null or the array is not found, null is returned.
    public T get(char[] chars) {
        PrefixTree<T> node = this;

        for (char c : chars) {
            node = node.nodes.get(c);

            if (node == null) return null;
        }

        return node.value;
    }

    // Overridden toString methode returning every String in this tree, and it's value
//    @Override
//    public String toString() {
//        Map<String, T> map = this.getAsMap();
//        int size = map.size();
//
//        return "size=" + size + ", content=" + map;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        PrefixTree<?> that = (PrefixTree<?>) o;
        return this.character == that.character && Objects.equals(this.value, that.value) && this.nodes.equals(that.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, character, nodes);
    }

//    // Methode to get all String the PrefixTree is holding as List of Strings
//    public Map<String, T> getAsMap() {
//        // Determinate this PrefixTrees prefix / character
//        String prefix = this.character == Character.MIN_VALUE ? "" : String.valueOf(this.character);
//
//        // Initialize List of Strings - keys
//        Map<String, T> map = new HashMap<>();
//
//        // If this PrefixTree has a value the current prefix is added to the list
//        if (this.value != null) {
//            map.put(prefix, this.value);
//        }
//
//        // Go through all nodes
//        for (PrefixTree<T> currentTree : this.nodes) {
//            // Get the current node's / tree's map
//            Map<String, T> currentMap = currentTree.getAsMap();
//
//            // For each key of currentMap, add the prefix with the key pointing to the key's value into map.
//            for (String currentKey : currentMap.keySet()) {
//                map.put(prefix + currentKey, currentMap.get(currentKey));
//            }
//        }
//
//        // Return map.
//        return map;
//    }
//
//    // Methode to get all keys the PrefixTree is holding as List of Strings
//    public List<String> getKeys() {
//        // Determinate this PrefixTrees prefix / character
//        String prefix = this.character == Character.MIN_VALUE ? "" : String.valueOf(this.character);
//
//        // Initialize List of Strings - keys
//        List<String> keys = new ArrayList<>();
//
//        // If this PrefixTree has a value the current prefix is added to the list
//        if (this.value != null) {
//            keys.add(prefix);
//        }
//
//        // Go through all nodes
//        for (PrefixTree<T> tree : this.nodes.keySet()) {
//            // Get the current node's / tree's keys
//            List<String> currentKeys = tree.getKeys();
//
//            // For each key, add the prefix with the key to keys.
//            for (String currentKey : currentKeys) {
//                keys.add(prefix + currentKey);
//            }
//        }
//
//        // Return keys.
//        return keys;
//    }

    // Private implementation of putting an object in.
    private T getImp(char[] chars, int index) {
        // Recursive canceling if the array's length is equal to index
        if (chars.length == index) {
            // Value is returned (can be null).
            return this.value;
        }

        // Getting the current char (c) and it's node.
        char c = chars[index];
        PrefixTree<T> node = this.nodes.get(c);

        // If no node was found (is null) null is returned.
        if (node == null) return null;

        // Recursively returning get on the found node.
        return node.getImp(chars, index + 1);
    }

    // Private implementation of putting an object in.
    private boolean putImp(char[] chars, int index, T t) {

        // Recursive canceling if the array's length is equal to index+1 (which means, the index reached the end of the array)
        if (chars.length == index) {
            return this.set(t);
        }

        // Current character c
        char c = chars[index];

        // Get a node with current character as character
        PrefixTree<T> node = this.nodes.get(c);

        // If node is null (there is no node with the character), node is set to a new node and added to the nodes list.
        if (node == null) {
            node = new PrefixTree<>(c);
            this.nodes.put(c, node);
        }

        // Recursively returning putImp on the found/created node.
        return node.putImp(chars, index + 1, t);
    }

    // Sets a value t as current object and returns if the object changed.
    private boolean set(T t) {
        boolean change = !Objects.equals(this.value, t);
        this.value = t;
        return change;
    }

    // Getter for character
    public char getCharacter() {
        return character;
    }
}
