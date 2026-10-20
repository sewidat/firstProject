package sample;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Page<T> {
    Graph graph;
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private ResourceBundle resources;
    @FXML
    private RadioButton ucs;
    @FXML
    private ComboBox<String> from;
    @FXML
    private ComboBox<String> to;
    @FXML
    private RadioButton aStar;
    @FXML
    private URL location;
    @FXML
    private TextArea path;
    @FXML
    private TextArea cost;
    @FXML
    private AnchorPane wp;
    @FXML
    private Circle ramallah;

    @FXML
    private Circle jericho;

    @FXML
    private Circle hebron;

    @FXML
    private Circle bayt_sahur;

    @FXML
    private Circle bayt_jala;

    @FXML
    private Circle bethlehem;

    @FXML
    private Circle isawiya;

    @FXML
    private Circle nablus;

    @FXML
    private Circle nazareth;

    @FXML
    private Circle tiberias;

    @FXML
    private Circle safad;

    @FXML
    private Circle haifa;

    @FXML
    private Circle acre;

    @FXML
    private Circle ras_alnakora;

    @FXML
    private Circle caesarea;

    @FXML
    private Circle tulkarm;

    @FXML
    private Circle qalqilya;

    @FXML
    private Circle jaffa;

    @FXML
    private Circle lod;

    @FXML
    private Circle ramla;

    @FXML
    private Circle jammain;

    @FXML
    private Circle ascalon;

    @FXML
    private Circle beit_ummar;

    @FXML
    private Circle alshyoukh;

    @FXML
    private Circle dheisha;

    @FXML
    private Circle sair;

    @FXML
    private Circle al_aroub;

    @FXML
    private Circle etcion;

    @FXML
    private Circle nahalin;

    @FXML
    private Circle beit_safafa;

    @FXML
    private Circle at_tur;

    @FXML
    private Circle ein_karem;

    @FXML
    private Circle dair_yassin;

    @FXML
    private Circle shuafat;

    @FXML
    private Circle beit_iksa;

    @FXML
    private Circle beit_hanina;

    @FXML
    private Circle bir_nabala;

    @FXML
    private Circle qalandiya;

    @FXML
    private Circle al_bireh;

    @FXML
    private Circle kafr_aqab;

    @FXML
    private Circle al_ram;

    @FXML
    private Circle anata;

    @FXML
    private Circle jerusalem;

    @FXML
    private Circle beitunia;

    @FXML
    private Circle hizma;

    @FXML
    private Circle salfit;

    @FXML
    private Circle balata;

    @FXML
    private Circle asira_ash_shamaliya;

    @FXML
    private Circle beisan;

    @FXML
    private Circle umm_al_fahm;

    @FXML
    private Circle beit_furik;

    @FXML
    private Circle malha;

    @FXML
    private Circle khan_al_ahmar;

    @FXML
    private Circle halhul;

    @FXML
    private Circle gaza;

    @FXML
    private Circle falujeh;

    @FXML
    private Circle bayt_ula;

    @FXML
    private Circle qabatiya;

    @FXML
    private Circle jenin;
    @FXML
    private Circle sur_baher;
    @FXML
    private Circle abu_deis;

    @FXML
    private Circle al_eizariya;

    @FXML
    private Circle ta3amra;
    private Label l2;
    ArrayList<Label> labels = new ArrayList<>();
    int x = 0;
    int y = 0;

    @FXML
    void findPath(ActionEvent event) throws IOException {
        cost.setText("");
        path.setText("");
        path.setWrapText(true);
        RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (x > 0) {
            startMethod();
            wp.getChildren().remove(1, wp.getChildren().size());
        }
        if (radioButton.getText().trim().compareTo("UCS") == 0) {
            graph.i = 0;

        } else if (radioButton.getText().trim().compareTo("A*") == 0) {
            graph.i = 1;
        }
        ArrayList arr = graph.method(from.getValue(), to.getValue());
        path.setText("the path is \n" + arr.toString());
        if (graph.i == 1) {
            cost.setText(graph.y + "");
        }
        if (graph.i == 0) {
            cost.setText((int) graph.y + "");
        }

        //   System.out.println(graph.y);
        y = arr.size();
        for (int i = 0; i < arr.size() - 1; i++) {
            Node startLabel = null, endLabel = null;
            String x = (String) arr.get(i);
            String y = (String) arr.get(i + 1);
            startLabel = (Circle) Main.scene.lookup("#" + x);
            endLabel = (Circle) Main.scene.lookup("#" + y);
            LineTo Lineto = new LineTo(startLabel.getLayoutX(), startLabel.getLayoutY());
            MoveTo moveto = new MoveTo(endLabel.getLayoutX(), endLabel.getLayoutY());
            Path path = new Path(moveto, Lineto);
            path.setFill(Color.BLACK);
            path.setStrokeWidth(2);
            wp.getChildren().add(path);

        }
        y = wp.getChildren().size();

        x++;

    }

    ArrayList arrayList1;

    @FXML
    void initialize() throws IOException {

        toggleGroup.getToggles().addAll(ucs, aStar);
        startMethod();
        ArrayList arrayList = arrayList1;
        arrayList.sort(Comparator.comparing(Object::toString));

        from.getItems().addAll(arrayList);
        to.getItems().addAll(arrayList);
    }

    void startMethod() throws IOException {

        graph = new Graph();
        System.out.println(System.getProperty("user.dir"));
        final String path = "./map.txt";
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        arrayList1 = new ArrayList();
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

        FileReader fileReader2 = new FileReader(file);
        BufferedReader bufferedReader1 = new BufferedReader(fileReader2);
        String line2;
        while ((line2 = bufferedReader1.readLine()) != null) {
            String[] tokens = line2.split(",");
            //   System.out.println(tokens[2]);
            graph.addAdj(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
            graph.addAdj(tokens[1], tokens[0], Double.parseDouble(tokens[2]));
        }

    }

    public double getD(T start, T end) {
        //   System.out.println(start + " " + end);
        Circle startLabel = (Circle) Main.scene.lookup("#" + start);
        Circle endLabel = (Circle) Main.scene.lookup("#" + end);
        double d = Math.sqrt(Math.pow((startLabel.getLayoutX() - endLabel.getLayoutX()), 2) + Math.pow((startLabel.getLayoutY() - endLabel.getLayoutY()), 2)) * 0.04;
        System.out.println("the cost from " + start + " to " + end + " = " + d);
        return d;
    }

}
