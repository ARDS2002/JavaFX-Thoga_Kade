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

public class UpdateCustomerFormController implements Initializable {

    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXTextField txtSearchedCustomerAddress;

    @FXML
    private JFXTextField txtSearchedCustomerContact;

    @FXML
    private DatePicker dateSearchedCustomerDOB;

    @FXML
    private JFXTextField txtSearchedCustomerID;

    @FXML
    private JFXTextField txtSearchedCustomerName;

    @FXML
    private JFXComboBox<String> cmbSearchedCustomerTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> customerTitle = FXCollections.observableArrayList();
        String[] titleArray = {"Mr.", "Mrs.", "Miss", "Ven.", "Master"};
        Collections.addAll(customerTitle, titleArray);
        cmbSearchedCustomerTitle.setItems(customerTitle);
    }

    private void setCustomerDetail() {

        if (!txtSearchCustomer.getText().isEmpty()) {
            Customer customer = SearchCustomerController.getSearchedCustomer(txtSearchCustomer.getText());
            if (null != customer && null != UpdateCustomerController.extractCustomerName(customer.getCustomerName())) {
                txtSearchedCustomerName.setText(UpdateCustomerController.extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerID.setText(customer.getCustomerID());
                cmbSearchedCustomerTitle.setValue(customer.getCustomerTitle());
                txtSearchedCustomerName.setText(UpdateCustomerController.extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerAddress.setText(customer.getCustomerAddress());
                txtSearchedCustomerContact.setText(customer.getCustomerContactNumber());
                dateSearchedCustomerDOB.setValue(customer.getCustomerDOB());
            }
        }

    }

    private void clearFields() {
        txtSearchCustomer.clear();
        txtSearchedCustomerID.clear();
        cmbSearchedCustomerTitle.setValue(null);
        txtSearchedCustomerName.clear();
        txtSearchedCustomerAddress.clear();
        txtSearchedCustomerContact.clear();
        dateSearchedCustomerDOB.setValue(null);
    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        setCustomerDetail();
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        boolean isUpdated = UpdateCustomerController.updateCustomerDetail(new Customer(txtSearchedCustomerID.getText(),
                cmbSearchedCustomerTitle.getValue(),
                txtSearchedCustomerName.getText(),
                txtSearchedCustomerAddress.getText(),
                txtSearchedCustomerContact.getText(),
                dateSearchedCustomerDOB.getValue()));
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION);
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR);
        }
    }

    public void txtSearchCustomerOnAction(ActionEvent actionEvent) {
        setCustomerDetail();
    }
}
