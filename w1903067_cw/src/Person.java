import java.io.Serializable;
import java.time.LocalDateTime;

//superclass
public class Person implements Serializable {
    //instance variables
    private String name;
    private String surname;
    private LocalDateTime DOB;
    private String mobileNo;

    //default constructor
    public Person (){

    }

    //constructor with arguments
    public Person (String fName, String lName, LocalDateTime DOB, String mobNo) {
        this.name = fName;
        this.surname = lName;
        this.DOB = DOB;
        this.mobileNo = mobNo;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getDOB() {
        return DOB;
    }

    public void setDOB(LocalDateTime DOB) {
        this.DOB = DOB;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
