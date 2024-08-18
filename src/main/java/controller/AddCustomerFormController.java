package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCustomerTitle;

    @FXML
    private DatePicker dateCustomerDOB;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerContactNumber;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> customerTitle = FXCollections.observableArrayList();
        String[] titleArray = {"Mr.", "Mrs.", "Miss", "Ven.", "Master"};
        Collections.addAll(customerTitle, titleArray);
        cmbCustomerTitle.setItems(customerTitle);
    }

    private void clearFields() {
        txtCustomerID.clear();
        cmbCustomerTitle.setValue(null);
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerContactNumber.clear();
        dateCustomerDOB.setValue(null);
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        try {
            if (null != cmbCustomerTitle.getValue()
                    && null != txtCustomerName.getText()
                    && null != txtCustomerAddress.getText()
                    && null != txtCustomerContactNumber.getText()
                    && null != dateCustomerDOB.getValue()) {
                boolean isValidCustomer = AddCustomerController.addCustomer(new Customer(null, cmbCustomerTitle.getValue(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCustomerContactNumber.getText(), dateCustomerDOB.getValue()));
                if (isValidCustomer) {
                    txtCustomerID.setText(SearchCustomerController.getSearchedCustomer(txtCustomerContactNumber.getText()).getCustomerID());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    // Set the title of the alert dialog
                    alert.setTitle("THOGAKADE");
                    // Set the header text (can be null to hide the header)
                    alert.setHeaderText(null);
                    // Set the content text
                    alert.setContentText("\t\tWELCOME TO THOGA-KADE !!!" +
                            "\n\nCustomer Added Successfully !!!" +
                            "\nYour Thoga-Kade ID : " + txtCustomerID.getText());
                    // Show the alert and wait for the user's response
                    alert.showAndWait();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

}
