package jd_prac;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        //  createTable();
        //  addJD("asan", 25);
        //  getCount();
        //   getAllStudents();
        //   getList();
        deleteStudentByID(1);

    }


    public static void createTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS students(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR (50) NOT NULL," +
                "age INTEGER);";
        try (Connection connection = JD_PAK.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("school");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    public static void addJD(String name, int age) {
        String SQL = "INSERT  INTO students(name, age)" +
                "VALUES(?,?)";
        try (Connection connection = JD_PAK.connection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public static int getCount() {
        int count = 0;
        String SQL = "SELECT count(*) FROM students";
        try (Connection connection = JD_PAK.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return count;
    }

    public static void getAllStudents() {
        String SQL = " SELECT * FROM students";
        try (Connection con = JD_PAK.connection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt(1));
                System.out.println("name: " + resultSet.getString("name"));
                System.out.println("age: " + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static List<Student> getList() {
        String SQL = "SELECT * FROM  students";
        List<Student> students = new ArrayList<>();
        try (Connection con = JD_PAK.connection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Student student = new Student();
                student.getId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    public static void deleteStudentByID(int id) {
        String SQL = "DELETE FROM students WHERE id  = ?";
        try (Connection connection = JD_PAK.connection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

}
