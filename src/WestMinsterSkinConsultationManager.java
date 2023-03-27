import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class WestMinsterSkinConsultationManager implements SkinConsultationManager, Serializable {

    Scanner scanner = new Scanner(System.in);
    private static final int MAX_NUM_DOCTORS = 10;
    private static final String MOB_NUM_PATTERN = "^[0-9]{10}$";
    private List<Doctor> doctors;
    private List<Consultation> consultations;
    private List<Patient> patients;

    public WestMinsterSkinConsultationManager() {
        doctors = new ArrayList<Doctor>();
        consultations = new ArrayList<Consultation>();
        patients = new ArrayList<Patient>();
    }

    //boolean method to check validity of DOB format entered
    private boolean isValid(String dateStr, String type) {
        String format = "";
        if (type == "D") {
            format = "yyyy-MM-dd";
        } else {
            format = "yyyy-MM-dd HH:mm";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate.parse(dateStr, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    //method to ask date of birth from user
    private String askDOB(String type) {
        String str = "";
        if (type == "D") {
            System.out.print("Enter date of birth [YYYY-MM-DD ]: ");
            str = this.scanner.nextLine();
        }
        return str;
    }

    //validation method to check if date of birth entered is correct
    private String validateDate(String type) {
        Boolean s = true;
        String strDate = "";
        while (s) {
            String date = askDOB("D");
            if (isValid(date, type)) {
                strDate = date;
                s = false;
            } else {
                System.out.print("\u001B[31m" + "Entered DOB format is wrong, please re-enter\n" + "\u001B[0m");
            }
        }
        return strDate;
    }

    //method to format date of birth
    private LocalDateTime dateConverter(String date, String type) {
        String format = "";
        if (type == "D") {
            format = "yyyy-MM-dd";
        } else {
            format = "yyyy-MM-dd HH:mm";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        if (type == "D") {
            return LocalDate.parse(date, dtf).atStartOfDay();
        } else {
            return LocalDateTime.parse(date, dtf);
        }
    }

    //boolean method for mobile number validation
    private boolean isValid(String mobileNo) {
        return Pattern.matches(MOB_NUM_PATTERN, mobileNo);
    }

    //method to capitalize the first letters of words
    private String capitalizeWord(String word) {
        String capitalizeIt = word.substring(0, 1).toUpperCase() + word.substring(1);
        return capitalizeIt;
    }

    //method to add a new doctor
    public void newDoctor() {

        //can only add up to 10 doctors
        if (this.doctors.size() < MAX_NUM_DOCTORS) {

            //create new instance of doctor object
            Doctor doctor = new Doctor();
            System.out.println("Please enter the following details ");
            Boolean validInput = false;

            //validation for empty input
            do {
                System.out.print("Doctor name: ");
                String s = scanner.nextLine();
                if (s.length() > 0) {// checks if input only contains digits
                    validInput = true;
                    doctor.setName(s);
                }
            } while (!validInput);

            System.out.print("Surname: ");
            doctor.setSurname(this.scanner.nextLine());
            doctor.setDOB(dateConverter(validateDate("D"), "D"));

            //validation for mobile number
            while (true) {
                System.out.print("Mobile number: ");
                String mobileNum = scanner.nextLine();
                if (isValid(mobileNum)) {
                    doctor.setMobileNo(mobileNum);
                    break;
                }
            }

            System.out.print("License number: ");
            doctor.setLicenseNo(this.scanner.nextLine());
            System.out.print("Specialisation: ");
            doctor.setSpecialisation(this.scanner.nextLine());

            //add doctor into doctor list
            doctors.add(doctor);
            System.out.println("\033[32m" + "\nDr." + capitalizeWord(doctor.getName()) + " "
                    + capitalizeWord(doctor.getSurname()) + " added!" + "\033[0m");

        } else {
            System.out.println("🛑 Maximum number of doctors added to the system 🛑");
        }

    }

    //method to remove a doctor
    public void deleteDoctor() {
        System.out.print("To remove doctor, please enter Doctor's license number: ");
        String licenseNumber = this.scanner.nextLine();

        Doctor doctor = null;
        for (Doctor d : this.doctors) {
            //entered license number is equal to license number, add doctor d to doctor
            if (d.getLicenseNo().equals(licenseNumber)) {
                doctor = d;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("🟨Try again! Doctor not found🟨");
        } else {
            this.doctors.remove(doctor);
            System.out.println("Dr. " + capitalizeWord(doctor.getName()) + " " + capitalizeWord(doctor.getSurname())
                    + " has been removed");
            System.out.println();
            System.out.println("\033[33m" + "The centre has now " + this.doctors.size() + " doctors" + "\033[0m");
        }
    }

    //method to print doctor information
    public void printDoctor() {
        if (doctors.isEmpty()) {
            System.out.println("There are no doctors in the system.");
        } else {
            this.doctors.sort(Comparator.comparing(Person::getSurname));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.println("🩺List of Doctors\n");
            for (Doctor doctor : this.doctors) {
                System.out.println("\033[34m" + "Dr. " + capitalizeWord(doctor.getName()) + " "
                        + capitalizeWord(doctor.getSurname()) + "\033[0m");
                System.out.println("Specialization: " + doctor.getSpecialisation());
                System.out.println("Date of birth: " + doctor.getDOB().format(formatter));
                System.out.println("Mobile number: " + doctor.getMobileNo());
                System.out.println("Medical license number: " + doctor.getLicenseNo());

                System.out.println();
            }
        }
    }

    //method to save data
    public void storeProgramData() {
        try {
            FileOutputStream fileOut = new FileOutputStream("listOfDoctors.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(doctors);
            out.close();
            fileOut.close();
            System.out.println("Your data has been saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to load data
    public void loadProgramData() {

        try {
            FileInputStream fileIn = new FileInputStream("listOfDoctors.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            doctors = (List<Doctor>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Your data has been loaded!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //check if doctor is free
    public boolean isDoctorAvailable(String licenseNo, LocalDateTime dateTime) {
        for (Consultation consultation : consultations) {
            if (consultation.getDoctor().getLicenseNo().equals(licenseNo) && consultation.getDate().equals(dateTime)) {
                return false;
            }
        }
        return true;
    }

    //method to check if the doctor is free when booking consultation
    public String checkAvailable(String licenseNo, LocalDateTime dateTime, Doctor doct) {
        Doctor doctor1 = new Doctor();
        if (isDoctorAvailable(licenseNo, dateTime)) {
            JOptionPane.showMessageDialog(null, "Doctor is available!");

        } else {
            JOptionPane.showMessageDialog(null, "Slot is taken" +
                    "\nYou will be allocated another doctor.");
            if (doctors.size() < 2) {
                JOptionPane.showMessageDialog(null, "There are no " +
                        "available doctors for the selected time slot.");
            } else {
                //get a new doctor that is available since chosen doctor is busy
                for (Doctor doctor : doctors){
                    if (!(doctor.getLicenseNo().equals(licenseNo))){
                        doctor1 = doctor;
                    }
                }
                JOptionPane.showMessageDialog(null, "New doctor has been allocated: Dr."
                        + capitalizeWord(doctor1.getName()) + " " + capitalizeWord(doctor1.getSurname())
                        + "\nSpecialisation: " + doctor1.getSpecialisation());
            }
        }
        //ternary operation. if doctor license null, return "na", else return license
        //it will return new doctor's license
        return doctor1.getLicenseNo() == null ? "na" : doctor1.getLicenseNo();
    }

    //method to add a new consultation in the gui
    public void addConsultation(LocalDateTime dateTime, Doctor doctor,
                                Patient patient, int cost, String notes, ImageIcon image) {
        Consultation consultation = new Consultation(dateTime, doctor, patient, cost, notes, image);
        //add it to the consultations list
        consultations.add(consultation);
    }

    //method to add an id for each consultation
    public void addConsultationId(Patient patient) {
        for (Consultation consultation : consultations) {
            if (consultation.getPatient().equals(patient)) {
                int i = consultations.indexOf(consultation);
                consultation.setConsultationNo(i + 1);
            }
        }
    }

    //method to set image submitted to consultation
    public void setIcon(Patient patient, String filePath){
        for(Consultation consultation : consultations){
            if(consultation.getPatient().equals(patient)){
                ImageIcon image = new ImageIcon(filePath);
                consultation.setImage(image);
                break;
            }
        }

    }

    //method to decrypt image
    public void DecryptImage(String filename) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a key for Decryption : ");
        int key = sc.nextInt();
        FileInputStream fis = new FileInputStream(filename);

        // Converting image into byte array
        byte[] data = new byte[fis.available()];

        // Read the array
        fis.read(data);
        int i = 0;

        //XOR operation on each value of byte array to Decrypt it.
        for (byte b : data) {
            data[i] = (byte) (b ^ key);
            i++;
        }

        // Opening file for writing purpose
        FileOutputStream fos = new FileOutputStream(filename);

        // Writing Decrypted data on Image
        fos.write(data);
        fos.close();
        fis.close();
        System.out.println("Decryption Done...");
    }

    //getters and setters

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
