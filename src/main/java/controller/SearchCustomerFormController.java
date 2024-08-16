package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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

public class SearchCustomerFormController implements Initializable {

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
        searchedCustomerDetail();
    }

    private void searchedCustomerDetail() {
        if (SearchCustomerController.getSearchedCustomerContactOrID() != null) {
            Customer customer = SearchCustomerController.getSearchedCustomer(SearchCustomerController.getSearchedCustomerContactOrID());
            if (null != customer && null != SearchCustomerController.extractCustomerName(customer.getCustomerName())) {
                txtSearchedCustomerName.setText(SearchCustomerController.extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerID.setText(customer.getCustomerID());
                cmbSearchedCustomerTitle.setValue(customer.getCustomerTitle());
                txtSearchedCustomerName.setText(SearchCustomerController.extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerAddress.setText(customer.getCustomerAddress());
                txtSearchedCustomerContact.setText(customer.getCustomerContactNumber());
                dateSearchedCustomerDOB.setValue(customer.getCustomerDOB());
                SearchCustomerController.setSearchedCustomerContactOrID(null);
            }
        }
    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        if (!txtSearchCustomer.getText().isEmpty() && SearchCustomerController.isCustomerExist(txtSearchCustomer.getText())) {
            Customer customer = SearchCustomerController.getSearchedCustomer(txtSearchCustomer.getText());
            if (null != customer && null != SearchCustomerController.extractCustomerName(customer.getCustomerName())) {
                txtSearchedCustomerName.setText(SearchCustomerController.extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerID.setText(customer.getCustomerID());
                cmbSearchedCustomerTitle.setValue(customer.getCustomerTitle());
                txtSearchedCustomerName.setText(SearchCustomerController.extractCustomerName(customer.getCustomerName()));
                txtSearchedCustomerAddress.setText(customer.getCustomerAddress());
                txtSearchedCustomerContact.setText(customer.getCustomerContactNumber());
                dateSearchedCustomerDOB.setValue(customer.getCustomerDOB());
            }
        }
    }

}
