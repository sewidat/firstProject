package sample;

import java.io.*;
import java.util.ArrayList;

public class PageBack {
    ArrayList arrayList;
    Graph graph = new Graph();

    public PageBack() throws IOException {
        final String path = System.getProperty("user.dir") + "/src/sample/map.txt";
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList arrayList1 = new ArrayList();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split(",");
            String str1 = tokens[0];
            String str2 = tokens[1];
            if (!arrayList1.contains(str1)) {
                arrayList1.add(str1.trim());

            }
            if (!arrayList1.contains(str2)) {
                arrayList1.add(str2.trim());
            }

        }
        graph.addNodes(arrayList1);
        //   bufferedReader.close();
        FileReader fileReader2 = new FileReader(file);
        BufferedReader bufferedReader1 = new BufferedReader(fileReader2);
        String line2;
        while ((line2 = bufferedReader1.readLine()) != null) {
            String[] tokens = line2.split(",");
            //   System.out.println(tokens[2]);
            graph.addAdj(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
            graph.addAdj(tokens[1], tokens[0], Double.parseDouble(tokens[2]));
        }

        graph.printArr();

    }
}