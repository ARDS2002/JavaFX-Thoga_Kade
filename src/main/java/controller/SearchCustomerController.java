package controller;

import db.DBConnection;
import dto.Customer;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.Setter;

public class SearchCustomerController {
    @Setter
    @Getter
    private static String searchedCustomerContactOrID = null;

    public static String extractCustomerName(String customerName) {
        if (null != customerName && !customerName.isEmpty()) {
            String[] customerNameParts = customerName.split(" ", 2);
            return customerNameParts[1];
        }
        return null;
    }

    private static int searchCustomer(String customerContactOrCustomerID) {
        for (int i = 0; i < DBConnection.getInstance().getConnection().size(); i++) {
            if (DBConnection.getInstance().getConnection().get(i).getCustomerID().equals(customerContactOrCustomerID) || DBConnection.getInstance().getConnection().get(i).getCustomerContactNumber().equals(customerContactOrCustomerID)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isCustomerExist(String customerContactOrCustomerID) {
        for (int i = 0; i < DBConnection.getInstance().getConnection().size(); i++) {
            if (DBConnection.getInstance().getConnection().get(i).getCustomerID().equals(customerContactOrCustomerID)
                    || DBConnection.getInstance().getConnection().get(i).getCustomerContactNumber().equals(customerContactOrCustomerID)) {
                return true;
            }
        }
        return false;
    }

    public static Customer getSearchedCustomer(String customerContactOrCustomerID) {
        int index = searchCustomer(customerContactOrCustomerID);
        if (index >= 0) {
            return DBConnection.getInstance().getConnection().get(index);
        } else {
            new Alert(Alert.AlertType.ERROR);
        }
        return null;
    }

}
