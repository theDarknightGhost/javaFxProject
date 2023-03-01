package ma.enset.presentation.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class javaFxApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try{
//            BorderPane root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
          BorderPane root = FXMLLoader.load(getClass().getResource("/fxml/GUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception ex){
           ex.printStackTrace();
        }

    }
}
