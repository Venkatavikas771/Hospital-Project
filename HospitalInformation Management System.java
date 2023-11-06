/*Arogya HospitalInformation Management System


Arogya is a reputed hospital and it is looking to revamp their aging information management system that is built using an old technology. 
They want a fully web based information system that can handle all their use cases. They have awarded the initial contract to bitLabs, 
and you are the lead Full-Stack Developer. Based on the success of this initial project, Arogya will award additional contracts to bitLabs.


The management system should have the following features:-

Add a patient’s details to the system whenever there is a new patient admitted.

The patient details should include his/her name, age, gender, Aadhar Card number, contact number, city, address, date of admission, guardian's name,
guardian’s address, guardian’s contact number. Assign an unique id to each patient admitted.


The system can show the list of patients at any point of time. Basically the system should be dynamic and a real time system. In other words,it can provide results at any point of time without any delays.


Given the unique id assigned to a patient, provide all the details of that patient.


In other words, the system should provide details about a patient using his/her unique id or name.


The system can provide the list of all the patients from a particular city or a state. Also provide the list of patients belonging to a particular age group. For example you can be asked to give the list of patients in the age group 50-60.


Mark the patient recovered when he starts working fine.

The system should be able to delete the information of a patient when asked to.  


Implement these features using efficient data structures and OOP concepts learnt in the Java Foundation course. Write clean and organized codes using proper objects and methods.*/

import java.util.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;

class Patients {
  private static int nextId = 1;
  private int id;
  private String name;
  private int age;
  private long adharNumber;
  private long mobileNumber;
  private String city;
  private String address;
  private Date dateOfAdmission;
  private String guardianName;
  private String guardianAddress;
  private long guardianNumber;
  private boolean status;

