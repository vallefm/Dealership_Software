module view.dealership_software {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;


    opens view.dealership_software to javafx.fxml;
    exports view.dealership_software;
}