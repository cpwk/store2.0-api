package com.maidao.edu.store.common.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailHelper {

    public static void sendMail(MailConnectionInfo connectionInfo, MailInfo mailInfo) throws MessagingException {
        Session session = Session.getDefaultInstance(connectionInfo.getProperties(), connectionInfo.getAuthenticator());
        session.setDebug(true);
        MimeMessage mail = new MimeMessage(session);
        Address from = new InternetAddress(
                mailInfo.getFromAddress() != null ? mailInfo.getFromAddress() : connectionInfo.getUsername());

        String nick = "";
        try {
            nick = javax.mail.internet.MimeUtility.encodeText("迈道科技");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mail.setFrom(new InternetAddress(nick + " <" + from + ">"));

        if (mailInfo.getToAddresses() != null) {
            String[] addrs = mailInfo.getToAddresses();
            Address[] addresses = new Address[addrs.length];
            for (int i = 0; i < addrs.length; i++) {
                addresses[i] = new InternetAddress(addrs[i]);
            }
            mail.setRecipients(Message.RecipientType.TO, addresses);
        } else {
            Address to = new InternetAddress(mailInfo.getToAddress());
            mail.setRecipient(Message.RecipientType.TO, to);
        }

        //抄送
        if (mailInfo.getToAddresses_cc() != null) {
            String[] addrs = mailInfo.getToAddresses_cc();
            Address[] addresses = new Address[addrs.length];
            for (int i = 0; i < addrs.length; i++) {
                addresses[i] = new InternetAddress(addrs[i]);
            }
            mail.setRecipients(Message.RecipientType.CC, addresses);
        }

        mail.setSubject(mailInfo.getSubject());
        if (mailInfo.getContent() != null) {
            Multipart content = new MimeMultipart();
            BodyPart part = new MimeBodyPart();
            part.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            content.addBodyPart(part);
            mail.setContent(content);
        }
        Transport.send(mail);
    }

    public static class UsernamePasswordAuthenticator extends Authenticator {
        String userName = null;
        String password = null;

        public UsernamePasswordAuthenticator(String username, String password) {
            this.userName = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }

    public static class MailConnectionInfo {
        private String from;
        private String host;
        private int port;
        private String username;
        private String password;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Properties getProperties() {
            Properties p = new Properties();
            p.put("mail.smtp.host", host);
            p.put("mail.smtp.port", port);
            p.put("mail.smtp.auth", (username != null) ? "true" : "false");
            p.put("mail.smtp.connectiontimeout", 20000);
            p.put("mail.smtp.timeout", 30000);
            return p;
        }

        public Authenticator getAuthenticator() {
            return new UsernamePasswordAuthenticator(username, password);
        }
    }

    public static final class MailInfo {
        private String fromAddress;
        private String toAddress;
        private String[] toAddresses;
        private String[] toAddresses_cc;
        private String subject;
        private String content;
        private String[] attachFileNames;

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String[] getAttachFileNames() {
            return attachFileNames;
        }

        public void setAttachFileNames(String[] attachFileNames) {
            this.attachFileNames = attachFileNames;
        }

        public String[] getToAddresses() {
            return toAddresses;
        }

        public void setToAddresses(String[] toAddresses) {
            this.toAddresses = toAddresses;
        }

        public String[] getToAddresses_cc() {
            return toAddresses_cc;
        }

        public void setToAddresses_cc(String[] toAddresses_cc) {
            this.toAddresses_cc = toAddresses_cc;
        }
    }

}