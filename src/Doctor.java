import java.io.Serializable;
import java.time.LocalDateTime;

//subclass
public class Doctor extends Person implements Serializable {
    //instance variables
    private String licenseNo;
    private String specialisation;

    public Doctor(){

    }
    //constructor
    public Doctor (String fName, String lName, LocalDateTime DOB, String mobNo, String licenseNo, String docSpecialisation) {
        super(fName, lName, DOB, mobNo);
        this.licenseNo = licenseNo;
        this.specialisation = docSpecialisation;
    }

    //getters and setters
    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}
