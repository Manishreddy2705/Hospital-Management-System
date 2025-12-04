

# **Hospital Management System (Java, JDBC, MySQL)**

A simple console-based **Hospital Management System** implemented using **Java**, **JDBC**, and **MySQL**. This mini-project demonstrates core backend development concepts such as database connectivity, CRUD operations, input validation, and modular program design using object-oriented programming.

---

## **📌 Features**

### **Patient Management**

* Add new patients
* View all registered patients
* Validate patient details before booking appointments

### **Doctor Management**

* View list of available doctors
* Validate doctor IDs for appointments

### **Appointment Booking**

* Book appointments by selecting patient ID, doctor ID, and date
* Prevent duplicate bookings by checking doctor availability
* Store and retrieve appointment data from MySQL

### **Database Integration**

* Uses MySQL as backend database
* JDBC for all SQL queries
* Prepared Statements for secure and efficient DB operations

---

## **🛠️ Technologies Used**

* **Java (Core + OOP Concepts)**
* **JDBC**
* **MySQL**
* **Console-based Interface**

---

## **📂 Project Structure**

```
HospitalManagementSystem/
│
├── HospitalManagementsystem.java   # Main class (menu + flow control)
├── Patients.java                   # Handles patient operations
├── Doctors.java                    # Handles doctor operations
└── Database (MySQL Tables)         # patients, doctors, appointments
```

---

## **⚙️ How to Run**

1. Install **MySQL** and create a database:

   ```sql
   create database if not exists hospital;
   ```

2. Create required tables:

   ```sql
   CREATE TABLE patients (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(255) not null,
       age INT not null,
       gender char not null
   );

   CREATE TABLE doctors (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100) not null,
       specialization VARCHAR(100) not null
   );

   CREATE TABLE appointments (
       id INT PRIMARY KEY AUTO_INCREMENT,
       patient_id INT not null,
       doctors_id INT not null,
       appointment_date DATE not null,
       foreign key (patient_id) references patients(id),
       foreign key (doctors_id) references doctors(id),
   );
   ```

3. Update your MySQL username and password inside:

   ```java
   private static final String url = "jdbc:mysql://127.0.0.1:3306/hospital";
   private static final String username = "root";
   private static final String password = "YOUR_PASSWORD";
   ```

4. Compile and run the project:

   ```
   javac *.java
   java HospitalManagementsystem
   ```

---

##  Highlights**

* Demonstrates real-world appointment booking logic
* Avoids SQL injection via PreparedStatement
* Simple, readable, and beginner-friendly code
* Good mini-project for Java + MySQL beginners

---

## Future Improvements**

* Add login system for admin, doctors, or staff
* Add appointment cancellation or update features
* Shift to a GUI using JavaFX / Swing
* Build REST API using Spring Boot

---

