/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaMailProvider;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author divsi
 */
public class MailProvider {

    public static String sendEmail(String emailReceiver, String subject, String message) {

        String emailSender = "divya.sitinamaluwa@gmail.com";
        String password = "divya@Gmail2123";

        Properties pr = new Properties();

        pr.put("mail.smtp.auth", "true");
        pr.put("mail.smtp.starttls.enable", "true");
        pr.put("mail.smtp.host", "smtp.gmail.com");
        pr.put("mail.smtp.port", "587");

        Session gs = Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSender, password);

            }
        });

        try {
            Message ms = messageContent(gs, emailSender, emailReceiver, subject, message);
        } catch (Exception ex) {
            Logger.getLogger(MailProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Email sent! ";

    }

    private static Message messageContent(Session gs, String emailId, String reciever, String subject, String message) throws Exception {
        try {

            Message msg = new MimeMessage(gs);
            msg.setFrom(new InternetAddress(emailId));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            Transport.send(msg);
            return msg;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        String patientEmail = "nipun.jayasanka10@gmail.com";
        String subject = "About your appointment";
        String message = "<p>Test :</p>"
                + " <p>Appointment date: </p>"
                + " <p>Appointment time:</p>"
                + " <p>Appointment no: </p>"
                + " <p>Payment Type: </p>"
                + " <p>Amount:</p>";

        JavaMailProvider.MailProvider.sendEmail(patientEmail, subject, message);
    }
}
