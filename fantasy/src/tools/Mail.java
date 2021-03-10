/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpi.newpackage;

/**
 *
 * @author Mahdi
 */

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    private String username = "ligue1fantasy@gmail.com";
    private String password = "azerty123_";

    public void envoyer(Adherent u,Groupe g,String email) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ligue1fantasy@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Ajout au groupe "+g.getNom());
            message.setText("Vous avez été ajoutés au groupe "+g.getNom()+" de la part de "+u.getNom_user());
            Transport.send(message);
            System.out.println("message envoyé");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void envoyer1(String email) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ligue1fantasy@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Hi");
            message.setText("Welcome to Fantasy ligue1,\n  account activation!");
            Transport.send(message);
            System.out.println("message envoyé");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
