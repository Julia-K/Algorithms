public class Node {
    Node left;
    Node right;
    Node parent;
    int key;
    public RBTree.color color;

    Node(int key) {
        this.key = key;
        left = RBTree.nullLeaf;
        right = RBTree.nullLeaf;
        parent = RBTree.nullLeaf;
        color = RBTree.color.BLACK;
    }
}
