package com.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Constantine Solovev
 * Created: 13.03.16  11:24
 */


public class BinarySearchTree<Key extends Comparable<Key>, Value> implements Table<Key, Value> {

    private Node root;

    @Override
    public Value get(Key key) {
        if (key == null) return null;
        return get(key, root);
    }

    private Value get(Key key, Node node) {
        if (node == null) return null;
        int comparison = key.compareTo(node.key);

        if (comparison < 0)      return get(key, node.left);
        else if (comparison > 0) return get(key, node.right);
        else                     return node.value;

    }

    @Override
    public void put(Key key, Value value) {
       root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node node) {

        if (node == null) return new Node(key, value, 1);
        int comparison = key.compareTo(node.key);

        if (comparison < 0) node.left = put(key, value, node.left);
        if (comparison > 0) node.right = put(key, value, node.right);
        else node.value = value;

        node.childrenAmount = size(node.left) + size(node.right) + 1;

        return node;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.childrenAmount;
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if(node.left == null) return node;
        return min(node.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if(node.right == null) return node;
        return max(node.right);
    }

    @Override
    public Key floor(Key key) {
        Node node = floor(key, root);
        if (node == null) return null;
        return node.key;
    }

    private Node floor(Key key, Node node) {
        if (node == null) return null;

        int comparison = key.compareTo(node.key);

        if (comparison == 0) return node;
        if (comparison < 0) return floor(key, node.left);

        Node tmpNode = floor(key, node.right);
        if (tmpNode != null) return tmpNode;

        return node;

    }

    @Override
    public Key ceiling(Key key) {
        //todo
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Key key) {
        root = delete(key, root);
    }

    private Node delete(Key key, Node node) {
        if (node == null) return null;

        int comparison = key.compareTo(node.key);

        if (comparison < 0) node.left = delete(key, node.left);
        else if (comparison > 0) node.right = delete(key, node.right);
        else {
            Node max = deleteMax(node.right != null ? node.right : node.left);
            if(max == null) return null;

            max.left = node.left;
            max.right = node.right;
            node = max;
        };
        node.childrenAmount = size(node.left) + size(node.right) + 1;
        return node;

    }

    @Override
    public Key deleteMin() {
        Node node = deleteMin(root);
        return updateRootAndGetDeletedKey(node);
    }

    private Key updateRootAndGetDeletedKey(Node node) {
        if(node != null && root != null && root.key.compareTo(node.key) != 0) {
            Node copyRoot = root;
            root = node;
            return copyRoot.key;
        } else {
            root = node;
        }
        return (node != null) ? node.key : null;
    }


    private Node deleteMin(Node node) {
        if(node == null) return null;

        Node left = node.left;
        Node right = node.right;

        if(left == null) return right;
        node.left = deleteMin(left);

        node.childrenAmount = size(left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Key deleteMax() {
        Node node = deleteMax(root);
        return updateRootAndGetDeletedKey(node);
    }

    private Node deleteMax(Node node) {
        if(node == null) return null;

        Node left = node.left;
        Node right = node.right;

        if(right == null) return left;
        node.left = deleteMax(right);

        node.childrenAmount = size(left) + size(node.right) + 1;
        return node;
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;

        Node left = node.left;
        Node right = node.right;
        int comparison = key.compareTo(node.key);
        if       (comparison < 0) return rank(left, key);
        else if (comparison > 0) return 1 + size(left) + rank(right, key);
        else return size(left);

    }

    @Override
    public Key select(int index) {
        return select(root, index).key;
    }

    private Node select(Node node, int index) {
        if (node == null) return null;

        Node left = node.left;
        Node right = node.right;
        int size = size(left);
        if       (size > index) return select(left, index);
        else if (size < index) return select(right, index - size - 1);
        else return node;

    }

    @Override
    public List<Key> keys() {
        List<Key> accumulator = new ArrayList<>();
        if (root == null) return accumulator;
        getKeys(root, accumulator);
        return accumulator;
    }

    private void getKeys(Node node, List<Key> accumulator) {
        Node left = node.left;
        Node right = node.right;
        if (left != null) getKeys(left, accumulator);
        accumulator.add(node.key);
        if (right != null) getKeys(right, accumulator);
    }


    @Override
    public List<Value> values() {
        throw new UnsupportedOperationException();
    }

    /* Inner structure */

    private class Node {

        private Key key;
        private Value value;
        private int childrenAmount;
        private Node left, right;

        Node(Key key, Value value, int childrenAmount) {
            this.key = key;
            this.value = value;
            this.childrenAmount = childrenAmount;
        }

    }

    public String toString() {
        // todo
        return "";
    }

}
