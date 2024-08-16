package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class DeleteCustomerFormController extends SearchCustomerController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbSearchedCustomerTitle;

    @FXML
    private DatePicker dateSearchedCustomerDOB;

    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXTextField txtSearchedCustomerAddress;

    @FXML
    private JFXTextField txtSearchedCustomerContact;

    @FXML
    private JFXTextField txtSearchedCustomerID;

    @FXML
    private JFXTextField txtSearchedCustomerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> customerTitle = FXCollections.observableArrayList();
        String[] titleArray = {"Mr.", "Mrs.", "Miss", "Ven.", "Master"};
        Collections.addAll(customerTitle, titleArray);
        cmbSearchedCustomerTitle.setItems(customerTitle);
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
    void btnDeleteCustomerOnAction(ActionEvent event) {
        if (!txtSearchedCustomerID.getText().isEmpty()) {
            Customer customer = getSearchedCustomer(txtSearchedCustomerID.getText());
            if (null != customer) {
                DBConnection.getInstance().getConnection().remove(customer);
                clearFields();
            }
        }
    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        if (!txtSearchCustomer.getText().isEmpty() && isCustomerExist(txtSearchCustomer.getText())) {
            Customer customer = getSearchedCustomer(txtSearchCustomer.getText());
            if (null != customer && null != extractCustomerName(customer.getCustomerName())) {
                txtSearchedCustomerName.setText(extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerID.setText(customer.getCustomerID());
                cmbSearchedCustomerTitle.setValue(customer.getCustomerTitle());
                txtSearchedCustomerName.setText(extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerAddress.setText(customer.getCustomerAddress());
                txtSearchedCustomerContact.setText(customer.getCustomerContactNumber());
                dateSearchedCustomerDOB.setValue(customer.getCustomerDOB());
            }
        }
    }

}
