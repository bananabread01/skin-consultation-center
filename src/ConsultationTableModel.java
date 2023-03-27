import javax.swing.table.AbstractTableModel;
import java.util.List;

class ConsultationTableModel extends AbstractTableModel {

    private String[] columnNames = {"Consultation No", "Consultation Date & Time", "Doctor License Number",
            "Doctor Name", "Patient ID", "Patient Name", "Patient Date of Birth", "Notes", "Image", "Cost"};
    private List<Consultation> consultationsList;

    public ConsultationTableModel(WestMinsterSkinConsultationManager currentManager) {
        consultationsList = currentManager.getConsultations();
    }

    @Override
    public int getRowCount() {
        return consultationsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // 9
    }


    //values of the consultation data passed into table rows
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consultation cc = consultationsList.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> cc.getConsultationNo();

            case 1 -> cc.getDate().getDayOfMonth() + "/" + cc.getDate().getMonth().getValue()
                    + "/" + cc.getDate().getYear() + " " + cc.getDate().getHour() + ":" + cc.getDate().getMinute();

            case 2 -> cc.getDoctor().getLicenseNo();

            case 3 -> cc.getDoctor().getName() + " " + cc.getDoctor().getSurname();

            case 4 -> cc.getPatient().getPatientId();

            case 5 -> cc.getPatient().getName() + " " + cc.getPatient().getSurname();

            case 6 -> cc.getPatient().getDOB().getDayOfMonth() + "/" + cc.getPatient().getDOB().getMonth().getValue()
                    + "/" + cc.getPatient().getDOB().getYear();

            case 7 -> cc.getNotes();

            case 8 -> cc.getImage();

            case 9 -> cc.getCost();

            default -> null;
        };
    }

    public String getColumnName(int column) {
        return this.columnNames[column];
    }

}




