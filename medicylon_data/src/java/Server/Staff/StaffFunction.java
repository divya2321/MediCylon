package Server.Staff;

import database_con.DBCon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author divsi
 */
public class StaffFunction {

    private static StaffFunction instanceStaff = null;

    private StaffFunction() {
    }

    public static synchronized StaffFunction getStaffFunction() {
        if (instanceStaff == null) {
            instanceStaff = new StaffFunction();
        }
        return instanceStaff;
    }

    public boolean insertStaff(StaffModel staffModel) {
        int generalId = -1;

        String insertGeneralQuery = "INSERT INTO general_detail (nic, fname, mname, lname, contact1, contact2, email) "
                + "VALUES ('" + staffModel.getNic() + "','" + staffModel.getFname() + "','" + staffModel.getMname() + "','" + staffModel.getLname() + "', "
                + "'" + staffModel.getContact1() + "','" + staffModel.getContact2() + "','" + staffModel.getEmail() + "')";

        generalId = DBCon.iudReturnId(insertGeneralQuery);

        if (generalId != -1) {
            String insertStaffQuery = "INSERT INTO staff (job, status,general_detail_idgeneral_detail) VALUES "
                    + "('" + staffModel.getJob() + "', '" + staffModel.getStatus() + "', '" + generalId + "')";

            if (DBCon.iud(insertStaffQuery)) {
                String insertAddressQuery = "INSERT INTO address (no, street1, street2, city, general_detail_idgeneral_detail) VALUES "
                        + "('" + staffModel.getNo() + "', '" + staffModel.getStreet1() + "', '" + staffModel.getStreet2() + "', "
                        + "'" + staffModel.getCity() + "', '" + generalId + "')";

                if (DBCon.iud(insertAddressQuery)) {
                    return true;
                }
            }

        }

        return false;
    }

    public boolean updateStaff(StaffModel staffModel) {

        int generalId = getGeneralIdByStaffId(staffModel.getStaffId());

        String updateGeneralQuery = "UPDATE general_detail SET nic='" + staffModel.getNic() + "', fname='" + staffModel.getFname() + "', mname='" + staffModel.getMname() + "', lname='" + staffModel.getLname() + "', "
                + " contact1='" + staffModel.getContact1() + "', contact2='" + staffModel.getContact2() + "', email='" + staffModel.getEmail() + "' "
                + " WHERE idgeneral_detail='" + generalId + "'";

        if (DBCon.iud(updateGeneralQuery)) {
            String updateStaffQuery = "UPDATE staff SET job = '" + staffModel.getJob() + "', status = '" + staffModel.getStatus() + "' WHERE idstaff='" + staffModel.getStaffId() + "'";

            if (DBCon.iud(updateStaffQuery)) {
                String updteAddressQuery = "UPDATE address SET "
                        + " no = '" + staffModel.getNo() + "', street1 = '" + staffModel.getStreet1() + "', street2 = '" + staffModel.getStreet2() + "', "
                        + " city = '" + staffModel.getCity() + "' WHERE general_detail_idgeneral_detail = '" + generalId + "'";

                if (DBCon.iud(updteAddressQuery)) {
                    return true;
                }
            }

        }

        return false;
    }

