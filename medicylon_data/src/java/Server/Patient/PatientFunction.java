package Server.Patient;

import database_con.DBCon;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author divsi
 */
public class PatientFunction {

    private static PatientFunction instancePatient = null;

    private PatientFunction() {
    }

    public static synchronized PatientFunction getPatientFunction() {
        if (instancePatient == null) {
            instancePatient = new PatientFunction();
        }
        return instancePatient;
    }

    public boolean insertPatient(PatientModel patientModel) {
        int generalId = -1;

        String insertGeneralQuery = "INSERT INTO general_detail (nic, fname, mname, lname, contact1, contact2, email) "
                + "VALUES ('" + patientModel.getNic() + "','" + patientModel.getFname() + "','" + patientModel.getMname() + "','" + patientModel.getLname() + "', "
                + "'" + patientModel.getContact1() + "','" + patientModel.getContact2() + "','" + patientModel.getEmail() + "')";

        generalId = DBCon.iudReturnId(insertGeneralQuery);

        if (generalId != -1) {
            String insertPatientQuery = "INSERT INTO patient(id, password,date,general_detail_idgeneral_detail) VALUES "
                    + "('" + patientModel.getId() + "', '" + patientModel.getPassword() + "', NOW() , '" + generalId + "')";

            if (DBCon.iud(insertPatientQuery)) {
                return true;
            }
        }

        return false;
    }

    public boolean updatePatient(PatientModel patientModel) {

        String updateGeneralQuery = "UPDATE general_detail SET nic='" + patientModel.getNic() + "', fname='" + patientModel.getFname() + "',"
                + " mname='" + patientModel.getMname() + "', lname='" + patientModel.getLname() + "', contact1='" + patientModel.getContact1() + "',"
                + " contact2='" + patientModel.getContact2() + "', email='" + patientModel.getEmail() + "' "
                + " WHERE nic='" + patientModel.getOldNic() + "'";

        return DBCon.iud(updateGeneralQuery);
    }

    public ArrayList<PatientModel> loadAllPatient() {
        ResultSet rs_patient;
        ArrayList<PatientModel> patientList = new ArrayList<>();
        try {
            
            String query = "{CALL loadAllPatient()}";
            CallableStatement statement = DBCon.getConnection().prepareCall(query);
                rs_patient = statement.executeQuery();
        
//            rs_patient = DBCon.search("select g.nic, concat(g.fname,' ',g.mname,' ',g.lname) as name, concat(g.contact1,'/',g.contact2) as contact,"
//                    + "g.email, p.id,p.date from general_detail g inner join patient p on p.general_detail_idgeneral_detail = g.idgeneral_detail ");

            while (rs_patient.next()) {
                PatientModel patientModel = new PatientModel();
                patientModel.setNic(rs_patient.getString("g.nic"));
                patientModel.setName(rs_patient.getString("name"));
                String contact = rs_patient.getString("contact");
                if (contact.endsWith("/")) {
                    patientModel.setContact(contact.split("/")[0]);
                } else {
                    patientModel.setContact(contact);
                }
                patientModel.setEmail(rs_patient.getString("g.email"));

              

                patientModel.setId(rs_patient.getString("p.id"));
                patientModel.setDate(rs_patient.getString("p.date"));

                patientList.add(patientModel);
            }
        } catch (Exception ex) {
            Logger.getLogger(PatientFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patientList;
    }
    
    public int getPatientIdByNic(String nic){
        
        String patientIdQuery = "select p.idpatient from patient p inner join general_detail g on p.general_detail_idgeneral_detail = g.idgeneral_detail "
                + "where g.nic = '"+nic+"' ";
        
        ResultSet rs_patientId = DBCon.search(patientIdQuery);
        
        try {
            if(rs_patientId.next()){
                return rs_patientId.getInt("p.idpatient");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }

        public String getPatientEmailByNic(String nic){
           String email = "select email from general_detail where nic = '"+nic+"' ";
           ResultSet rs_email = DBCon.search(email);
           
        try {
            if(rs_email.next()){
               return rs_email.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       }
    
   

    public static void main(String[] args) {
//        StaffModel staffModel = new StaffModel();
//        staffModel.setNic("2000000004");
//        staffModel.setFname("Divyaaaaa");
//        staffModel.setMname("Nimsararrr");
//        staffModel.setLname("Sitinamaluwaaaa");
//        staffModel.setContact1("0112705631");
//        staffModel.setContact2("0778206800");
//        staffModel.setEmail("nipun.sitinamaluwa@gmail.com");
//        staffModel.setNo("62Aaaa");
//        staffModel.setStreet1("Old rdddd");
//        staffModel.setStreet2("Wetaraaaa");
//        staffModel.setCity("Polgasowitaaaaa");
//        staffModel.setJob("rec");
//        staffModel.setStatus("0");
//        staffModel.setStaffId(2);
//
        System.out.println(new PatientFunction().getPatientEmailByNic("998429450V"));

    }

}
