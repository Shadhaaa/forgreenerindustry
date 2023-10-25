package tn.edu.forGreenerIndustry.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.fxml.FXML;
import tn.edu.forGreenerIndustry.services.MailService;

public class MailController {
    @FXML
    public TextField emailToField;
    @FXML
    public TextField emailFromField;
    @FXML
    public TextArea emailMessageField;
    @FXML
    public TextField emailSubjectField;
    @FXML
    public PasswordField emailPasswordField;
    @FXML
    public Label sentBoolValue;

    public void buttonClicked(ActionEvent actionEvent) {
        sendEmail();
    }

    public void sendEmail() {
        String to = emailToField.getText();
        String from = emailFromField.getText();
        String host = "smtp.gmail.com";
        final String username = emailFromField.getText();
        final String password = emailPasswordField.getText();

        // setup mail server
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(emailSubjectField.getText());
            m.setText(emailMessageField.getText());

            // send mail
            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendEmailButtonClicked(ActionEvent actionEvent) {
    String to = emailToField.getText(); // Récupérer l'adresse e-mail du destinataire
    String subject = emailSubjectField.getText(); // Récupérer le sujet de l'e-mail
    String body = emailMessageField.getText(); // Récupérer le corps de l'e-mail

    MailService mailService = new MailService();
    mailService.sendEmail(to, subject, body);
}

}



