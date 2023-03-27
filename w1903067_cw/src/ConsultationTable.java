import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultationTable extends JFrame{
    List<Consultation> consultations;


    public ConsultationTable(WestMinsterSkinConsultationManager currentManager){
        consultations = currentManager.getConsultations();

        //frame layout
        setLayout(new BorderLayout());
        setTitle("WestMinster Skin Consultation Center");
        setSize(910, 540);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Consultation bookings");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        //consultation table creation
        JTable cTable = new JTable();
        cTable.setFont(new Font("Arial", Font.PLAIN,12));;
        cTable.setModel(new ConsultationTableModel(currentManager));
        cTable.setRowHeight(200);

        //button to decrypt images
//        JButton decryptBtn = new JButton("Decrypt");
//        add(decryptBtn, BorderLayout.SOUTH);
//        decryptBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                currentManager.DecryptImage();
//            }
//        });


        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(10, 1));
        centerPanel.add(cTable, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.NORTH);
        // create a JScrollPane and add the cTable to it
        JScrollPane scrollPane = new JScrollPane(cTable);
        scrollPane.setPreferredSize(new Dimension(900, 540));
        // add the scroll pane to the JFrame
        add(scrollPane);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);
    }
}
