package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients {
    private Connection connection;

    private Scanner scanner;

    public Patients(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void AddPatient() {
        System.out.print("Enter Patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Patient gender: ");
        String gender = scanner.nextLine();

        try {

            String query = "INSERT INTO patients (name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient has been added");
            }
            else{
                System.out.println("Patient has NOT been added");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void viewPatient() {
        String query = "SELECT * FROM patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.println("Patient ID: " + id);
                System.out.println("Patient name:"+name);
                System.out.println("Patient age:"+age);
                System.out.println("Patient gender:"+gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPatient(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
