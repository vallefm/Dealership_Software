package Controller.GUIControllers;

import Controller.Commands;
import Models.Dealer;
import Models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import view.dealership_software.GUI;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;

public class mainMenuGUIController implements Initializable {

    //Add car text fields
    @FXML
    private TextField addCarMake;
    @FXML
    private TextField addCarModel;
    @FXML
    private TextField addCarDID;
    @FXML
    private TextField addCarID;
    @FXML
    private TextField addCarType;
    @FXML
    private TextField addCarPrice;



   //list
    @FXML
    private ObservableList<Object> carList1 = FXCollections.observableArrayList();
    @FXML
    private ListView carList;

    private Commands cmds = new Commands();

    public void readJson(ActionEvent event) throws IOException, ParserConfigurationException, SAXException {
        cmds.readJSON();
    }

    public void addCar(ActionEvent event) {
        String  carMake = addCarMake.getText();
        String carModel = addCarModel.getText();
        String carDID = addCarDID.getText();
        String carID = addCarID.getText();
        String carType = addCarType.getText();
        String carPrice = addCarPrice.getText();
        cmds.addCarGUI(carMake, carModel, carDID, carID, carType, carPrice);
    }

    public void loadList(ActionEvent event) throws IOException {
        for (Dealer d : cmds.listOfDealers) {
            for(Vehicle i: d.getListOfCarsAtDealer()){
                //This boolean serves to make sure that we do not add duplicates to list
                boolean b = carList1.contains("Dealer ID: " + i.getDealership_id() + " | Car ID: " + i.getVehicle_id() + " | Car Price: " + i.getPrice() + " | Car Acquisition Date: " + Instant.ofEpochMilli(i.getAcquisition_date()) + " | vehicle type: " + i.getVehicle_type() + " | vehicle manufacturer: " + i.getVehicle_manufacturer() + " | vehicle model: " + i.getVehicle_model());
                if (b == false) {
                    carList1.add("Dealer ID: " + i.getDealership_id() + " | Car ID: " + i.getVehicle_id() + " | Car Price: " + i.getPrice() + " | Car Acquisition Date: " + Instant.ofEpochMilli(i.getAcquisition_date()) + " | vehicle type: " + i.getVehicle_type() + " | vehicle manufacturer: " + i.getVehicle_manufacturer() + " | vehicle model: " + i.getVehicle_model());
                }
            }
        }


        carList.setItems(carList1);

    }
    public void switchToDealerGUI(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("DealerGUI.fxml"));
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("DealerGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTransferGUI(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("transferGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLoanGUI(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("loanCarGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}