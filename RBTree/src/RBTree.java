public class RBTree {
    private int red = 0;
    public static Node nullLeaf = new Node(0);
    private Node root = nullLeaf;

    public enum color {
        RED, BLACK
    }

    private void insert(int key) {
        insert(new Node(key));
    }

    private void insert(Node node) {
        Node temporary = root;
        if(root == nullLeaf) {
            root = node;
            node.color = color.BLACK;
            node.parent = nullLeaf;
        } else {
            node.color = color.RED;
            while (true) {
                if(node.key < temporary.key) {
                    if(temporary.left == nullLeaf) {
                        temporary.left = node;
                        node.parent = temporary;
                        break;
                    } else {
                        temporary = temporary.left;
                    }
                } else {
                    if(temporary.right == nullLeaf) {
                        temporary.right = node;
                        node.parent = temporary;
                        break;
                    } else {
                        temporary = temporary.right;
                    }
                }
            }
            fixInsertRBTree(node);
        }
    }

    private void fixInsertRBTree(Node node) {
        Node uncle = nullLeaf;
        while (node.parent.color == color.RED) {
            if(node.parent == node.parent.parent.left) { //jesli rodzic jest lewym dzieckiem
                uncle = node.parent.parent.right;

                if(uncle.color == color.RED) { //1. wujek jest czerwony
                    node.parent.color = color.BLACK;
                    uncle.color = color.BLACK;
                    node.parent.parent.color = color.RED;
                    node = node.parent.parent; // węzeł jest teraz dziadkiem
                    continue;
                } else if (node == node.parent.right) { //2. leżą w różnych kierunkach tworząc zakręt
                    node = node.parent;
                    leftRotate(node);
                }

                node.parent.color = color.BLACK;
                node.parent.parent.color = color.RED;
                rightRotate(node.parent.parent);

            } else { //jesli rodzic jest prawym dzieckiem
                uncle = node.parent.parent.left;

                if(uncle.color == color.RED) {
                    node.parent.color = color.BLACK;
                    uncle.color = color.BLACK;
                    node.parent.parent.color = color.RED;

                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.left) {
                    node = node.parent;
                    rightRotate(node);
                }

                node.parent.color = color.BLACK;
                node.parent.parent.color = color.RED;
                leftRotate(node.parent.parent);
            }
        }
        root.color = color.BLACK;
    }

    private void leftRotate(Node node) {
        Node temp = node.right;
        node.right = temp.left; // lewe odgałęzienie tempa staje się prawym odgałęzieniem noda
        if (temp.left != nullLeaf) { // jesli temp ma lewe dziecko
            temp.left.parent = node;
        }
        temp.parent = node.parent; // przypisanie rodzica

        if (node.parent == nullLeaf) { // jesli node jest korzeniem
            this.root = temp;
        } else if (node == node.parent.left) { // jesli node jest lewym dzieckiem
            node.parent.left = temp;
        } else { // jesli jest prawym
            node.parent.right = temp;
        }
        temp.left = node; //node staje sie lewym dzieckiem tempa
        node.parent = temp;
    }

    private void rightRotate(Node node) {
        Node temp = node.left;
        node.left = temp.right; // prawe odgałęzienie tempa staje się lewym odgałęzieniem noda
        if (temp.right != nullLeaf) { // jeśli temp ma prawe dziecko
            temp.right.parent = node;
        }
        temp.parent = node.parent; // przypisanie rodzica
        if (node.parent == nullLeaf) { // jesli node jest korzeniem
            this.root = temp;
        } else if (node == node.parent.right) { // jesli node jest prawym dzieckiem
            node.parent.right = temp;
        } else { // jesli lewym
            node.parent.left = temp;
        }
        temp.right = node; // node staje sie prawym dzieckiem tempa
        node.parent = temp;
    }

    private void swapNodes(Node node, Node nodeRight){
        if (node.parent == null) { //jesli jest korzeniem
            root = nodeRight;
        } else if (node == node.parent.left){ //jesli node jest lewym dzieckiem
            node.parent.left = nodeRight;
        } else { //prawym
            node.parent.right = nodeRight;
        }
        nodeRight.parent = node.parent;
    }

    private Node minimumTree(Node node) {
        while (node.left != nullLeaf) {
            node = node.left;
        }
        return node;
    }

    private void delete(int key) {
        delete(this.root,key);
    }

    private void delete(Node node, int key) {
        Node founded = nullLeaf;
        Node a, b;

        while (node != nullLeaf){
            if (node.key == key) {
                founded = node;
            }
            if (node.key <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (founded == nullLeaf) {
            System.out.println("Brak");
            return;
        }

        b = founded;
        color bOriginalColor = b.color;
        if (founded.left == nullLeaf) {
            a = founded.right;
            swapNodes(founded, founded.right);
        } else if (founded.right == nullLeaf) {
            a = founded.left;
            swapNodes(founded, founded.left);
        } else {
            b = minimumTree(founded.right);
            bOriginalColor = b.color;
            a = b.right;
            if (b.parent == founded) {
                a.parent = b;
            } else {
                swapNodes(b, b.right);
                b.right = founded.right;
                b.right.parent = b;
            }

            swapNodes(founded, b);
            b.left = founded.left;
            b.left.parent = b;
            b.color = founded.color;
        }
        if (bOriginalColor == color.BLACK){
            fixDelete(a);
        }
    }

    private void fixDelete(Node node) {
        Node bro;
        while (node != root && node.color == color.BLACK) {
            if (node == node.parent.left) { //jesli node jest lewym dzieckiem
                bro = node.parent.right; // s - brat noda
                if (bro.color == color.RED) { //
                    //1. brat jest czerwony
                    bro.color = color.BLACK;
                    node.parent.color = color.RED;
                    leftRotate(node.parent);
                    bro = node.parent.right;
                }

                if (bro.left.color == color.BLACK && bro.right.color == color.BLACK) {
                    //2. brat noda czarny i synowie czarni
                    bro.color = color.RED;
                    node = node.parent;
                } else {
                    if (bro.right.color == color.BLACK) {
                        //3 brat noda czarny, syn brata skierowany tak jak node jest czerwony, drugi czarny
                        bro.left.color = color.BLACK;
                        bro.color = color.RED;
                        rightRotate(bro);
                        bro = node.parent.right;
                    }

                    //4.syn brata przeciwnie skierowany jest czerwony
                    bro.color = node.parent.color;
                    node.parent.color = color.BLACK;
                    bro.right.color = color.BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            } else { //symetrycznie
                bro = node.parent.left;
                if (bro.color == color.RED) {
                    //1.
                    bro.color = color.BLACK;
                    node.parent.color = color.RED;
                    rightRotate(node.parent);
                    bro = node.parent.left;
                }

                if (bro.left.color == color.BLACK && bro.right.color == color.BLACK) {
                    //2.
                    bro.color = color.RED;
                    node = node.parent;
                } else {
                    if (bro.left.color == color.BLACK) {
                        //3.
                        bro.right.color = color.BLACK;
                        bro.color = color.RED;
                        leftRotate(bro);
                        bro = node.parent.left;
                    }

                    //4.
                    bro.color = node.parent.color;
                    node.parent.color = color.BLACK;
                    bro.left.color = color.BLACK;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.color = color.BLACK;
    }

    private int minimumDepth(Node node) {
        if (node == nullLeaf) {
            return 0;
        }

        int x = minimumDepth(node.left);
        int y = minimumDepth(node.right);

        return (1 + ((x < y) ? x : y));
    }

    private int maximumDepth(Node node) {
        if (node == nullLeaf) {
            return 0;
        }

        int x = maximumDepth(node.left);
        int y = maximumDepth(node.right);

        return (1 + ((x > y) ? x : y));
    }

    private void numberOfRedNodes() {
        numberOfRedNodes(root);
        System.out.println("Liczba czerwonych: " + red);
        red = 0;
    }

    private void numberOfRedNodes(Node node) {
        if(node != nullLeaf) {
            if(node.color == color.RED) {
                this.red++;
            }
            numberOfRedNodes(node.left);
            numberOfRedNodes(node.right);
        }
    }

    private void inorder() {
        inorder(root);
    }

    private void inorder(Node root) {
        if (root == nullLeaf) return;
        inorder(root.left);
        if(root.color==color.RED) {
            System.out.println(root.key + "-R");
        } else {
            System.out.println(root.key + "-B");
        }
        inorder(root.right);
    }

    private void print() {
        System.out.println();
        print(root, 0);
    }

    private void print(Node root) {
        if(root != nullLeaf) {
            print(root.left);
            System.out.print(root.key + " ");
            print(root.right);
        }
    }

    private void print(Node root, int height){
        if(root == nullLeaf)
            return;
        print(root.right, height+1);
        if(height != 0){
            for(int i = 0; i < height - 1; i++) {
                System.out.print("|\t");
            }
            if(root.color == color.RED) {
                System.out.println("|── " + root.key+ "(r)");
            } else {
                System.out.println("|── " + root.key);
            }
        }
        else {
            System.out.println(root.key);
        }
        print(root.left, height+1);
    }



    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        int[] numbers = {23,43,16,8,22,34,11,8};
        for (int n : numbers) {
            rbTree.insert(n);
        } /*
        for (int i = 1; i < 1000; i++) {
            rbTree.insert(i);
        } */
        rbTree.print();
        System.out.println("\n--------------");
        System.out.println("Minimalna głębokość: " + rbTree.minimumDepth(rbTree.root));
        System.out.println("Maksymalna głębokość: " + rbTree.maximumDepth(rbTree.root));
        rbTree.numberOfRedNodes();
        System.out.println("--------------");
        rbTree.delete(16);
        rbTree.print();
        //rbTree.inorder();
    }
}
