package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DashBoardFormController {

    @FXML
    private JFXTextField txtSearchCustomer;
    private Stage addCustomerFormStage;
    private Stage updateCustomerFormStage;
    private Stage deleteCustomerFormStage;
    private Stage searchCustomerFormStage;
    private Stage viewCustomerFormStage;

    private void searchCustomer() {
        String customerSearchText = txtSearchCustomer.getText().trim();

        if (!customerSearchText.isEmpty()) {
            try {
                if (SearchCustomerController.isCustomerExist(customerSearchText)) {
                    SearchCustomerController.setSearchedCustomerContactOrID(customerSearchText);
                    txtSearchCustomer.clear();

                    if (searchCustomerFormStage == null) {
                        searchCustomerFormStage = new Stage();
                        searchCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/search_customer_form.fxml"))));
                        searchCustomerFormStage.setResizable(false);
                        searchCustomerFormStage.setTitle("SEARCH CUSTOMER FORM");
                        searchCustomerFormStage.setOnCloseRequest(event -> searchCustomerFormStage = null);
                    } else {
                        // Optionally reload the scene if you want to reset the form
                        searchCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/search_customer_form.fxml"))));
                    }
                    searchCustomerFormStage.show();
                    searchCustomerFormStage.toFront();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Customer Not Found");
                    alert.setHeaderText("Customer Search Error");
                    alert.setContentText("No customer found with the provided ID or contact.");
                    alert.showAndWait();

                    // Ensure the stage is set to null to allow for new searches
                    searchCustomerFormStage = null;
                }
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Loading Form");
                alert.setHeaderText("Unable to load the search customer form.");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Input Error!");
            alert.setContentText("Please enter a customer ID or contact.");
            alert.showAndWait();
        }
    }

    /*private void searchCustomer() {
        if (searchCustomerFormStage == null) {
            searchCustomerFormStage = new Stage();
            try {
                if (!txtSearchCustomer.getText().isEmpty()) {
                    if (SearchCustomerController.isCustomerExist(txtSearchCustomer.getText())) {
                        SearchCustomerController.setSearchedCustomerContactOrID(txtSearchCustomer.getText());
                        txtSearchCustomer.clear();
                        searchCustomerFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/search_customer_form.fxml"))));
                        searchCustomerFormStage.setResizable(false);
                        searchCustomerFormStage.setTitle("SEARCH CUSTOMER FORM");
                        searchCustomerFormStage.setOnCloseRequest(event -> searchCustomerFormStage = null);
                        searchCustomerFormStage.show();
                    } else {
                        new Alert(Alert.AlertType.ERROR);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error Alert Example");
                    alert.setContentText("This is an error alert.");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            searchCustomerFormStage.toFront();
        }
    }*/

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure, you want to CLEAR ALL CUSTOMER DATA?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (!DBConnection.getInstance().getConnection().isEmpty()) {
                DBConnection.getInstance().getConnection().clear();
            } else {
                Alert alertInformEmpty = new Alert(Alert.AlertType.INFORMATION);
                alertInformEmpty.setTitle("Information");
                alertInformEmpty.setHeaderText(null);
                alertInformEmpty.setContentText("No data to clear!");
                alertInformEmpty.showAndWait();
            }
        } else {
            alert.close();
        }
    }

    public void txtSearchCustomerOnAction(ActionEvent actionEvent) {
        searchCustomer();
    }

    public void imgSearchCustomerOnMouseClicked(MouseEvent mouseEvent) {
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

    public void btnExitPlatformOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure, you want to EXIT?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            Platform.exit();
        } else {
            alert.close();
        }
    }

}
