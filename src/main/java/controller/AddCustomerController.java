package controller;

import db.DBConnection;
import dto.Customer;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.Period;

public class AddCustomerController {
    private static int information;

    public static boolean addCustomer(Customer newCustomer) {
        String customerID;
        if (null != newCustomer) {
            if (isValidBirthday(newCustomer.getCustomerDOB())) {
                customerID = createCustomerID(newCustomer.getCustomerDOB().getYear());
                newCustomer.setCustomerID(customerID);
            } else {
                new Alert(Alert.AlertType.ERROR);
                return false;
            }
            if (!isValidContactNumber(newCustomer.getCustomerContactNumber())) {
                new Alert(Alert.AlertType.ERROR);
                return false;
            }
            if (null != newCustomer.getCustomerID()) {
                DBConnection.getInstance().getConnection().add(newCustomer);
                new Alert(Alert.AlertType.NONE);
                return true;
            }
        }
        return false;
    }

    private static String createCustomerID(int year) {
        if (DBConnection.getInstance().getConnection().isEmpty()) {
            return year + "0001";
        }
        int lastCustomerIndex = DBConnection.getInstance().getConnection().size() - 1;
        int index = Integer.parseInt(DBConnection.getInstance().getConnection().get(lastCustomerIndex).getCustomerID().substring(4));
        return String.format((year + "%04d"), index + 1);
    }

    private static boolean isValidBirthday(LocalDate customerDOB) {
        if (customerDOB.isAfter(LocalDate.now())) {
            return false;
        }
        int age = Period.between(customerDOB, LocalDate.now()).getYears();
        return age >= 10 && age <= 100;
    }

    private static boolean isValidContactNumber(String contactNumber) {
        if (contactNumber.charAt(0) != '0' || contactNumber.length() != 10) {
            information = 0;
            return false;
        } else {
            for (int i = 1; i < contactNumber.length(); i++) {
                if (!Character.isDigit(contactNumber.charAt(i))) {
                    information = 1;
                    return false;
                } else if (!isDuplicateContactNumber(contactNumber)) {
                    information = 2;
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isDuplicateContactNumber(String contactNumber) {
        for (int i = 0; i < DBConnection.getInstance().getConnection().size(); i++) {
            if (DBConnection.getInstance().getConnection().get(i).getCustomerContactNumber().equals(contactNumber)) {
                return false;
            }
        }
        return true;
    }

}
