/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Test;

import Server.Staff.StaffFunction;
import Server.User.UserFunction;
import Server.User.UserModel;
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
public class TestFunction {

    private static TestFunction instanetTest = null;

    private TestFunction() {
    }

    public static synchronized TestFunction getTestFunction() {
        if (instanetTest == null) {
            instanetTest = new TestFunction();
        }
        return instanetTest;
    }

    public boolean insertTestCategory(TestModel testModel) {

        String insertTestQuery = "insert into test_category(category, testperday, roomno, staff_idstaff) values('" + testModel.getTestCategory() + "', "
                + " '" + testModel.getTestPerDay() + "', '" + testModel.getRoomNo() + "', '" + testModel.getStaffId() + "' )";

        return DBCon.iud(insertTestQuery);
    }

    public boolean updateTestCategory(TestModel testModel) {

        String updateTestQuery = "update test_category set category = '" + testModel.getTestCategory() + "', testperday = '" + testModel.getTestPerDay() + "', "
                + " roomno = '" + testModel.getRoomNo() + "', staff_idstaff = '" + testModel.getStaffId() + "'  where category = '" + testModel.getOldTestCategory() + "' ";

        return DBCon.iud(updateTestQuery);
    }
    
     public ArrayList<TestModel> loadAllTestCategory() {
        ArrayList<TestModel> testList = new ArrayList<TestModel>();
        try {
//        String loadTestQuery = "select CONCAT(s.idstaff,' - ', g.fname,' ',g.lname) as techname, c.category, c.testperday, c.roomno from test_category c inner join staff s inner join general_detail g  on c.staff_idstaff = s.idstaff and  s.general_detail_idgeneral_detail = g.idgeneral_detail";

        String query = "{CALL loadAllTestCategory()}";
            CallableStatement statement = DBCon.getConnection().prepareCall(query);
        ResultSet testResultSet = statement.executeQuery();

            while (testResultSet.next()) {

                TestModel testModel = new TestModel();

                testModel.setStaffName(testResultSet.getString("techname"));
                testModel.setTestCategory(testResultSet.getString("c.category"));
                testModel.setTestPerDay(testResultSet.getString("c.testperday"));
                testModel.setRoomNo(testResultSet.getString("c.roomno"));

                testList.add(testModel);

            }
        } catch (Exception ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return testList;
    }

    public ArrayList<TestModel> loadAllTest() {
        ArrayList<TestModel> testList = new ArrayList<TestModel>();
        try {
            String query = "{CALL loadAllTest()}";
            CallableStatement statement = DBCon.getConnection().prepareCall(query);
//        String loadTestQuery = "select CONCAT(g.fname,' ',g.lname) as techname, s.idstaff, c.category, c.testperday, c.roomno, t.test, t.price from test_category c inner join staff s inner join general_detail g inner join test t on c.staff_idstaff = s.idstaff and t.test_category_idtest_category = c.idtest_category and s.general_detail_idgeneral_detail = g.idgeneral_detail";

        ResultSet testResultSet =statement.executeQuery();

            while (testResultSet.next()) {

                TestModel testModel = new TestModel();

                testModel.setStaffName(testResultSet.getString("techname"));
                testModel.setStaffId(testResultSet.getString("s.idstaff"));
                testModel.setTestCategory(testResultSet.getString("c.category"));
                testModel.setTestPerDay(testResultSet.getString("c.testperday"));
                testModel.setRoomNo(testResultSet.getString("c.roomno"));
                testModel.setTestName(testResultSet.getString("t.test"));
                testModel.setPrice(testResultSet.getString("t.price"));

                testList.add(testModel);

            }
        } catch (Exception ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return testList;
    }

    public boolean insertTest(TestModel testModel) {

        String insertTestQuery = "insert into test(test, price, test_category_idtest_category) values('" + testModel.getTestName()+ "', "
                + " '" + testModel.getPrice()+ "', '" + loadCategoryId(testModel.getTestCategory()) + "' )";

        return DBCon.iud(insertTestQuery);
    }

    public boolean updateTest(TestModel testModel) {

        String updateTestQuery = "update test set test = '" + testModel.getTestName()+ "', price = '" + testModel.getPrice()+ "', "
                + " test_category_idtest_category = '" + loadCategoryId(testModel.getTestCategory())  + "' where test = '" + testModel.getOldTestName()+ "' ";

        return DBCon.iud(updateTestQuery);
    }
    
    public int loadCategoryId(String category){
        String loadIdQuery = "select idtest_category from test_category where category = '"+category+"' ";
        
        ResultSet idResultSet = DBCon.search(loadIdQuery);
        
        try {
            if(idResultSet.next()){
                return idResultSet.getInt("idtest_category");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
     public boolean isTestCategoryHave(String testCategory) {
        String checkCategory = "select category from test_category where category ='" + testCategory + "' ";

        ResultSet rs_Caregory = DBCon.search(checkCategory);
        try {
            if (rs_Caregory.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
         public boolean isTestHave(String test) {
        String checkTest = "select test from test where test ='" + test + "' ";

        ResultSet rs_Test = DBCon.search(checkTest);
        try {
            if (rs_Test.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
         
    public String getCategoryByTest(String test) {

        String categoryQuery = "select c.category from test t inner join test_category c on t.test_category_idtest_category = "
                + "c.idtest_category where t.test = '" + test + "' ";

        ResultSet rs_category = DBCon.search(categoryQuery);

        try {
            if (rs_category.next()) {
                return rs_category.getString("c.category");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
       public int getTestIdByTest(String test) {

        String testIdQuery = "select t.idtest from test t inner join test_category c on t.test_category_idtest_category = "
                + "c.idtest_category where t.test = '" + test + "' ";

        ResultSet rs_testId = DBCon.search(testIdQuery);

        try {
            if (rs_testId.next()) {
                return rs_testId.getInt("t.idtest");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
       
       public String getTestPriceByTest(String test){
           
           String priceQuery = "select price from test where test = '"+test+"' ";
           
           ResultSet rs_price = DBCon.search(priceQuery);
           
        try {
            if(rs_price.next()){
                return rs_price.getString("price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
       }
       
         public static void main(String[] args) {
        TestFunction testFunction = TestFunction.getTestFunction();
        
        TestModel test =  new TestModel();
        
        test.setTestCategory("Blood Test");
        test.setTestName("Suger Test");
        test.setPrice("2000");
        test.setOldTestName("Suger Testtt");
             System.out.println(testFunction.getTestIdByTest("Suger Test"));
        
    }

}
