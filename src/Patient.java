import java.time.LocalDateTime;

//subclass
public class Patient extends Person{

    //instance variable
    private String patientId;

    //constructor
    public Patient (String patId, String fName, String lName, LocalDateTime DOB, String mobNo) {
        super(fName, lName, DOB, mobNo);
        this.patientId = patId;
    }

    public Patient() {

    }

    //getter and setter
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

}
