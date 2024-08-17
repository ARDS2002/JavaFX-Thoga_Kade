package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    @FXML
    private JFXTextField txtSearchCustomer;
    private Stage addCustomerFormStage;
    private Stage updateCustomerFormStage;
    private Stage deleteCustomerFormStage;
    private Stage clearCustomerFormStage;
    private Stage searchCustomerFormStage;
    private Stage viewCustomerFormStage;

    private void searchCustomer() {
        if (searchCustomerFormStage == null) {
            searchCustomerFormStage = new Stage();
            try {
                if (!txtSearchCustomer.getText().isEmpty()) {
                    if (SearchCustomerController.isCustomerExist(txtSearchCustomer.getText())) {
                        SearchCustomerController.setSearchedCustomerContactOrID(txtSearchCustomer.getText());
                        searchCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/search_customer_form.fxml"))));
                        searchCustomerFormStage.setResizable(false);
                        searchCustomerFormStage.setTitle("SEARCH CUSTOMER FORM");
                        searchCustomerFormStage.setOnCloseRequest(event -> searchCustomerFormStage = null);
                        searchCustomerFormStage.show();
                    } else {
                        new Alert(Alert.AlertType.ERROR);
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            searchCustomerFormStage.toFront();
        }
    }

    public void btnAddCustomerFormOnAction(ActionEvent actionEvent) {
        if (addCustomerFormStage == null) {
            addCustomerFormStage = new Stage();
            try {
                addCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/add_customer_form.fxml"))));
                addCustomerFormStage.setResizable(false);
                addCustomerFormStage.setTitle("ADD CUSTOMER FORM");
                addCustomerFormStage.setOnCloseRequest(event -> addCustomerFormStage = null);
                addCustomerFormStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            addCustomerFormStage.toFront(); // Bring the window to the front if it's already open
        }
    }

    public void btnUpdateCustomerFormOnAction(ActionEvent actionEvent) {
        if (updateCustomerFormStage == null) {
            updateCustomerFormStage = new Stage();
            try {
                updateCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/update_customer_form.fxml"))));
                updateCustomerFormStage.setResizable(false);
                updateCustomerFormStage.setTitle("UPDATE CUSTOMER FORM");
                updateCustomerFormStage.setOnCloseRequest(event -> updateCustomerFormStage = null);
                updateCustomerFormStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            updateCustomerFormStage.toFront();
        }
    }

    public void btnDeleteCustomerFormOnAction(ActionEvent actionEvent) {
        if (deleteCustomerFormStage == null) {
            deleteCustomerFormStage = new Stage();
            try {
                deleteCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/delete_customer_form.fxml"))));
                deleteCustomerFormStage.setResizable(false);
                deleteCustomerFormStage.setTitle("DELETE CUSTOMER FORM");
                deleteCustomerFormStage.setOnCloseRequest(event -> deleteCustomerFormStage = null);
                deleteCustomerFormStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            deleteCustomerFormStage.toFront();
        }
    }

    public void btnClearCustomerFormOnAction(ActionEvent actionEvent) {
        if (clearCustomerFormStage == null) {
            clearCustomerFormStage = new Stage();
            try {
                clearCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/clear_customer_form.fxml"))));
                clearCustomerFormStage.setResizable(false);
                clearCustomerFormStage.setTitle("CLEAR CUSTOMER FORM");
                clearCustomerFormStage.setOnCloseRequest(event -> clearCustomerFormStage = null);
                clearCustomerFormStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            clearCustomerFormStage.toFront();
        }
    }

    public void btnSearchCustomerFormOnAction(ActionEvent actionEvent) {
        searchCustomer();
    }

    public void txtSearchCustomerOnAction(ActionEvent actionEvent) {
        searchCustomer();
    }

    public void btnViewCustomerFormOnAction(ActionEvent actionEvent) {
        if (viewCustomerFormStage == null) {
            viewCustomerFormStage = new Stage();
            try {
                viewCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_customer_form.fxml"))));
                viewCustomerFormStage.setResizable(false);
                viewCustomerFormStage.setTitle("VIEW CUSTOMER FORM");
                viewCustomerFormStage.setOnCloseRequest(event -> viewCustomerFormStage = null);
                viewCustomerFormStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            viewCustomerFormStage.toFront();
        }
    }

}
