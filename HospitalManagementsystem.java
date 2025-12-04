package HospitalManagementSystem;


import java.sql.*;
import java.util.Scanner;

public class HospitalManagementsystem {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/hospital";
    private static final String username = "root";
    private static final String password = "manish@2004";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Patients patients = new Patients(connection, scanner);
            Doctors doctors = new Doctors(connection);
            while(true){
                System.out.println("hospital managementsystem");
                System.out.println("1. Add Patient");
                System.out.println("2. view patients");
                System.out.println("3. view doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.print("Enter your choice : ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 1:
                        patients.AddPatient();
                        System.out.println();
                        Thread.sleep(5000);
                        break;

                        case 2:
                            patients.viewPatient();
                            System.out.println();
                            Thread.sleep(5000);
                            break;

                            case 3:
                                doctors.ViewDoctors();
                                System.out.println();
                                Thread.sleep(5000);
                                break;

                                case 4:
                                    Bookappointment(connection,scanner,doctors,patients);
                                    System.out.println();
                                    Thread.sleep(5000);
                                    break;

                                    case 5:
                                        return;

                                        default:
                                            System.out.println("Invalid choice");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

   public static void Bookappointment(Connection connection,Scanner scanner,Doctors doctors,Patients patients){
        System.out.print("enter the Patient ID");
        int patientID = scanner.nextInt();
        System.out.print("enter the doctor's ID:");
        int doctorId = scanner.nextInt();
        System.out.print("enter the Appointment date (yyyy-MM-dd):");
        String appointment_date = scanner.next();
        if(patients.checkPatient(patientID) && doctors.checkdoctors(doctorId)){
            if (checkDoctorAvailability(doctorId,appointment_date,connection)){
                String appointmentquery = "insert into appointments(patient_id,doctors_id,appointment_date) values(?,?,?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentquery);
                    preparedStatement.setInt(1, patientID);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointment_date);
                    int rowsaffected =  preparedStatement.executeUpdate();
                    if (rowsaffected > 0){
                        System.out.println("Appointment booked successfully !");
                    }
                    else{
                        System.out.println("Appointment failed !");
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("Invalid patient");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId,String appointment_date,Connection connection){
        String query = "select count(*) from appointments where doctors_id=? and appointment_date=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorId);
            preparedStatement.setString(2,appointment_date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if (count == 0){
                    return true;
                }
                else {
                    return false;
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }



}
