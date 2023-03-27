import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class DoctorTable extends JFrame {

    JTable docTable;
    DoctorTableModel tableModel;

    // constructor
    public DoctorTable( List<Doctor> list) {

        JTable table = new JTable();
        tableModel = new DoctorTableModel(list);
        docTable = new JTable(tableModel);
        table.setModel(new DoctorTableModel(list));

        setTitle("WestMinster Skin Consultation Center");
        setBounds(10, 10, 910, 540);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        docTable.setAutoCreateRowSorter(true);
        JLabel nameLabel = new JLabel("Doctors");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        add(nameLabel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(docTable);
        scrollPane.setPreferredSize(new Dimension(900, 540));

        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);
    }


}
