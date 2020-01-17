public class CDLList {

    class Node {
        Node next;
        Node prev;
        String word;

        Node(String data) {
            next = null;
            prev = null;
            this.word = data;
        }
    }

    public void insertFront(String word, Node x) {
        Node newNode = new Node(word);
        newNode.next = x.next;
        newNode.prev = x;
        x.next.prev = newNode;
        x.next = newNode;
    }

    public void insertBack(String word, Node x) {
        Node newNode = new Node(word);
        newNode.next = x.prev.next;
        newNode.prev = x.prev;
        x.prev.next = newNode;
        x.prev = newNode;
    }

    public void print(Node x) {
        String output = "";
        /*for (Node newNode = sentinel.next; newNode != sentinel; newNode = newNode.next) {
            output += newNode.data + " -> ";
        } */
        Node newNode = x.next;
        while (newNode != x) {
            output += newNode.word + " ";
            newNode = newNode.next;
        }
        System.out.println(output);
    }

    public Node find(String word2, Node x) {
        Node newNode = x.next;
        if(newNode == null) {
            return null;
        } else {
            while (newNode != x) {
                if(newNode.word.equals(word2)) {
                    return newNode;
                }
                newNode = newNode.next;
            }
            return null;
        }
    }

    public void delete(String word, Node x) {
        Node newNode;
        if((newNode = find(word,x)) != null) {
            while ((newNode= find(word,x))!=null) {
                newNode.prev.next = newNode.next;
                newNode.next.prev = newNode.prev;
            }
        } else {
            System.out.println("Nie ma elementu do usuniecia.");
        }
    }

    public void clear(Node x) {
        Node newNode = x.next;
        while (newNode != x) {
            newNode.prev.next = newNode.next;
            newNode.next.prev = newNode.prev;
            newNode = newNode.next;
        }
        x = null;
    }

    public Node unique(Node x) {
        Node sentinel2 = new Node(null);
        sentinel2.prev = sentinel2.next = sentinel2;
        Node newNode = x.next;
        while (newNode.word != null) {
            if(find(newNode.word,sentinel2) == null) {
                insertBack(newNode.word,sentinel2);
            }
            newNode = newNode.next;
        }
        return sentinel2;
    }

    public Node merge(Node s1, Node s2) {
        Node sentinel3 = new Node(null);
        sentinel3.prev = sentinel3.next = sentinel3;

        sentinel3.next = s1.next;
        s1.next.prev = sentinel3;

        s1.prev.next = s2.next;
        s2.next.prev = s1.prev;

        s2.prev.next = sentinel3;
        sentinel3.prev = s2.prev;

        s1 = s2 = null;
        return sentinel3;
    }

    public static void main(String[] args) {
        CDLList list = new CDLList();
        CDLList.Node sentinel1 = list.new Node(null);
        sentinel1.next = sentinel1.prev = sentinel1;
        System.out.println("------LISTA------");
        list.insertFront("Pierwszy", sentinel1);
        list.insertFront("Drugi", sentinel1);
        list.insertFront("Trzeci", sentinel1);
        list.insertFront("Czwarty", sentinel1);
        list.insertFront("Pierwszy", sentinel1);
        list.insertFront("Kwiat",sentinel1);
        list.print(sentinel1);
        System.out.println("------BEZ POWTORZEN------");
        Node list2 = list.unique(sentinel1);
        list.print(list2);
        System.out.println("------DWIE POŁĄCZONE------");
        Node list3 = list.merge(sentinel1,list2);
        list.print(list3);
    }
}


