package Server.Appointment;

import database_con.DBCon;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author divsi
 */
public class AppointmentFunction {

    private static AppointmentFunction instanceAppointment = null;

    private AppointmentFunction() {
    }

    public static synchronized AppointmentFunction getAppointmentFunction() {
        if (instanceAppointment == null) {
            instanceAppointment = new AppointmentFunction();
        }
        return instanceAppointment;
    }

    public boolean insertAppointment(AppointmentModel appointmentModel) {
        int appointmentCode = -1;
        
        String insertAppointmentQuery = "INSERT INTO appointment(patient_idpatient, test_idtest, doctor, appointmentdate, appointmenttime, appointmentno"
                + ",appointeddate,status, user_account_username) VALUES ('"+appointmentModel.getPatientId()+"', '"+appointmentModel.getTestId()+"',"
                + " '"+appointmentModel.getDoctor()+"', '"+appointmentModel.getAppointmentDate()+"', '"+appointmentModel.getAppointmentTime()+"', "
                + " '"+appointmentModel.getAppointmentNo()+"', now(), '"+appointmentModel.getStatus()+"', "
                + " '"+appointmentModel.getUsername()+"')";
        
        System.out.println(insertAppointmentQuery);

        appointmentCode = DBCon.iudReturnId(insertAppointmentQuery);

        if (appointmentCode != -1) {
            String insertInvoiceQuery = "INSERT INTO invoice(amount, type,appointment_appointmentcode) VALUES "
                    + "('" + appointmentModel.getAmount()+ "', '" + appointmentModel.getPaymentType() + "', '" + appointmentCode + "')";

            System.out.println(insertInvoiceQuery);
             return DBCon.iud(insertInvoiceQuery);
            
        }

        return false;
    }

