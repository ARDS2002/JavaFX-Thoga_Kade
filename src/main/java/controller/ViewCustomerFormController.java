package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {

    @FXML
    private JFXTextField txtNumberOfCustomers;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerContactNumber;

    @FXML
    private TableColumn<?, ?> colCustomerDOB;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerContactNumber.setCellValueFactory(new PropertyValueFactory<>("customerContactNumber"));
        colCustomerDOB.setCellValueFactory(new PropertyValueFactory<>("customerDOB"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));

        loadTable();
    }

    public void loadTable() {
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerObservableList.addAll(customerList);

        tblCustomer.setItems(customerObservableList);
        int numberOfCustomers = DBConnection.getInstance().getConnection().size();
        if (numberOfCustomers < 10) {
            txtNumberOfCustomers.setText("0" + numberOfCustomers);
        } else {
            txtNumberOfCustomers.setText("" + numberOfCustomers);
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

}
