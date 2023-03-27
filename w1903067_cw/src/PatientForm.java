import com.github.lgooddatepicker.components.DateTimePicker;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;


public class PatientForm extends JFrame {
    private JPanel JPanelImage;
    private JTextField jTextField1;
    private final JTextField patientDOBField;
    private final JTextField patientMobNoField;
    private final JTextField doctorLicenseField;

    WestMinsterSkinConsultationManager manager;
    Patient patient;

    public PatientForm(WestMinsterSkinConsultationManager currentManager) {

        this.manager = currentManager;

        //create instances of the objects
        patient = new Patient();
        Doctor doctor = new Doctor();
        Consultation consultation = new Consultation();

        setTitle("WestMinster Skin Consultation Centre");

        //First panel
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(16, 2, 5, 5));
        p1.setBackground(Color.white);
        p1.setBounds(120, 30, 550, 550);

        //adding all labels and fields to p1 panel using add
        JLabel nameLabel = new JLabel("Patient Form");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        p1.add(nameLabel);
        p1.add(new JLabel(""));

        p1.add(new JLabel("Patient ID"));
        JTextField patientIDField = new JTextField();
        patient.setPatientId(patientIDField.getText());
        p1.add(patientIDField);

        p1.add(new JLabel("Patient first name"));
        JTextField patientFNameField = new JTextField();
        patient.setName(patientFNameField.getText());
        p1.add(patientFNameField);

        p1.add(new JLabel("Patient surname "));
        JTextField patientSNameField = new JTextField();
        patient.setSurname(patientSNameField.getText());
        p1.add(patientSNameField);

        p1.add(new JLabel("Date of birth [YYYY-MM-DD]"));
        patientDOBField = new JTextField();
        p1.add(patientDOBField);

        patientDOBField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Objects.equals(patientDOBField.getText(), "")) {
                    return;
                }

                //date of birth input validation
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String patientDOB = patientDOBField.getText();
                    LocalDate date = LocalDate.parse(patientDOB, formatter);
                    patient.setDOB(date.atStartOfDay());
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "DOB incorrect, Please re-enter");
                }
            }
        });

        p1.add(new JLabel("Mobile number "));
        patientMobNoField = new JTextField();
        p1.add(patientMobNoField);

        patientMobNoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Objects.equals(patientMobNoField.getText(), "")) {
                    return;
                }

                //mobile number input validation
                String patientMobNo = patientMobNoField.getText();
                if (patientMobNo.matches("^[0-9]{10}$")) {
                    patient.setMobileNo(String.valueOf(patientMobNoField));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid mobile number, Please re-enter");
                }
            }
        });

        p1.add(new JLabel("Notes "));
        JTextField patientNotesField = new JTextField();
        consultation.setNotes(String.valueOf(patientNotesField));
        p1.add(patientNotesField);

        //button to attach image
        p1.add(new JLabel("Upload image of skin"));
        JButton attachPictureBtn = new JButton("Attach image");
        p1.add(attachPictureBtn);

        JLabel bDoctorLabel = new JLabel("Book Doctor");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        p1.add(bDoctorLabel);
        p1.add(new JLabel(""));

        p1.add(new JLabel("Doctor License Number"));
        doctorLicenseField = new JTextField();
        p1.add(doctorLicenseField);

        doctorLicenseField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

                List<Doctor> dsc = currentManager.getDoctors();
                System.out.println(dsc.size());

                boolean correctDoctor = false;

                //check if the doctor license number entered by user is correct
                // by iterating through the license numbers in the doctor list
                for (int i = 0; i < dsc.size(); i++) {
                    if (dsc.get(i).getLicenseNo().equals(doctorLicenseField.getText())) {
                        correctDoctor = true;
                    }
                }
                if (!correctDoctor) {
                    JOptionPane.showMessageDialog(null, "No doctor found, Please re-enter");
                }
            }
        });


        p1.add(new JLabel("Date and Time"));
        DateTimePicker DT = new DateTimePicker();
        consultation.setDate(DT.getDateTimePermissive());
        p1.add(DT);

        p1.add(new JLabel(""));
        //button to check availability of doctor
        JButton checkAvailabilityBtn = new JButton("Check Doctor Availability");
        p1.add(checkAvailabilityBtn);

        //radio button to choose cost
        JRadioButton jRadioButton1 = new JRadioButton("£15 (first consultation)");
        JRadioButton jRadioButton2 = new JRadioButton("£25");
        ButtonGroup G1 = new ButtonGroup();
        JLabel L1 = new JLabel("Cost per hour");
        L1.setBounds(20, 30, 150, 50);
        jRadioButton1.setBounds(120, 30, 120, 50);
        jRadioButton2.setBounds(250, 30, 80, 50);

        if(jRadioButton1.isSelected()){
            consultation.setCost(15);
        } else if (jRadioButton2.isSelected()) {
            consultation.setCost(25);
        }

        p1.add(L1);
        p1.add(new JLabel(""));
        G1.add(jRadioButton1);
        G1.add(jRadioButton2);
        p1.add(jRadioButton1);
        p1.add(jRadioButton2);


        //submit button
        JButton submitButton = new JButton("Submit");

        JLabel cost1 = new JLabel("15");

        //once button is clicked
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (jRadioButton1.isSelected()) {
                    cost1.setText("15");
                } else if (jRadioButton2.isSelected()) {
                    cost1.setText("25");
                }

                //patient object
                patient = new Patient(patientIDField.getText(), patientFNameField.getText(), patientSNameField.getText(),
                        patient.getDOB(), patientMobNoField.getText());

                //doctor object
                Doctor dd = null;
                for (Doctor doctor1 : currentManager.getDoctors()) {
                    if (Objects.equals(doctorLicenseField.getText(), doctor1.getLicenseNo())) {
                        dd = new Doctor(doctor1.getName(), doctor1.getSurname(), doctor1.getDOB(),
                                doctor1.getMobileNo(), doctor1.getLicenseNo(), doctor1.getSpecialisation());
                        consultation.setDoctor(dd);
                    }
                }


                //
