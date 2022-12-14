package Controller.GUIControllers;

import Controller.CommandManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import view.dealership_software.GUI;

public class transferGUIController implements Initializable {

    @FXML
    private Label transfer_From_InvalidDealerID;
    @FXML
    private Label transfer_To_InvalidDealerID;
    @FXML
    private Label transfer_InvalidCarID;
    @FXML
    private Label transfer_Success;
    @FXML
    private TextField transfer_From_DealerID;
    @FXML
    private TextField transfer_To_DealerID;
    @FXML
    private TextField transfer_CarID;
    @FXML
    private Button transfer;
    @FXML
    private TextField DealerIDField;

    CommandManager cmds = new CommandManager();

    public void transferCar(ActionEvent event) {

        String from = transfer_From_DealerID.getText();
        String carID = transfer_CarID.getText();
        String to = transfer_To_DealerID.getText();

        boolean [] outcome = cmds.transferCar(from, carID, to);

        //error booleans
        boolean success = outcome[0];
        boolean invalid_From_DealerID = outcome[1];
        boolean invalid_CarID = outcome[2];
        boolean invalid_TO_DealerID = outcome[3];
        boolean invalid_To_DealerClosed = outcome[4];

        //error / success message

        //hide all error / success messages
        transfer_Success.setVisible(false);
        transfer_From_InvalidDealerID.setVisible(false);
        transfer_InvalidCarID.setVisible(false);
        transfer_To_InvalidDealerID.setVisible(false);

        //display success
        if (success) {

            transfer_Success.setVisible(true);
        }

        //display invalid from dealer id
        if (invalid_From_DealerID) {
            
            transfer_From_InvalidDealerID.setVisible(true);
        }

        //display invalid carID
        if (invalid_CarID) {

            transfer_InvalidCarID.setVisible(true);
        }

        //display invalid to dealer id
        if (invalid_TO_DealerID) {

            transfer_To_InvalidDealerID.setText("Invalid dealer id");
            transfer_To_InvalidDealerID.setVisible(true);
        }

        //display if toDealer is closed
        if (invalid_To_DealerClosed) {

            transfer_To_InvalidDealerID.setText("Dealer Closed");
            transfer_To_InvalidDealerID.setVisible(true);
        }
    }

    public void switchToMainMenuGUI(ActionEvent event) throws IOException {

        FXMLLoader root = new FXMLLoader(GUI.class.getResource("mainMenuGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram() {

        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}