  public Patients_1(String name,int age,long adharNumber,long mobileNumber,String city,String address,Date dateOfAdmission,String guardianName,String guardianAddress,long guardianNumber){
    this.id=nextId++;
    this.name=name;
    this.age=age;
    this.adharNumber=adharNumber;
    this.mobileNumber=mobileNumber;
    this.city=city;
    this.address=address;
    this.address=address;
    this.dateOfAdmission=dateOfAdmission;
    this.guardianName=guardianName;
    this.guardianAddress=guardianAddress;
    this.guardianNumber=guardianNumber;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public long getAdharNumber() {
    return adharNumber;
  }

  public long getMobileNumber() {
    return mobileNumber;
  }

  public String getCity() {
    return city;
  }

  public String getAddress() {
    return address;
  }

  public Date getDateOfAdmission() {
    return dateOfAdmission;
  }

  public String getGuardianName() {
    return guardianName;
  }

  public String getGuardianAddress() {
    return guardianAddress;
  }

  public long getGuardianNumber() {
    return guardianNumber;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus() {
    this.status = true;
  }
}

class Hospital {
  HashMap<Integer, Patients> patients = new HashMap<Integer, Patients>();

  public void addPatient(Patients p) throws Exception {
    patients.put(p.getId(), p);
    System.out.println(" New Patient Added Successfully");
  }

  public void display(Patients p) throws Exception {
    System.out.println();
    System.out.println("Patient Id: " + p.getId());
    System.out.println("Patient Name: " + p.getName());
    System.out.println("Patient Age: " + p.getAge());
    System.out.println("Patient Adhar Number: " + p.getAdharNumber());
    System.out.println("Patient Mobile Number: " + p.getMobileNumber());
    System.out.println("Patient city: " + p.getCity());
    System.out.println("Patient Address: " + p.getAddress());
    System.out.println("Date of Admission: " + p.getDateOfAdmission());
    System.out.println("Guardian Name: " + p.getGuardianName());
    System.out.println("Guardian Address: " + p.getGuardianAddress());
    System.out.println("Guardian Mobile Number: " + p.getGuardianNumber());
    System.out.println("Status of the Patient: " + p.getStatus());
  }

  public void allPatientsDetails() throws Exception {
    boolean check = patients.isEmpty();
    if (check == false) {
      Set keys = patients.keySet();
      for (Object obj : keys) {
        Integer p1 = (Integer) obj;
        Patients_1 p = patients.get(p1);
        display(p);
      }
    } else {
      System.out.println("Empty collection");
    }
  }

  public void searchWithId(int id) throws Exception {
    Patients p = patients.get(id);
    if (p != null) {
      display(p);
    } else {
      System.out.println("With this Id:" + id + " there is no Patient");
    }
  }

  public void searchWithName(String name) throws Exception {
    int c = 0;
    for (Patients p : patients.values()) {
      if (p.getName().equalsIgnoreCase(name)) {
        display(p);
        c++;
      }
    }
    if (c == 0) {
      System.out.println("Patient not found");
    }
  }

  public void filteringWithCity(String city) throws Exception {
    int c = 0;
    for (Patients p : patients.values()) {
      if (p.getCity().equalsIgnoreCase(city)) {
        display(p);
        c++;
      }
    }
    if (c == 0) {
      System.out.println("Patients not found");
    }
  }

  public void filteringWithAge(int minAge, int maxAge) throws Exception {
    int c = 0;
    for (Patients p : patients.values()) {
      if (p.getAge() > minAge && p.getAge() < maxAge) {
        display(p);
        c++;
      }
    }
    if (c == 0) {
      System.out.println("Patients not found");
    }
  }

  public void dischargePatient(int id) throws Exception {
    Patient p = patients.get(id);
    if (p != null) {
      System.out.println();
      System.out.println("Patient Id: " + p.getId());
      System.out.println("Patient Name: " + p.getName());
      System.out.println("Patient Age: " + p.getAge());
      System.out.println("Patient Adhar Number: " + p.getAdharNumber());
      System.out.println("Patient Mobile Number: " + p.getMobileNumber());
      System.out.println("Patient city: " + p.getCity());
      System.out.println("Patient Address: " + p.getAddress());
      System.out.println("Date of Admission: " + p.getDateOfAdmission());
      System.out.println("Guardian Name: " + p.getGuardianName());
      System.out.println("Guardian Address: " + p.getGuardianAddress());
      System.out.println("Guardian Mobile Number: " + p.getGuardianNumber());
      p.setStatus();
      System.out.println("Status of the Patient: " + p.getStatus());
    } else {
      System.out.println("Id Not found");
    }
  }

  public void deletePatient(int id) throws Exception {
    Patients p = patients.remove(id);
    if (p != null) {
      display(p);
    } else {
      System.out.println("Id Not found");
    }
  }
}

public class HospitalProject {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int v = 0;
    Hospital hsp = new Hospital();
    while (v == 0) {
      System.out.println();
      System.out.println("*************************************************");
      System.out.println("***Welcome to Arogya Hospital Managment System***");
      System.out.println("*************************************************");
      System.out.println(
          "1.Add New Patient\n2.List of Patients\n3.Search Patient With Unique ID\n4.Search Patient With name\n5.Filtering Patients\n6.Discharge Patient\n7.Delete Patient\n8.Shut Down\nSelect One Option");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          System.out.print("Patient Name: ");
          sc.nextLine();
          String name = sc.nextLine();
          System.out.print("Patient Age: ");
          int age = sc.nextInt();
          System.out.print("Patient Adhar Number: ");
          long adharNumber = sc.nextLong();
          System.out.print("Patient Mobile Number: ");
          long mobileNumber = sc.nextLong();
          System.out.print("Patient city: ");
          sc.nextLine();
          String city = sc.nextLine();
          System.out.print("Patient Address: ");

          String address = sc.nextLine();
          System.out.print("Date of Admission: ");

          String dateOfAdmission = sc.nextLine();
          DateFormat df = new SimpleDateFormat("DD/MM/YYYY");
          Date presentDate = df.parse(dateOfAdmission);
          System.out.print("Guardian Name: ");

          String guardianName = sc.nextLine();
          System.out.print("Guardian Address: ");

          String guardianAddress = sc.nextLine();
          System.out.print("Guardian Mobile Number: ");
          long guardianNumber = sc.nextLong();
          Patients p1 = new Patients(name, age, adharNumber, mobileNumber, city, address, presentDate, guardianName,
              guardianAddress, guardianNumber);
          hsp.addPatient(p1);
          break;
        case 2:
          hsp.allPatientsDetails();
          break;
        case 3:
          System.out.print("Enter the Patient Id: ");
          int id = sc.nextInt();
          hsp.searchWithId(id);
          break;
        case 4:
          System.out.println("Enter the name of the Patient: ");
          sc.nextLine();
          String name1 = sc.nextLine();
          hsp.searchWithName(name1);
          break;
        case 5:
          System.out.println("1.With city\n2.With Age\nSelect One above Two");
          int choice2 = sc.nextInt();
          switch (choice2) {
            case 1:
              System.out.print("Enter the City Name: ");
              sc.nextLine();
              String city1 = sc.nextLine();
              hsp.filteringWithCity(city1);
              break;
            case 2:
              System.out.print("Enter the Min Age: ");
              int minAge = sc.nextInt();
              System.out.print("Enter the Max Age: ");
              int maxAge = sc.nextInt();
              hsp.filteringWithAge(minAge, maxAge);
              break;
          }
          break;
        case 6:
          System.out.print("Entre the Patient Id to Discharge: ");
          int id1 = sc.nextInt();
          hsp.dischargePatient(id1);
          break;
        case 7:
          System.out.print("Enter the Id Of The Patient to Delete: ");
          int id2 = sc.nextInt();
          hsp.deletePatient(id2);
          break;
        case 8:
          System.out.println("EXIT");
          r++;
      }
    }
  }
}