    public boolean updateAppointment(AppointmentModel appointmentModel) {
        
        int appointmentNo = getAppointmentNo(appointmentModel.getTestCategory(), appointmentModel.getAppointmentDate());

        String updateAppointmentQuery = "UPDATE appointment SET doctor='" + appointmentModel.getDoctor() + "', appointmentdate ='" + appointmentModel.getAppointmentDate()+ "',"
                + " appointmenttime='" + appointmentModel.getAppointmentTime() + "', appointmentno='" + appointmentNo+ "' "
                + " WHERE appointmentcode ='" + appointmentModel.getAppointmentCode()+ "' ";

        return DBCon.iud(updateAppointmentQuery);
    }
    
    
     public boolean uploadReport(AppointmentModel appointmentModel) {
        
         PreparedStatement preparedStatement = null;
         
        try {
           preparedStatement = DBCon.getConnection().prepareStatement("insert into test_report(report,user_account_username,appointment_appointmentcode) values(?,?,?) ");
           preparedStatement.setBinaryStream(1, new ByteArrayInputStream(appointmentModel.getReport()));
           preparedStatement.setString(2, appointmentModel.getUsername());
           preparedStatement.setString(3, appointmentModel.getAppointmentCode());

           
           
           int i = preparedStatement.executeUpdate();
           if(i == 1){
               return true;
           }
           
        } catch (Exception ex) {
            Logger.getLogger(AppointmentFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
    
    
    
    public int getAppointmentNo(String category, String appointmentdate){
        
        String appointmentNo = "SELECT COUNT(a.appointmentcode) AS appointmentno, c.testperday FROM appointment a INNER JOIN test t INNER JOIN "
                + "test_category c ON a.test_idtest = t.idtest AND t.test_category_idtest_category=c.idtest_category "
                + "WHERE a.appointmentdate='"+appointmentdate+"' AND c.category='"+category+"' GROUP BY c.category";
        
        ResultSet rs_appointmentno = DBCon.search(appointmentNo);
        
        try {
            if(rs_appointmentno.next()){
                int no = rs_appointmentno.getInt("appointmentno");
                return ++ no;
            }else{
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 1;
    }
    
       public boolean cancelAppointment(AppointmentModel appointmentModel) {

        String cancelAppointmentQuery = "UPDATE appointment SET status='" + appointmentModel.getStatus()+ "' WHERE appointmentcode ='" + appointmentModel.getAppointmentCode()+ "'";

           System.out.println(cancelAppointmentQuery);
        return DBCon.iud(cancelAppointmentQuery);
    }
       

       public ArrayList<AppointmentModel> getAllAppointment(){
           
           String allAppointmentQuery = "select a.appointmentcode,p.id,g.nic,t.test,a.doctor,a.appointmentdate,a.appointmenttime,a.appointmentno,"
                   + "a.appointeddate,a.status, concat(gg.fname, ' ', gg.lname) as technician,i.amount,i.type,concat(ggg.fname, ' ', ggg.lname) as username "
                   + "from appointment a inner join invoice i inner join patient p inner join general_detail g inner join test t inner join "
                   + "test_category tt inner join staff s inner join general_detail gg inner join user_account u inner join staff ss inner join "
                   + "general_detail ggg on a.appointmentcode =i.appointment_appointmentcode and a.patient_idpatient=p.idpatient and "
                   + "p.general_detail_idgeneral_detail=g.idgeneral_detail and a.test_idtest=t.idtest and t.test_category_idtest_category=tt.idtest_category "
                   + "and tt.staff_idstaff=s.idstaff and s.general_detail_idgeneral_detail=gg.idgeneral_detail and a.user_account_username = u.username "
                   + "and u.staff_idstaff = ss.idstaff and ss.general_detail_idgeneral_detail = ggg.idgeneral_detail";
           
           ResultSet rs_appointment = DBCon.search(allAppointmentQuery);
           ArrayList<AppointmentModel> appointmentList = new ArrayList<AppointmentModel>();
           
        try {
            while(rs_appointment.next()){
                
                AppointmentModel appointmentModel = new AppointmentModel();
                
                appointmentModel.setAppointmentCode(rs_appointment.getString("a.appointmentcode"));
                appointmentModel.setPatientId(rs_appointment.getString("p.id"));
                appointmentModel.setDoctor(rs_appointment.getString("a.doctor"));
                appointmentModel.setAppointmentDate(rs_appointment.getString("a.appointmentdate"));
                appointmentModel.setAppointmentTime(rs_appointment.getString("a.appointmenttime"));
                appointmentModel.setAppointmentNo(rs_appointment.getString("a.appointmentno"));
                appointmentModel.setAppointedDate(rs_appointment.getString("a.appointeddate"));
                appointmentModel.setStatus(rs_appointment.getString("a.status"));
                appointmentModel.setTechnician(rs_appointment.getString("technician"));
                appointmentModel.setUsername(rs_appointment.getString("username"));
                appointmentModel.setAmount(rs_appointment.getString("i.amount"));
                appointmentModel.setPaymentType(rs_appointment.getString("i.type"));
                appointmentModel.setTest(rs_appointment.getString("t.test"));
                appointmentModel.setPatientNic(rs_appointment.getString("g.nic"));
                
                appointmentList.add(appointmentModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
         return appointmentList;
         
       }
       
       
       public boolean canPlaceAppointment(AppointmentModel appointmentModel){
           
            String checkAppointment = "SELECT COUNT(a.appointmentcode) AS appointmentno, c.testperday FROM appointment a INNER JOIN test t INNER JOIN "
                + "test_category c ON a.test_idtest = t.idtest AND t.test_category_idtest_category=c.idtest_category "
                + "WHERE a.appointmentdate='"+appointmentModel.getAppointmentDate()+"' AND c.category='"+appointmentModel.getTestCategory()+"' GROUP BY c.category";
        
        ResultSet rs_checkAppointment = DBCon.search(checkAppointment);
        
        try {
            if(rs_checkAppointment.next()){
                 int no = rs_checkAppointment.getInt("appointmentno");
                 int maxNo = rs_checkAppointment.getInt("c.testperday");
                 
                 if(no>=maxNo){
                     return false;
                 }
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
       }

       
       public AppointmentModel loadReport(AppointmentModel appointmentModel){
           String s = "select report from test_report where appointment_appointmentcode = '"+appointmentModel.getAppointmentCode()+"' ";
           
           ResultSet rs = DBCon.search(s);
           
        try {
            if(rs.next()){
               appointmentModel.setReport(rs.getBytes("report"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appointmentModel;
       }
       
   

    public static void main(String[] args) {
      AppointmentFunction appointmentOperation = new AppointmentFunction();

    }

}
