package by.bsuir.flowershop.security.mail;


import by.bsuir.flowershop.email.configuration.BaseEmailProperties;
import by.bsuir.flowershop.email.exception.EmailServiceException;
import by.bsuir.flowershop.email.service.core.EmailSenderService;
import by.bsuir.flowershop.security.core.RestAuthenticationEntryPoint;
import by.bsuir.flowershop.security.exception.InvalidEmailException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSecurityMailServiceImpl implements UserSecurityMailService {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);
    private final EmailSenderService emailSenderService;
    private final BaseEmailProperties baseEmailProperties;

    @Override
    public void sendConfirmAccountEmail(final String recipient,
                                        final String confirmationToken) {
        final SimpleMailMessage mailMessage = getConfirmAccountMailMessage(recipient, confirmationToken);

        try {
            this.emailSenderService.send(mailMessage);
            logger.info("confirm acc email sent successfully");
        } catch (EmailServiceException ex) {
            logger.error("Вы указали не верный Email! Такого не существует; " + ex.getMessage());
            throw new InvalidEmailException("Вы указали не верный Email! Такого не существует");
        }

    }

    private SimpleMailMessage getConfirmAccountMailMessage(final String recipient,
                                                           final String confirmationToken) {

        logger.info("sending to : " + recipient + " and token= " + confirmationToken);

        logger.info("send from: {}", baseEmailProperties.getEmailSender());
        final String subject = "Завершение регистрации!";

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(baseEmailProperties.getEmailSender());
        mailMessage.setText("Для подтверждения аккаунта, перейдите по ссылке : "
                + "http://localhost:8080/auth/user/confirm-account?token=" + confirmationToken);

        return mailMessage;
    }
}
