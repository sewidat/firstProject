package sample;

import java.util.ArrayList;

public class Node<T extends Comparable<Node>> {
    T data;
    boolean visited = false;
    T father = null;
    ArrayList<AdjNode> adjNodes = new ArrayList<>();

    public Node(T data) {
        this.data = data;
    }

    String printList() {
        String str = "";
        for (int i = 0; i < adjNodes.size(); i++) {
            str = str + " | " + adjNodes.get(i).n1.data + " / " + adjNodes.get(i).weight;
        }
        return str;
    }
}
