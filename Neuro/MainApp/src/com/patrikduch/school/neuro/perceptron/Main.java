package com.patrikduch.school.neuro.perceptron;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {


        Parent root = FXMLLoader.load(getClass().getResource("views/sample.fxml"));

        // Programaticke vytvoření gridpane uzlu
        //GridPane root = new GridPane();
        //root.setAlignment(Pos.TOP_CENTER);
        //root.setVgap(10);
        //root.setHgap(10);


        //HBox hBox = new HBox();
        //hBox.setPadding(new Insets(10, 50, 50, 50));
        //hBox.setSpacing(10);
        //Label title = new Label("Perceptron");
        //title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));

        //hBox.getChildren().add(title);


        //root.getChildren().add(hBox);



        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

        primaryStage.setTitle("Backpropagation by Patrik Duch");
        primaryStage.setScene(new Scene(root, 900, 575));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
