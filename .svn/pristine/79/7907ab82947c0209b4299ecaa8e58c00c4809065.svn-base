/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evnit.util.sendMail;

import java.io.File;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ba
 */
public class SendMail {

    public static boolean sendMail(String host, String port, final String user, final String pass,
            String mail, String toEmail, String title, String messageContent, String reportpath) throws UnknownHostException {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.setProperty("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.socketFactory.port", "25");
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        try {
//            String toEmail = "tvba88@gmail.com;ba_tt2@yahoo.com.vn;batv.evnit@evn.com.vn;fdfs@dfafad.vn";
            String[] arr = toEmail.split(";");
            InternetAddress[] lstEmail = new InternetAddress[arr.length];
            for (int i = 0; i < arr.length; i++) {
                lstEmail[i] = InternetAddress.parse(arr[i])[0];
            }
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail));
            message.setRecipients(Message.RecipientType.TO, lstEmail);
//            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("tvb88@gmail.com.vn"));
//                    message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("tvba88@gmail.com"));
            message.setSubject(title, "UTF-8");
            if (reportpath == null) {
                if (messageContent != null && !messageContent.isEmpty()) {
//                message.setText(messageContent,"text/html;charset=UTF-8");
                    message.setContent(messageContent, "text/html;charset=UTF-8");
//                message.setDescription(messageContent);
//                message.setContent(messageContent, title);
                } else {
                    message.setText("");
                }
            } else {
                // Create the message part 
                String filename="";
                File f=new File(reportpath);
                if (f.isDirectory()) {
                    filename = f.list()[0];
                    
                }
                BodyPart messageBodyPart = new MimeBodyPart();

                // Fill the message
                messageBodyPart.setContent(messageContent, "text/html;charset=UTF-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
//                String filename = "D:\\assetSort.xls";
                DataSource source = new FileDataSource(reportpath+"\\"+filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                // Put parts in message
                message.setContent(multipart);
            }
            Transport transport = session.getTransport();
            transport.connect(user, pass);
//            Transport.send(message);
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();

            System.out.println("successful");
            return true;
        } catch (MessagingException e) {
            System.out.print(e.getMessage());
        }
        return false;
    }
}
