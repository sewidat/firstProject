package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    Stack stack;
    PriorityQueue<AdjNode> priorityQueue;
    double y = 0;
    T realStart = null;
    int i = 0;
    int x = 0;
    Page page = new Page();
    double count;

    public ArrayList method(T start, T end) {
        //     System.out.println(i);
        y = 0;
        priorityQueue = new PriorityQueue();
        stack = new Stack();
        realStart = start;
        Node startNode = find(start);
        Node endNode = find(end);
        // System.out.println(bool);
        if (i == 0) {
            findPath(startNode, endNode);
        } else {
            aStar(startNode, endNode);
        }
        ArrayList p = new ArrayList();
        while (!stack.isEmpty()) {
            p.add(stack.pop());
        }
        System.out.println(x);
        return p;
    }


    public void findPath(Node startNode, Node endNode) {

        x++;

        if (startNode.data.compareTo(endNode.data) == 0) {
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
//                                System.out.println("father visited cant change it " + node.data);
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


    public void addAdj(T from, T to, double weight) {


        find(from).adjNodes.add(new AdjNode<T>(find(to), weight));


    }

    public double costFinder(T start, T end) {
        double cost = 0;
        Node startNode = find(start);
        for (int i = 0; i < startNode.adjNodes.size(); i++) {
            AdjNode adjNode = (AdjNode) startNode.adjNodes.get(i);

            Node node = adjNode.n1;
            if (node.data == end) {

                cost = adjNode.weight;
//                System.out.println("the cost from " + start + " to " + end + " = " + cost);
                break;
            }
        }

        return cost;
    }

    public Node find(T data) {
        Node wantedNode = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).data.compareTo(data) == 0) {
                wantedNode = arrayList.get(i);
                break;
            }
        }
        return wantedNode;
    }

    public void printArr() throws IOException {
//        System.out.println(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Node wantedNode = arrayList.get(i);
            System.out.println(i + "- " + wantedNode.data + " ----> " + wantedNode.printList());
        }
    }

    public void addNode(Node n) {
        if (n.data.compareTo(realStart) == 0) {
            System.out.println("done");
            return;
        } else {
            Node newNode = find((T) n.father);
            stack.push(newNode.data);
            addNode(newNode);
        }
    }


    public void aStar(Node startNode, Node endNode) {
        x++;
        if (startNode.data.compareTo(endNode.data) == 0) {
//            System.out.println("you are in Astaer");
            stack.add(endNode.data);
            addNode(endNode);
            return;
        } else {
            if (startNode.visited == false) {
                startNode.visited = true;
                for (int i = 0; i < startNode.adjNodes.size(); i++) {
                    AdjNode adjNode = (AdjNode) startNode.adjNodes.get(i);
                    if (adjNode.n1.visited == false) {

                        adjNode.weight = adjNode.weight + page.getD(adjNode.n1.data, startNode.data);
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
            count = page.getD(adjNode.n1.data, startNode.data);
            y = y + count;
            System.out.println(adjNode.n1.data + " selected with weight of " + adjNode.weight);
            aStar(adjNode.n1, endNode);
        }
    }

}
