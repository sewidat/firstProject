package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph<T extends Comparable<T>> {
    ArrayList<Node> arrayList = new ArrayList<>();

    public void addNodes(ArrayList<T> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = new Node(nodes.get(i));
            arrayList.add(node);
        }

    }

    Stack stack = new Stack();
    PriorityQueue<AdjNode> priorityQueue = new PriorityQueue();
    int y = 0;
    T realStart = null;

    public void method(T start, T end) {
        realStart = start;
        Node startNode = find(start);
        Node endNode = find(end);
        findPath(startNode, endNode);
        ArrayList p = new ArrayList();
        while (!stack.isEmpty()) {
            p.add(stack.pop());
        }
        System.out.println("The path from " + start + " to " + end + " is " + p.toString() + " with cost of " + y);

    }

    int x = 0;
    T lateStart = realStart;

    public void findPath(Node startNode, Node endNode) {

        x++;

        if (startNode.data == endNode.data) {
            stack.add(endNode.data);
            addNode(endNode);
            return;
        } else {
            if (startNode.visited == false) {
                startNode.visited = true;
                for (int i = 0; i < startNode.adjNodes.size(); i++) {
                    AdjNode adjNode = (AdjNode) startNode.adjNodes.get(i);
                    if (adjNode.n1.visited == false) {
                        adjNode.weight = adjNode.weight + y;
                        T data = (T) adjNode.n1.father;

                        if (data != null) {
                            Node node = find(data);
                            if (node.visited == true && (costFinder((T) node.data, (T) adjNode.n1.data) < costFinder((T) startNode.data, (T) adjNode.n1.data))) {
                                System.out.println("father visited cant change it " + node.data);
                            } else {
                                adjNode.n1.father = startNode.data;
                            }

                        } else {
                            adjNode.n1.father = startNode.data;
                        }

                        priorityQueue.add(adjNode);
                    } else {
                        continue;
                    }

                }
            }
            AdjNode adjNode = priorityQueue.poll();
            y = adjNode.weight;

            findPath(adjNode.n1, endNode);
        }
    }


    public void addAdj(T from, T to, int weight) {
        find(from).adjNodes.add(new AdjNode<>(find(to), weight));


    }

    public int costFinder(T start, T end) {
        int cost = 0;
        Node startNode = find(start);
        for (int i = 0; i < startNode.adjNodes.size(); i++) {
            AdjNode adjNode = (AdjNode) startNode.adjNodes.get(i);

            Node node = adjNode.n1;
            if (node.data == end) {

                cost = adjNode.weight;
                System.out.println("the cost from " + start + " to " + end + " = " + cost);
                break;
            }
        }

        return cost;
    }

    public Node find(T data) {
        Node wantedNode = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).data == data) {
                wantedNode = arrayList.get(i);
                break;
            }
        }
        return wantedNode;
    }

    public void printArr() {
        for (int i = 0; i < arrayList.size(); i++) {
            Node wantedNode = arrayList.get(i);
            System.out.println(wantedNode.data + " ----> " + wantedNode.printList());
        }
    }

    public void addNode(Node n) {
        if (n.data == realStart) {
            return;
        } else {
            Node newNode = find((T) n.father);
            stack.push(newNode.data);
            addNode(newNode);
        }
    }

}
