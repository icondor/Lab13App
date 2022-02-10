import java.time.LocalTime;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class SendMail {

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    private String fileName;
    private String toEmail;


    public void sendEmail() {
        System.out.println("start email");

        final String username = "fasttrackit.intro@gmail.com";
        final String password = "fasttrackit.intro1";
//        final String fileName="medii.txt";
//        final String toEmail="ionel.condor@gmail.com";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("salut, uite un mail " + LocalTime.now());


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Attached your file"  + System.getProperty("line.separator"));
            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            Transport.send(message);


            System.out.println("stop email");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