//                try{
                    consultation.setPatient(patient);
                    //method which passes the arguments to create a new consultation object
                    currentManager.addConsultation(LocalDateTime.parse(DT.toString()),
                            dd, patient, Integer.parseInt(cost1.getText()), patientNotesField.getText(), consultation.getImage());

                    //method to add a consultation id to the consultation
                    currentManager.addConsultationId(patient);
//                } catch (DateTimeParseException dtE){
//                    JOptionPane.showMessageDialog(null, "Choose a date and time");
//                }

                JOptionPane.showMessageDialog(null, "Submitted!");
                setVisible(false);

            }
        });
        submitButton.setBounds(125, 90, 80, 30);
        p1.add(new JLabel(""));
        p1.add(submitButton);

        JPanelImage = new JPanel();
        JPanelImage.setLayout(new GridLayout(4, 2, 10, 10));
        add(JPanelImage);
//        this.add(JPanelImage, BorderLayout.CENTER);

        //add p1 and image panel to a bigger main panel
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(2, 1, 10, 10));
        p3.add(p1);
        p3.add(JPanelImage);

        //action listeners for attach picture and check doctor availability button
        attachPictureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //set image uploaded to consultation
                    ImageIcon icon = jButton1ActionPerformed(e);
                    consultation.setImage(icon);
                    pack();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        checkAvailabilityBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set return value of method
                String docLicense = currentManager.checkAvailable(doctorLicenseField.getText(),
                        DT.getDateTimePermissive(), doctor);
                if (docLicense != "na"){
                    //set doctor license
                    doctorLicenseField.setText(docLicense);
                }
            }
        });

        //add main panel p3 to frame
        this.add(p3, BorderLayout.CENTER);
        this.pack();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }


    //action button to upload image
    private ImageIcon jButton1ActionPerformed(ActionEvent evt) throws FileNotFoundException, IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        jTextField1 = new JTextField(filename);

        try {
            ImageIcon ii = new ImageIcon(scaleImage(520, 520, ImageIO.read(new File(f.getAbsolutePath()))));//get the image from file chooser and scale it to match JLabel size
            JLabel pic = new JLabel(ii);

            JPanelImage.add(pic);
            //call EncryptImage method to encrypt
            EncryptImage(filename);
            manager.setIcon(patient, filename);

            return ii;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //scale image
    public static BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
        BufferedImage bi;
        bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, w, h, null);
        g2d.dispose();
        return bi;
    }

    //method to encrypt image
    private void EncryptImage(String filename) throws FileNotFoundException, IOException {
        int key = 12345;
        FileInputStream fis = new FileInputStream(filename);
        // Converting into byte array, so the array is same size as image size

        byte data[] = new byte[fis.available()];

        // Read the array
        fis.read(data);
        int i = 0;

        //do XOR operation on each value of byte array so each value of image changes
        for (byte b : data) {
            data[i] = (byte) (b ^ key);
            i++;
        }

//        try {
//            Files.copy(data, filename, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            // Print the line number where exception occurred
//            e.printStackTrace();
//        }

        // Open file for writing
        FileOutputStream fos = new FileOutputStream(filename);

        // Writing new byte array value to image that will encrypt
        fos.write(data);

        // close file
        fos.close();
        fis.close();

        System.out.println("Encryption Done...");
    }


}

