import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public interface SkinConsultationManager {

    void newDoctor();

    void deleteDoctor();

    void printDoctor();

    void storeProgramData();

    void loadProgramData();

    boolean isDoctorAvailable(String licenseNo, LocalDateTime dateTime);
    String checkAvailable(String licenseNo, LocalDateTime dateTime, Doctor doctor);

    void addConsultation(LocalDateTime dateTime, Doctor doctor,
                         Patient patient, int cost, String notes, ImageIcon image);

    void addConsultationId(Patient patient);

    List<Consultation> getConsultations();

}
