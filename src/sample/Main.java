package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    static Parent root;

    static Pane pane;
    static Pane lastPane;
    static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        primaryStage.setTitle("NAV");
        pane = new Pane();
        pane.getChildren().addAll(root);
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
    }


    public static void main(String[] args) throws IOException {
        launch(args);

    }
}
//    ArrayList arrayList;
//    final String path = "sample/map.txt";
//    File file = new File(path);
//
//    FileReader fileReader = new FileReader(file);
//    BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//    public NewMain(T start, T end) throws IOException {
//
//        method(start, end);
//    }
//
//    void method(T start, T end) throws IOException {
//        Graph graph = new Graph();
//        ArrayList arrayList = new ArrayList();
//        while (bufferedReader.readLine() != null) {
//            String[] str = bufferedReader.readLine().split(",");
//            System.out.println(Arrays.toString(str));
//        }