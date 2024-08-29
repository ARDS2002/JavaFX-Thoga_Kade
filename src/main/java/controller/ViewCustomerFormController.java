package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.Optional;
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

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        if (!DBConnection.getInstance().getConnection().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Refresh ALL table data.\nThis may take longer time...");
            alert.setContentText("Are you sure, you want to REFRESH ALL table data?");


            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                tblCustomer.refresh();
            } else {
                alert.close();
            }
        } else {
            Alert alertInformEmpty = new Alert(Alert.AlertType.INFORMATION);
            alertInformEmpty.setTitle("Information");
            alertInformEmpty.setHeaderText("Database is EMPTY!");
            alertInformEmpty.setContentText("No data to refresh!");
            alertInformEmpty.showAndWait();
        }
    }

    public void imgReloadOnMouseClick(MouseEvent mouseEvent) {
        loadTable();
    }
}
