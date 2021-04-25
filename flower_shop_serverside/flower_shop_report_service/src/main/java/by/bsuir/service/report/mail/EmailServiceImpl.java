package by.bsuir.service.report.mail;

import by.bsuir.email.service.core.EmailSenderService;
import by.bsuir.service.report.core.PdfExporter;
import by.bsuir.service.report.dto.Report;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    //@Value("${spring.mail.username}")
    private final String sender = "warehouse_logistic_worker@inbox.ru";
    // @Value("${spring.mail.host}")
    private final String smtpHost = "smtp.mail.ru";
    // @Value("${spring.mail.port}")
    private final int smtpPort = 587;

    private final PdfExporter pdfExporter;
    private final EmailSenderService emailSenderService;


    @Override
    public void sendConfirmAccountEmail(final String recipient,
                                        final String confirmationToken) {
        final SimpleMailMessage mailMessage = getConfirmAccountMailMessage(recipient, confirmationToken);
        this.emailSenderService.send(mailMessage);
    }

    private SimpleMailMessage getConfirmAccountMailMessage(final String recipient,
                                                           final String confirmationToken) {
        final String subject = "Завершение регистрации!";

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(sender);
        mailMessage.setText("Для подтверждения аккаунта, перейдите по ссылке : "
                + "http://localhost:8080/user/confirm-account?token=" + confirmationToken);

        return mailMessage;
    }


    @Override
    public void sendReportOnMail(final String recipient,
                                 final Report report) {
        final MimeMessage mimeMessage = this.getMimeMessage(recipient, report);
        this.emailSenderService.send(mimeMessage);
    }

    private MimeMessage getMimeMessage(final String recipient,
                                       final Report report) {

        final String subject = report.getSubject();
        final String content = report.getContent();
        final String fileName = report.getFileName() + "_" + report.getDateOfCreation();
        final String contentType = report.getContentType();
        final String fileSuffix = report.getFileSuffix();

        final Properties properties = getPortAndHostProperties();
        final Session session = Session.getDefaultInstance(properties, null);

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = getTextBodyPart(content);

            //now write the PDF content to the output stream
            //writePdf(outputStream);
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            pdfExporter.export(report, outputStream);
            final byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            MimeBodyPart pdfBodyPart = getFileBodyPart(bytes, fileName, contentType, fileSuffix);

            //construct the mime multi part
            MimeMultipart mimeMultipart = getMimeMultiPart(textBodyPart, pdfBodyPart);

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
            //
            return mimeMessage;
        } catch (Exception ignore) {
            ignore.printStackTrace();
            throw new EmailServiceException("Can`t send email");
        }
    }

    private MimeMultipart getMimeMultiPart(final MimeBodyPart textBodyPart,
                                           final MimeBodyPart mimeBodyPart) throws MessagingException {
        final MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(textBodyPart);
        mimeMultipart.addBodyPart(mimeBodyPart);
        return mimeMultipart;
    }


    private MimeBodyPart getFileBodyPart(final byte[] bytes,
                                         final String fileName,
                                         final String contentType,
                                         final String fileSuffix) throws MessagingException {
        final DataSource dataSource = new ByteArrayDataSource(bytes, contentType);
        final MimeBodyPart pdfBodyPart = new MimeBodyPart();
        pdfBodyPart.setDataHandler(new DataHandler(dataSource));
        pdfBodyPart.setFileName(fileName + fileSuffix);
        return pdfBodyPart;
    }


    private MimeBodyPart getTextBodyPart(final String content) throws MessagingException {
        final MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(content);
        return textBodyPart;
    }


    private Properties getPortAndHostProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtps.auth", "true");

        return properties;
    }
}
