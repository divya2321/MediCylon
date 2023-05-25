/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.User;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author divsi
 */
public class UserFunctionTest {
    
    public UserFunctionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testInsertUser() {
        System.out.println("insertUser");
        UserModel userModel = new UserModel();
        userModel.setUsername("hansikaA99");
        userModel.setPassword("Hansika112233");
        userModel.setStatus("active");
        userModel.setStaffId("8");
        
        UserFunction instance = UserFunction.getUserFunction();
        boolean expResult = true;
        boolean result = instance.insertUser(userModel);
        assertEquals(expResult, result);
    }

    
    
   
    
    
    
   
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        UserModel userModel = new UserModel();
        userModel.setStatus("deactive");
        userModel.setStaffId("8");
        UserFunction instance = UserFunction.getUserFunction();
        boolean expResult = true;
        boolean result = instance.updateUser(userModel);
        assertEquals(expResult, result);
    }
    
    
    

    
    @Test
    public void testLoadAllUser() {
        System.out.println("loadAllUser");
        UserFunction instance = UserFunction.getUserFunction();
        ArrayList<UserModel> expResult =  new ArrayList<UserModel>();
         UserModel userModel = new UserModel();
        userModel.setUsername("anura1020");
        userModel.setPassword("sdsv848C");
        userModel.setStatus("active");
        userModel.setStaffId("3");
        expResult.add(userModel);
        
        ArrayList<UserModel> result = instance.loadAllUser();
        assertEquals(expResult.get(0).getUsername(), result.get(0).getUsername());
    }

   
    
}
