/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Appointment;

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
public class AppointmentFunctionTest {
    
    public AppointmentFunctionTest() {
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
    public void testInsertAppointment() {
        System.out.println("insertAppointment");
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setPatientId("1");
        appointmentModel.setTestId("1");
        appointmentModel.setDoctor("Dr. Maharage");
        appointmentModel.setAppointmentDate("2020-11-22");
        appointmentModel.setAppointmentTime("09:00");
        appointmentModel.setAppointmentNo("1");
        appointmentModel.setUsername("hansikaA99");
        appointmentModel.setStatus("appointed");
        appointmentModel.setAmount("2000");
        appointmentModel.setPaymentType("card");
        AppointmentFunction instance = AppointmentFunction.getAppointmentFunction();
        boolean expResult = true;
        boolean result = instance.insertAppointment(appointmentModel);
        assertEquals(expResult, result);
    }
    
    
    
    

    @Test
    public void testUpdateAppointment() {
        System.out.println("updateAppointment");
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setDoctor("Dr. Kumarage");
        appointmentModel.setAppointmentDate("2020-10-22");
        appointmentModel.setAppointmentTime("09:30");
        appointmentModel.setAppointmentNo("2");
        appointmentModel.setAppointmentCode("16");
        AppointmentFunction instance = AppointmentFunction.getAppointmentFunction();
        boolean expResult = true;
        boolean result = instance.updateAppointment(appointmentModel);
        assertEquals(expResult, result);
    }
    

    @Test
    public void testCancelAppointment() {
        System.out.println("cancelAppointment");
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setStatus("canceled");
        appointmentModel.setAppointmentCode("16");
        AppointmentFunction instance = AppointmentFunction.getAppointmentFunction();
        boolean expResult = true;
        boolean result = instance.cancelAppointment(appointmentModel);
        assertEquals(expResult, result);
    }
    
    
    

   
    @Test
    public void testGetAllAppointment() {
        System.out.println("getAllAppointment");
        AppointmentFunction instance = AppointmentFunction.getAppointmentFunction();
        ArrayList<AppointmentModel> expResult = new ArrayList<AppointmentModel>();
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setPatientId("1");
        appointmentModel.setTestId("1");
        appointmentModel.setDoctor("Dr. Maharage");
        appointmentModel.setAppointmentDate("2020-11-22");
        appointmentModel.setAppointmentTime("09:00");
        appointmentModel.setAppointmentNo("1");
        appointmentModel.setUsername("hansikaA99");
        appointmentModel.setStatus("appointed");
        appointmentModel.setAmount("2000");
        appointmentModel.setPaymentType("card");
        appointmentModel.setAppointmentCode("4");
        expResult.add(appointmentModel);
        ArrayList<AppointmentModel> result = instance.getAllAppointment();
        assertEquals(expResult.get(0).getAppointmentCode(), result.get(0).getAppointmentCode());
    }

  
}
