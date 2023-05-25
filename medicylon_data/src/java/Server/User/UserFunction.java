/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.User;

import database_con.DBCon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.pkcs11.Secmod;

/**
 *
 * @author divsi
 */
public class UserFunction {

    private static UserFunction instaneUser = null;

    private UserFunction() {
    }

    public static synchronized UserFunction getUserFunction() {
        if (instaneUser == null) {
            instaneUser = new UserFunction();
        }
        return instaneUser;
    }

    public boolean insertUser(UserModel userModel) {

        String insertUserQuery = "insert into user_account(username, password, status, staff_idstaff) values('" + userModel.getUsername() + "', "
                + " '" + userModel.getPassword() + "', '" + userModel.getStatus() + "', '" + userModel.getStaffId() + "' )";
        
        System.out.println(insertUserQuery);
        return DBCon.iud(insertUserQuery);
    }

    public boolean updateUser(UserModel userModel) {

        String updateUserQuery = "update user_account set status = '" + userModel.getStatus() + "'"
                + " where staff_idstaff = '" + userModel.getStaffId()+ "' ";

        return DBCon.iud(updateUserQuery);
    }

    public ArrayList<UserModel> loadAllUser() {
        String loadUserQuery = "select g.nic, s.idstaff,CONCAT(g.fname,' ', g.mname, ' ', g.lname) as name, u.username, u.password, u.`status` from user_account u inner join staff s inner join general_detail g on u.staff_idstaff = s.idstaff and s.general_detail_idgeneral_detail = g.idgeneral_detail";

        ResultSet userResultSet = DBCon.search(loadUserQuery);

        ArrayList<UserModel> userList = new ArrayList<UserModel>();
        try {
            while (userResultSet.next()) {

                UserModel userModel = new UserModel();
                userModel.setStaffId(userResultSet.getString("s.idstaff"));
                userModel.setStaffNic(userResultSet.getString("g.nic"));
                userModel.setStaffName(userResultSet.getString("name"));
                userModel.setUsername(userResultSet.getString("u.username"));
                userModel.setPassword(userResultSet.getString("u.password"));
                userModel.setStatus(userResultSet.getString("u.status"));
                userList.add(userModel);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userList;
    }

    public boolean isUsernameHave(String username) {
        String checkUsername = "select username from user_account where username ='" + username + "' ";

        ResultSet rs_username = DBCon.search(checkUsername);
        try {
            if (rs_username.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public UserModel checkUsernamePassword(UserModel model) {
        String checkUsernamePassword = "select u.username,s.job, concat(g.fname, ' ', g.lname) as name from user_account u inner join staff s inner join general_detail g "
                + "on u.staff_idstaff = s.idstaff and s.general_detail_idgeneral_detail = g.idgeneral_detail "
                + "where u.username = '"+model.getUsername()+"' and u.password = '"+model.getPassword()+"' and u.status = 'active' ";

        ResultSet rs_usernamepass = DBCon.search(checkUsernamePassword);
        try {
            if (rs_usernamepass.next()) {
                UserModel userModel = new UserModel();
                userModel.setUsername(rs_usernamepass.getString("u.username"));
                userModel.setStaffName(rs_usernamepass.getString("name"));
                userModel.setJob(rs_usernamepass.getString("s.job"));
                return userModel;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
      public boolean chagePassword(UserModel userModel) {

        String updateUserQuery = "update user_account set password = '" + userModel.getPassword()+ "'"
                + " where username = '" + userModel.getUsername()+ "' ";

        return DBCon.iud(updateUserQuery);
    }

     
    
    
    public static void main(String[] args) {
        UserModel m = new UserModel();
        m.setUsername("divz2123");
        m.setPassword("IoX/EeaCah541r4v93Od+g==");
        
        System.out.println(new UserFunction().checkUsernamePassword(m));
    }

}
