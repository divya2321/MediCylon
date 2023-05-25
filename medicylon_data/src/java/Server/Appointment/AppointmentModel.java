/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Appointment;

import java.io.InputStream;

/**
 *
 * @author divsi
 */
public class AppointmentModel {
    
    
    private String patientNic;
    private String patientId;
    private String test;
    private String testCategory;
    private String testId;
    private String username;
    private String technician;
    private String amount;
    private String paymentType;
    private String appointmentCode;
    private String doctor;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentNo;
    private String appointedDate;
    private String status;
    private byte[] report;

    /**
     * @return the patientNic
     */
    public String getPatientNic() {
        return patientNic;
    }

    /**
     * @param patientNic the patientNic to set
     */
    public void setPatientNic(String patientNic) {
        this.patientNic = patientNic;
    }

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the test
     */
    public String getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(String test) {
        this.test = test;
    }

    /**
     * @return the testId
     */
    public String getTestId() {
        return testId;
    }

    /**
     * @param testId the testId to set
     */
    public void setTestId(String testId) {
        this.testId = testId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the appointmentCode
     */
    public String getAppointmentCode() {
        return appointmentCode;
    }

    /**
     * @param appointmentCode the appointmentCode to set
     */
    public void setAppointmentCode(String appointmentCode) {
        this.appointmentCode = appointmentCode;
    }

    /**
     * @return the doctor
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the appointmentDate
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return the appointmentTime
     */
    public String getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * @param appointmentTime the appointmentTime to set
     */
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * @return the appointmentNo
     */
    public String getAppointmentNo() {
        return appointmentNo;
    }

    /**
     * @param appointmentNo the appointmentNo to set
     */
    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    /**
     * @return the appointedDate
     */
    public String getAppointedDate() {
        return appointedDate;
    }

    /**
     * @param appointedDate the appointedDate to set
     */
    public void setAppointedDate(String appointedDate) {
        this.appointedDate = appointedDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the testCategory
     */
    public String getTestCategory() {
        return testCategory;
    }

    /**
     * @param testCategory the testCategory to set
     */
    public void setTestCategory(String testCategory) {
        this.testCategory = testCategory;
    }

    /**
     * @return the report
     */
    public byte[] getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(byte[] report) {
        this.report = report;
    }

    /**
     * @return the technician
     */
    public String getTechnician() {
        return technician;
    }

    /**
     * @param technician the technician to set
     */
    public void setTechnician(String technician) {
        this.technician = technician;
    }
    
}
