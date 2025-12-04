package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {

    private Connection connection;

   // private Scanner scanner;

    public Doctors(Connection connection) {
        this.connection = connection;
//        this.scanner = scanner;
    }

    public void ViewDoctors(){
        String query = "SELECT * FROM doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.println("Doctor ID: " + id);
                System.out.println("Doctor Name: " + name);
                System.out.println("Doctor Specialization: " + specialization);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public  boolean checkdoctors(int id){
        String query = "SELECT * FROM doctors where id=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
