package Task_3.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Task_3.model.Student;
import Task_3.utility.DatabaseUtility;

public class DatabaseService {

    DatabaseUtility databaseUtility = new DatabaseUtility();

    public boolean addStudent(Student student) throws SQLException {
        try (
                Connection connection = databaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement("insert into Student(name,roll_no,grade) values(?,?,?) ");) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getRoll_no());
            ps.setString(3, student.getGrade());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        }
        return false;

    }

    public boolean updateStudent(int roll_no, Student student) throws SQLException {
        try (
                Connection connection = databaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "update Student set name = ?, roll_no = ?, grade = ? where roll_no = ? ");) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getRoll_no());
            ps.setString(3, student.getGrade());
            ps.setInt(4, roll_no);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        }
        return false;
    }

    public Student getAStudent(int roll_no) throws SQLException {
        Student student = new Student();
        try (
                Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery("select * from Student where roll_no = " + roll_no);
            while (rs.next()) {
                student = new Student(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4));
            }
            return student;
        }

    }

    public boolean deleteAStudent(int roll_no) throws SQLException {
        try (
                Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement();) {

            int rowsaffected = statement.executeUpdate("delete from Student where roll_no = " + roll_no);

            if (rowsaffected > 0) {
                return true;
            }
        }
        return false;
    }

    public int resultsetSize() throws SQLException {
        try (Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("select * from Student ");
            int size = 0;
            while (rs.next()) {
                size++;
            }
            return size;
        }

    }

    public Student Select(int start, int size) throws SQLException {
        try (Connection connection = databaseUtility.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE)) {

            ResultSet rs = statement.executeQuery("select * from Student ");

            Student student = new Student();

            while (start <= size) {
                rs.absolute(start);
                start++;
                return student = new Student(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4));
            }
            return student;

        }
    }


}
