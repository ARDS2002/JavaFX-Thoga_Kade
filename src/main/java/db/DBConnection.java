package db;

import dto.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static DBConnection instance;
    private final List<Customer> connection;

    private DBConnection() {
        connection = new ArrayList<>();
    }

    public List<Customer> getConnection() {
        return this.connection;
    }

    public static DBConnection getInstance() {
        if (null == instance) {
            instance = new DBConnection();
        }
        return instance;
    }
}
