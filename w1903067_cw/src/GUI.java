import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    public GUI(WestMinsterSkinConsultationManager currentManager) {

        //frame
        setVisible(true); //make frame visible
        setSize(400, 400); //set x-dimension and y-dimension of frame
        setResizable(false);
        setTitle("WestMinster Skin Consultation Center"); //set title of frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //exit out of application

        //panel
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 1, 10, 10));
        p1.setBackground(Color.white);

        JLabel home = new JLabel("~HOME~");
        home.setFont(new Font("Arial", Font.PLAIN, 20));
        p1.add(home);

        //book consultation button
        JButton bookDocBtn = new JButton("Book Consultation");
        bookDocBtn.setOpaque(true);
        bookDocBtn.setBorderPainted(false);
        bookDocBtn.setFont(new Font("Arial", Font.BOLD, 15));
        bookDocBtn.setBounds(125, 90, 80, 30);
        p1.add(bookDocBtn);

        bookDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientForm patientForm = new PatientForm(currentManager);
                patientForm.setVisible(true);
            }
        });

        //view doctor table button
        JButton viewTableBtn = new JButton("View Doctor Records");
        p1.add(viewTableBtn);

        viewTableBtn.setOpaque(true);
        viewTableBtn.setBorderPainted(false);
        viewTableBtn.setFont(new Font("Arial", Font.BOLD, 15));

        viewTableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoctorTable table = new DoctorTable(currentManager.getDoctors());
                table.setVisible(true);
            }
        });

        //view consultation table button
        JButton viewConsultationsBtn = new JButton("View Consultations");
        p1.add(viewConsultationsBtn);

        viewConsultationsBtn.setOpaque(true);
        viewConsultationsBtn.setBorderPainted(false);
        viewConsultationsBtn.setFont(new Font("Arial", Font.BOLD, 15));
        viewConsultationsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultationTable consultationTable = new ConsultationTable(currentManager);
            }
        });
        this.add(p1, BorderLayout.CENTER);

    }
}