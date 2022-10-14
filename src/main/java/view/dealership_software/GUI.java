package view.dealership_software;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage mainWindow) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainGUI.fxml"));
        Scene scene =  new Scene(root);
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch();
    }
}