    public int getGeneralIdByStaffId(int staffId) {

        String generalIdQuery = "select general_detail_idgeneral_detail from staff where idstaff = '" + staffId + "' ";

        ResultSet rs_generalId = DBCon.search(generalIdQuery);

        try {
            if (rs_generalId.next()) {
                return rs_generalId.getInt("general_detail_idgeneral_detail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<StaffModel> loadAllStaff() {
        ResultSet rs_staff;
        ArrayList<StaffModel> staffList = new ArrayList<>();
        try {
            rs_staff = DBCon.search("SELECT idstaff,job,status,nic,concat(fname,' ',mname,' ',lname) as name,"
                    + "concat(contact1,'/',contact2) as contact,email,concat(no,',',street1,',',street2,',',city) as address"
                    + " FROM staff INNER JOIN general_detail INNER JOIN address ON "
                    + "staff.general_detail_idgeneral_detail=general_detail.idgeneral_detail AND "
                    + "general_detail.idgeneral_detail=address.general_detail_idgeneral_detail");

            while (rs_staff.next()) {
                StaffModel staffModel = new StaffModel();
                staffModel.setNic(rs_staff.getString("nic"));
                staffModel.setName(rs_staff.getString("name"));
                String contact = rs_staff.getString("contact");
                if (contact.endsWith("/")) {
                    staffModel.setContact(contact.split("/")[0]);
                } else {
                    staffModel.setContact(contact);
                }
                staffModel.setEmail(rs_staff.getString("email"));

                String address = rs_staff.getString("address");
                if (address.contains(",,")) {
                    staffModel.setAddress(address.replace(",,", ","));
                } else {
                    staffModel.setAddress(address);
                }

                staffModel.setJob(rs_staff.getString("job"));
                staffModel.setStatus(rs_staff.getString("status"));
                staffModel.setStaffId(rs_staff.getInt("idstaff"));

                staffList.add(staffModel);
            }
        } catch (Exception ex) {
            Logger.getLogger(StaffFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staffList;
    }

    public ArrayList<StaffModel> loadAllIdNic() {
        String loadAllIdNicQuery = "SELECT CONCAT(idstaff, ' - ', nic) as nicid FROM staff INNER JOIN general_detail ON staff.general_detail_idgeneral_detail = general_detail.idgeneral_detail WHERE idstaff \n"
                + "NOT IN (SELECT idstaff FROM staff INNER JOIN user_account ON staff.idstaff = user_account.staff_idstaff)";

        ArrayList<StaffModel> staffList = new ArrayList<>();

        ResultSet rs_staff = DBCon.search(loadAllIdNicQuery);

        try {
            while (rs_staff.next()) {
                StaffModel staffModel = new StaffModel();
                staffModel.setNic(rs_staff.getString("nicid"));
                staffList.add(staffModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return staffList;
    }

    public ArrayList<StaffModel> loadAllTechnicianIdName() {
        String loadTechnicianQuery = "select CONCAT(s.idstaff, ' - ', g.fname, ' ', g.lname) as idname from staff s inner join general_detail g"
                + " on s.general_detail_idgeneral_detail = g.idgeneral_detail where s.job = 'technician'";

        ArrayList<StaffModel> staffList = new ArrayList<>();

        ResultSet rs_staff = DBCon.search(loadTechnicianQuery);

        try {
            while (rs_staff.next()) {
                StaffModel staffModel = new StaffModel();
                staffModel.setName(rs_staff.getString("idname"));
                staffList.add(staffModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return staffList;
    }

    public boolean isNicHave(String nic) {
        String checkNicQuery = "select nic from general_detail where nic = '" + nic + "' ";

        ResultSet rs_nic = DBCon.search(checkNicQuery);

        try {
            if (rs_nic.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static void main(String[] args) {
        StaffModel staffModel = new StaffModel();
        staffModel.setNic("2000000004");
        staffModel.setFname("Divyaaaaa");
        staffModel.setMname("Nimsararrr");
        staffModel.setLname("Sitinamaluwaaaa");
        staffModel.setContact1("0112705631");
        staffModel.setContact2("0778206800");
        staffModel.setEmail("nipun.sitinamaluwa@gmail.com");
        staffModel.setNo("62Aaaa");
        staffModel.setStreet1("Old rdddd");
        staffModel.setStreet2("Wetaraaaa");
        staffModel.setCity("Polgasowitaaaaa");
        staffModel.setJob("rec");
        staffModel.setStatus("0");
        staffModel.setStaffId(2);

        System.out.println(StaffFunction.getStaffFunction().loadAllStaff());

    }

}
