package sample;

import java.util.ArrayList;

public class NewMain {
    public static void main(String[] args) {
        Graph graph = new Graph();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 4; i++) {
            arrayList.add(i);
        }
        graph.addNodes(arrayList);
//        graph.addAdj(1, 2, 3);
//        graph.addAdj(1, 3, 1);
//        graph.addAdj(2, 1, 3);
//        graph.addAdj(2, 3, 5);
//        graph.addAdj(2, 4, 2);
//        graph.addAdj(3, 1, 1);
//        graph.addAdj(3, 2, 5);
//        graph.addAdj(3, 4, 1);
//        graph.addAdj(4, 2, 2);
//        graph.addAdj(4, 3, 1);
//        graph.addAdj(4, 5, 1);
//        graph.addAdj(5, 4, 1);
        // -------------------------------------
        //        graph.addNodes(0, 1, 6);
        graph.addAdj(0, 4, 2);
        graph.addAdj(1, 0, 6);
        graph.addAdj(1, 2, 1);
        graph.addAdj(1, 4, 3);
        graph.addAdj(1, 3, 1);
        graph.addAdj(2, 1, 1);
        graph.addAdj(2, 3, 3);
        graph.addAdj(3, 1, 1);
        graph.addAdj(3, 2, 3);
        graph.addAdj(3, 4, 1);
        graph.addAdj(4, 0, 2);
        graph.addAdj(4, 1, 3);
        graph.addAdj(4, 3, 1);
        graph.printArr();
        graph.method(3, 2);
    }
}
