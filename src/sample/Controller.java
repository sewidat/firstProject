package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private TextField start;

    @FXML
    private TextField end;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label l0;

    @FXML
    private Label l1;

    @FXML
    private Label l4;

    @FXML
    private Label l3;

    @FXML
    private Label l2;
    ArrayList<Label> labels = new ArrayList<>();
    int x = 0;
    int y = 0;

    @FXML
    void doSomething(ActionEvent event) throws Exception {

        NewMain newMain = new NewMain(Integer.parseInt(start.getText()), Integer.parseInt(end.getText()));
        y = Main.pane.getChildren().size();
        if (x > 0) {
            Main.pane.getChildren().remove(1, Main.pane.getChildren().size());

        }
        for (int i = 0; i < newMain.arrayList.size() - 1; i++) {
            Label startLabel = null, endLabel = null;
            int x = (int) newMain.arrayList.get(i);
            int y = (int) newMain.arrayList.get(i + 1);
            String start = "l" + x;
            String end = "l" + y;
            startLabel = (Label) Main.scene.lookup("#" + start);
            endLabel = (Label) Main.scene.lookup("#" + end);
            //   System.out.println("start " + start + " ,end " + end);
            LineTo Lineto = new LineTo(startLabel.getLayoutX(), startLabel.getLayoutY());
            MoveTo moveto = new MoveTo(endLabel.getLayoutX(), endLabel.getLayoutY());
            Path path = new Path(moveto, Lineto);
            path.setFill(Color.BLACK);
            path.setStrokeWidth(2);
            Main.pane.getChildren().add(path);
        }
        y = Main.pane.getChildren().size();

        x++;

    }


}
//        Main.root = FXMLLoader.load(getClass().getResource("page.fxml"));
////        Main.pane.getChildren().clear();
////        Main.pane.getChildren().addAll(Main.root);
////        Main.scene = new Scene(Main.pane);
//        Main.stage.hide();
//        Stage stage = new Stage();
