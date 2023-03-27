import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DoctorTableModel extends AbstractTableModel{
    private String[] columnNames = {"Name","Specialisation","License Number","Mobile Number", "Date of birth"};
    private List<Doctor> list;

    public DoctorTableModel(List<Doctor> doctorList){
        this.list = doctorList;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    //pass doctor details values into the rows
    public Object getValueAt(int rowIndex, int columnIndex) {
        Doctor dd = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return dd.getName() + " " + dd.getSurname();
            case 1:
                return dd.getSpecialisation();
            case 2:
                return dd.getLicenseNo();
            case 3:
                return dd.getMobileNo();
            case 4:
                return dd.getDOB().getDayOfMonth() + "/" + dd.getDOB().getMonth().getValue()
                        + "/" + dd.getDOB().getYear();
        }
        return null;
    }

    //show column names in JTable
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

}
