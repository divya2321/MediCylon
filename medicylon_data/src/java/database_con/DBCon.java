/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author divsi
 */
public class DBCon {
    
    private static int i = 0;
    
    private static Connection c = null;
    
    private DBCon(){}
    
    public static synchronized Connection getConnection() throws Exception{
        if (c==null) {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
        }
        return c;
    }
    
    public static boolean iud(String query){
        try {
            i = getConnection().createStatement().executeUpdate(query);
        } catch (Exception ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (i==1) {
            return true;
        } else {
            return false;
        }
    }
    
    public static ResultSet search(String query){
        try {
            return getConnection().createStatement().executeQuery(query);
        } catch (Exception ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static int iudReturnId(String sql) {
        try {
            PreparedStatement prepareStatement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
