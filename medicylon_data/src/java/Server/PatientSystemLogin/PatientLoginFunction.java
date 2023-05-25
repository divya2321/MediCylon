/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.PatientSystemLogin;

import Server.User.*;
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
public class PatientLoginFunction {

    private static PatientLoginFunction instanePatientLogin = null;

    private PatientLoginFunction() {
    }

    public static synchronized PatientLoginFunction getPatientLoginFunction() {
        if (instanePatientLogin == null) {
            instanePatientLogin = new PatientLoginFunction();
        }
        return instanePatientLogin;
    }

   
    
     public String checkIdPassword(PatientLoginModel patientLoginModel) {
        String checkIdPassword = "select concat(general_detail.fname, ' ', general_detail.lname) as patientName from patient inner join "
                + "general_detail on patient.general_detail_idgeneral_detail = general_detail.idgeneral_detail "
                + "where id = '"+patientLoginModel.getId()+"' and password = '"+patientLoginModel.getPassword()+"' ";

        ResultSet rs_idpass = DBCon.search(checkIdPassword);
        try {
            if (rs_idpass.next()) {
                return rs_idpass.getString("patientName");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientLoginFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
