package sample;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class Page {
    Graph graph = new Graph();
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private ResourceBundle resources;

    @FXML
    private RadioButton ucs;

    @FXML
    private RadioButton aStar;
    @FXML
    private URL location;
    @FXML
    private TextArea path;

    @FXML
    private TextArea cost;

    @FXML
    void findPath(ActionEvent event) throws IOException {
        path.setWrapText(true);
        // String str = "the path is " + graph.method("ramallah", "jericho").toString() + "with cost of " + graph.y;

        path.setText("the path is \n" + graph.method("ramallah", "jericho").toString());
        cost.setText(graph.y + "");

    }

    @FXML
    void initialize() throws IOException {

        toggleGroup.getToggles().addAll(ucs, aStar);
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
