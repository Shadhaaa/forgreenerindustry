package tn.edu.forGreenerIndustry.tools;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;

public class Myemail1 {

    private MimeMessage message;

    public Myemail1() throws AddressException {
        try {
            String from = "slimi.amal@esprit.tn"; // sender's email address
            String password = "223JFT5859"; // sender's password
            String host = "smtp.gmail.com"; // Gmail SMTP server address
            String port = "465"; // SMTP port
            
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");
            
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            
            this.message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
        } catch (MessagingException ex) {
            Logger.getLogger(Myemail1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMail(String to, String subject, String html) throws MessagingException {
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setContent(html, "text/html");
        Transport.send(message);
    }

    public void sendMailAsync(String to, String subject, String html) {
        new Thread(() -> {
            try {
                sendMail(to, subject, html);
            } catch (MessagingException ex) {
                Logger.getLogger(Myemail1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

}
//////