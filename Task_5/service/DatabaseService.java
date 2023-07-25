package Task_5.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Task_5.model.Contact;
import Task_5.utility.DatabaseUtility;

public class DatabaseService {

    DatabaseUtility databaseUtility = new DatabaseUtility();

    public boolean addContact(Contact contact) throws SQLException {
        try (
                Connection connection = databaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement("insert into Contact(name,phone_no,email) values(?,?,?) ");) {

            ps.setString(1, contact.getName());
            ps.setLong(2, contact.getPhone_no());
            ps.setString(3, contact.getEmail());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        }
        return false;

    }

    public boolean updateContact(String name, Contact contact) throws SQLException {
        try (
                Connection connection = databaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "update Contact set name = ?, phone_no = ?, email = ? where name = ? ");) {

            ps.setString(1, contact.getName());
            ps.setLong(2, contact.getPhone_no());
            ps.setString(3, contact.getEmail());
            ps.setString(4, name);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        }
        return false;
    }

    public Contact getAContact(String name) throws SQLException {
        Contact contact = new Contact();
        try (
                Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery("select * from Contact where name = " + name);
            while (rs.next()) {
                contact = new Contact(rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getString(4));
            }
            return contact;
        }

    }

    public boolean deleteAContact(String name) throws SQLException {
        try (
                Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement();) {

            int rowsaffected = statement.executeUpdate("delete from Contact where name = " + name);

            if (rowsaffected > 0) {
                return true;
            }
        }
        return false;
    }

    public int resultsetSize() throws SQLException {
        try (Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("select * from Contact ");
            int size = 0;
            while (rs.next()) {
                size++;
            }
            return size;
        }

    }

    public Contact Select(int start, int size) throws SQLException {
        try (Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE)) {

            ResultSet rs = statement.executeQuery("select * from Student ");

            Contact contact = new Contact();

            while (start <= size) {
                rs.absolute(start);
                start++;
                return contact = new Contact(rs.getInt(1),rs.getString(2), rs.getLong(3), rs.getString(4));
            }
            return contact;

        }
    }


}
