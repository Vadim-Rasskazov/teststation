package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    
    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("controllermain.fxml")));
        mainStage.setTitle("Test Station");
        mainStage.setX(0);
        mainStage.setY(0);
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}