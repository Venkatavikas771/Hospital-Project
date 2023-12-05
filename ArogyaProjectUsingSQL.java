package a1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Patient {
    private static int nextId = 1;
    private int id;
    private String name;
    private int age;
    private String guardianName;
    private String gender;
    private String address;
    private String guardianAddress;
    private String contactNumber;
    private String city;
    private String dateOfAdmission;
    private String guardianContactNumber;
    private String aadharCardNumber;
    private boolean recovered;

    Patient(String name, int age, String guardianName, String gender, String address, String guardianAddress,
            String contactNumber, String city, String dateOfAdmission, String guardianContactNumber,
            String aadharCardNumber) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.aadharCardNumber = aadharCardNumber;
        this.contactNumber = contactNumber;
        this.city = city;
        this.address = address;
        this.dateOfAdmission = dateOfAdmission;
        this.guardianName = guardianName;
        this.guardianAddress = guardianAddress;
        this.guardianContactNumber = guardianContactNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(String aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianAddress() {
        return guardianAddress;
    }

    public void setGuardianAddress(String guardianAddress) {
        this.guardianAddress = guardianAddress;
    }

    public String getGuardianContactNumber() {
        return guardianContactNumber;
    }

    public void setGuardianContactNumber(String guardianContactNumber) {
        this.guardianContactNumber = guardianContactNumber;
    }
}

class Services {
    private HashMap<Integer, Patient> patients = new HashMap<>();

    public void addPatient(Patient p) throws ClassNotFoundException {
        patients.put(p.getId(), p);
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arogya123", "root", "Vikas@12345");
            String query = "INSERT INTO patients (id, name, age, gender, aadhar_card_number, contact_number, city, address, date_of_admission, guardian_name, guardian_address, guardian_contact_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, p.getId());
                preparedStatement.setString(2, p.getName());
                preparedStatement.setInt(3, p.getAge());
                preparedStatement.setString(4, p.getGender());
                preparedStatement.setString(5, p.getAadharCardNumber());
                preparedStatement.setString(6, p.getContactNumber());
                preparedStatement.setString(7, p.getCity());
                preparedStatement.setString(8, p.getAddress());
                preparedStatement.setString(9, p.getDateOfAdmission());
                preparedStatement.setString(10, p.getGuardianName());
                preparedStatement.setString(11, p.getGuardianAddress());
                preparedStatement.setString(12, p.getGuardianContactNumber());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("New patient added successfully");
    }

    public void getPatientById(int id) {
        Patient p = patients.get(id);
        if (p != null) {
            displayPatientDetails(p);
        } else {
            System.out.println("No patient found with that id");
        }
    }

    public ArrayList<Patient> getPatientByCity(String city) {
        ArrayList<Patient> patientList = new ArrayList<>();
        for (Patient p : patients.values()) {
            if (city.equalsIgnoreCase(p.getCity())) {
                patientList.add(p);
            }
        }
        if (!patientList.isEmpty()) {
            for (Patient p : patientList) {
                displayPatientDetails(p);
            }
        } else {
            System.out.println("No patients found in the given city");
        }
        return patientList;
    }

    public void deletePatient(int idToDelete) {
        patients.remove(idToDelete);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");
            String query = "DELETE FROM patients WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idToDelete);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Patient with ID " + idToDelete + " has been deleted");
    }

    public ArrayList<Patient> getPatientByAge(int minAge, int maxAge) {
        ArrayList<Patient> patientList = new ArrayList<>();
        for (Patient p : patients.values()) {
            if (p.getAge() >= minAge && p.getAge() <= maxAge) {
                patientList.add(p);
            }
        }
        if (!patientList.isEmpty()) {
            for (Patient p : patientList) {
                displayPatientDetails(p);
            }
        } else {
            System.out.println("No patients found in the given age range");
        }
        return patientList;
    }

    public void getByName(String name) {
        int count = 0;
        for (Patient p : patients.values()) {
            if (p.getName().equalsIgnoreCase(name)) {
                displayPatientDetails(p);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No patient found with that name");
        }
    }

    public void allPatientDetails() {
        if (!patients.isEmpty()) {
            for (Patient p : patients.values()) {
                displayPatientDetails(p);
            }
        } else {
            System.out.println("Collection is empty");
        }
    }

    private void displayPatientDetails(Patient p) {
        System.out.println("Patient Details:");
        System.out.println("ID: " + p.getId());
        System.out.println("Name: " + p.getName());
        System.out.println("Age: " + p.getAge());
        System.out.println("Guardian Name: " + p.getGuardianName());
        System.out.println("Gender: " + p.getGender());
        System.out.println("Address: " + p.getAddress());
        System.out.println("Guardian Address: " + p.getGuardianAddress());
        System.out.println("Contact Number: " + p.getContactNumber());
        System.out.println("City: " + p.getCity());
        System.out.println("Date of Admission: " + p.getDateOfAdmission());
        System.out.println("Guardian Contact Number: " + p.getGuardianContactNumber());
        System.out.println("Aadhar Card Number: " + p.getAadharCardNumber());
        System.out.println("--------------------");
    }
}

public class HospitalProject {
    public static void main(String[] args) throws ClassNotFoundException {
        Services hospital = new Services();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------***--------------");
            System.out.println("Welcome to the Hospital management app");
            System.out.println("-----------------***--------------");
            System.out.println("1. Adding a patient");
            System.out.println("2. Get the patient with a unique id");
            System.out.println("3. Get the patient by city");
            System.out.println("4. List of patients");
            System.out.println("5. Filtering the patients");
            System.out.println("6. Get details with name");
            System.out.println("7. Delete patient");
            System.out.println("8. Exit");

            System.out.print("Enter the choice required: ");
            choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    hospital.addPatient(getPatientDetailsFromUser(sc));
                    break;
                case 2:
                    System.out.print("Enter the patient id to search: ");
                    int id = sc.nextInt();
                    hospital.getPatientById(id);
                    break;
                case 3:
                    System.out.print("Enter the city to search for patients: ");
                    sc.nextLine();  
                    String city = sc.nextLine();
                    hospital.getPatientByCity(city);
                    break;
                case 4:
                    hospital.allPatientDetails();
                    break;
                case 5:
                    System.out.print("Search the people between ages\nEnter the min age: ");
                    int minAge = sc.nextInt();
                    System.out.print("Enter the max age: ");
                    int maxAge = sc.nextInt();
                    hospital.getPatientByAge(minAge, maxAge);
                    break;
                case 6:
                    System.out.print("Enter the patient name to get details: ");
                    String name = sc.next();
                    hospital.getByName(name);
                    break;
                case 7:
                    System.out.print("Enter the patient id to delete: ");
                    int idToDelete = sc.nextInt();
                    hospital.deletePatient(idToDelete);
                    break;
                case 8:
                    System.out.println("Exiting the Hospital Management App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 8.");
            }

        } while (choice != 8);
    }

    private static Patient getPatientDetailsFromUser(Scanner scanner) {
        System.out.print("Enter the patient name: ");
        String name = scanner.next();
        scanner.nextLine();  
        System.out.print("Enter the patient age: ");
        int age = scanner.nextInt();
        System.out.print("Enter the guardian name: ");
        String guardianName = scanner.next();
        System.out.print("Enter the gender: ");
        String gender = scanner.next();
        System.out.print("Enter the address: ");
        String address = scanner.next();
        System.out.print("Enter the guardian address: ");
        String guardianAddress = scanner.next();
        System.out.print("Enter the patient contact number: ");
        String contactNumber = scanner.next();
        System.out.print("Enter the patient city: ");
        scanner.nextLine();  
        String city = scanner.nextLine();
        System.out.print("Enter the patient dateOfAdmission: ");
        String dateOfAdmission = scanner.next();
        System.out.print("Enter the guardian contact number: ");
        String guardianContactNumber = scanner.next();
        System.out.print("Enter the patient aadharCardNumber: ");
        String aadharCardNumber = scanner.next();

        return new Patient(name, age, guardianName, gender, address, guardianAddress, contactNumber, city,
                dateOfAdmission, guardianContactNumber, aadharCardNumber);
    }
}
