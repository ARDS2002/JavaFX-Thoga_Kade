package controller;

import db.DBConnection;
import dto.Customer;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.Period;

public class UpdateCustomerController {
    private static int information;

    public static String extractCustomerName(String customerName) {
        if (null != customerName && !customerName.isEmpty()) {
            String[] customerNameParts = customerName.split(" ", 2);
            return customerNameParts[1];
        }
        return null;
    }

    public static boolean updateCustomerDetail(Customer updatedCustomer) {
        String newCustomerID;
        if (null != updatedCustomer) {
            Customer customer = SearchCustomerController.getSearchedCustomer(updatedCustomer.getCustomerID());
            String updatedCustomerContact = updatedCustomer.getCustomerContactNumber();
            LocalDate updatedCustomerDOB = updatedCustomer.getCustomerDOB();
            if (null != customer) {
                if (!customer.getCustomerDOB().equals(updatedCustomerDOB)) {
                    if (isValidBirthday(updatedCustomerDOB)) {
                        if (customer.getCustomerDOB().getYear() != updatedCustomerDOB.getYear()) {
                            newCustomerID = createCustomerID(updatedCustomerDOB.getYear());
                            customer.setCustomerID(newCustomerID);
                        }
                        customer.setCustomerDOB(updatedCustomerDOB);
                    } else {
                        new Alert(Alert.AlertType.ERROR);
                        return false;
                    }
                }
                if (!customer.getCustomerContactNumber().equals(updatedCustomerContact)
                        && !isValidContactNumber(updatedCustomerContact)) {
                    new Alert(Alert.AlertType.ERROR);
                    return false;
                }
                if (!customer.getCustomerTitle().equalsIgnoreCase(updatedCustomer.getCustomerTitle())) {
                    customer.setCustomerTitle(updatedCustomer.getCustomerTitle());
                }
                String currentName = extractCustomerName(customer.getCustomerName());
                String updatedName = extractCustomerName(updatedCustomer.getCustomerName());
                if (currentName != null && updatedName != null && !currentName.equalsIgnoreCase(updatedName)) {
                    customer.setCustomerName(updatedName);
                }
                if (!customer.getCustomerAddress().equalsIgnoreCase(updatedCustomer.getCustomerAddress())) {
                    customer.setCustomerAddress(updatedCustomer.getCustomerAddress());
                }
                if (!customer.getCustomerContactNumber().equals(updatedCustomer.getCustomerContactNumber())) {
                    customer.setCustomerContactNumber(updatedCustomer.getCustomerContactNumber());
                }
                new Alert(Alert.AlertType.NONE);
                return true;
            }
            return false;
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
