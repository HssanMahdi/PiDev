/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Mahdi
 */

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void envoyer2(ManagerFootball u, String nom, String prenom, String position, int prix, String ideq) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            String requete = "SELECT email FROM user WHERE type_user='MF' ;";

            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ligue1fantasy@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rs.getString("email")));
                message.setSubject("Ajout d'un nouveau joueur");
                message.setText("Le football manager " + u.getNom_user() + " a ajouté un nouveau joueur avec les coordonnées ci dessous : \n Nom :  " + nom + " \n Prenom = " + prenom + " \n Position = " + position + "\n Equipe = " + ideq);
                Transport.send(message);
                System.out.println("Message envoyé");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void notifSupp(ManagerFootball u, String nom, String prenom) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            String requete = "SELECT email FROM user WHERE type_user='MF' ;";

            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ligue1fantasy@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rs.getString("email")));
                message.setSubject("Notification de suppression d'un joueur");
                message.setText("Le Football Manager " + u.getNom_user() + " a supprimé le joueur " + nom + " " + prenom + ".");
                Transport.send(message);
                System.out.println("Message envoyé");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void envoyerPub(ManagerFootball u, String nom, Date datePub) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            String requete = "SELECT email FROM user WHERE type_user='MF' ;";

            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ligue1fantasy@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rs.getString("email")));
                message.setSubject("Ajout d'un nouveau joueur");
                message.setText("Le football manager " + u.getNom_user() + " a publié une nouvelle 'Highlights' dont le nom est \" " + nom + " \" et la date de cette publication est " + datePub );
                Transport.send(message);
                System.out.println("Message envoyé");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
}
