/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Patient;

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
public class PatientFunctionTest {
    
    public PatientFunctionTest() {
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
    public void testInsertPatient() {
        System.out.println("insertPatient");
        PatientModel patientModel = new PatientModel();
        patientModel.setNic("654535162V");
        patientModel.setFname("Jayawathi");
        patientModel.setLname("Kodithuwakku");
        patientModel.setContact1("0774125699");
        
        PatientFunction instance = PatientFunction.getPatientFunction();
        boolean expResult = true;
        boolean result = instance.insertPatient(patientModel);
        assertEquals(expResult, result);
    }
    

    
    @Test
    public void testUpdatePatient() {
        System.out.println("updatePatient");
         PatientModel patientModel = new PatientModel();
        patientModel.setNic("6545351452V");
        patientModel.setFname("Jaya");
        patientModel.setLname("Kodithuwakku");
        patientModel.setContact1("0774125699");
        patientModel.setOldNic("654535162V");
        PatientFunction instance = PatientFunction.getPatientFunction();
        boolean expResult = true;
        boolean result = instance.updatePatient(patientModel);
        assertEquals(expResult, result);
    }
    
  
    @Test
    public void testLoadAllPatient() {
        System.out.println("loadAllPatient");
        PatientFunction instance = PatientFunction.getPatientFunction();
        ArrayList<PatientModel> expResult = new ArrayList<PatientModel>();
        PatientModel patientModel = new PatientModel();
        patientModel.setNic("625623659V");
        patientModel.setFname("Shashika");
        patientModel.setLname("Kodithuwakku");
        patientModel.setContact1("077542699");
        expResult.add(patientModel);
        
        ArrayList<PatientModel> result = instance.loadAllPatient();
        assertEquals(expResult.get(0).getNic(), result.get(0).getNic());
    }

    
    
    
    
  
}
