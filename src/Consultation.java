import javax.swing.*;
import java.time.LocalDateTime;

public class Consultation {
    //instance variables
    private int consultationNo;
    private LocalDateTime date;
    private int cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;
    private ImageIcon image;

    //constructor
    public Consultation(LocalDateTime dateTime, Doctor doctor, Patient patient,
                        int cost, String notes, ImageIcon image){
        this.date = dateTime;
        this.doctor = doctor;
        this.patient = patient;
        this.cost = cost;
        this.notes = notes;
        this.image = image;
    }

    public Consultation() {

    }

    //getters and setters
    public int getConsultationNo() {
        return consultationNo;
    }

    public void setConsultationNo(int consultationNo) {
        this.consultationNo = consultationNo;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

}
