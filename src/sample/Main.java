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
