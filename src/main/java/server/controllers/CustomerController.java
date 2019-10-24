package server.controllers;

import server.models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    private static DatabaseController dbCon;

    public static Customer getCustomer(int accountNo) {
        if (dbCon == null) {
            dbCon = new DatabaseController();
        }
        String sql = "SELECT * FROM customers WHERE account_no = ?";
        PreparedStatement ps = dbCon.prepare(sql);
        Customer customer = null;
        try {
            ps.setInt(1, accountNo);
            ResultSet rs = dbCon.executePreparedStatementQuery(ps);
            rs.next();
            customer = new Customer(rs.getString("name"), rs.getInt("balance"), rs.getInt("account_no"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public static ArrayList<Customer> getCustomers() {

        // Check for DB connection
        if (dbCon == null) {
            dbCon = new DatabaseController();
        }

        // Build SQL
        String sql = "SELECT * FROM customers";

        // Do the query and initialyze an empty list for use if we don't get results
        ResultSet rs = dbCon.query(sql);
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try {
            // Loop through DB Data
            while (rs.next()) {
                Customer customer =
                        new Customer(
                                rs.getString("name"),
                                rs.getInt("balance"),
                                rs.getInt("account_no"));
                // Add element to list
                customers.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Return the list of users
        return customers;
    }

    public static Customer createCustomer(Customer customer) {
        if (dbCon == null) {
            dbCon = new DatabaseController();
        }
        String sql = "INSERT INTO customers(name, balance) VALUES (?, ?);";
        PreparedStatement ps = dbCon.prepare(sql);
        try {
            ps.setString(1, customer.getName());
            ps.setInt(2, customer.getBalance());
            if(dbCon.executePreparedStatementUpdate(ps)) {
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer updateCustomer(Customer customer) {
        if (dbCon == null) {
            dbCon = new DatabaseController();
        }
        String sql = "UPDATE customers SET name = ?, balance = ? WHERE account_no = ?;";
        PreparedStatement ps = dbCon.prepare(sql);
        try {
            ps.setString(1, customer.getName());
            ps.setInt(2, customer.getBalance());
            ps.setInt(3, customer.getAccountNo());
            if(dbCon.executePreparedStatementUpdate(ps)) {
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
