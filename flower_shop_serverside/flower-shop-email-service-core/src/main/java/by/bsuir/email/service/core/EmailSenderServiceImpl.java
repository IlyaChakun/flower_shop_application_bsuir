package by.bsuir.email.service.core;

import by.bsuir.email.exception.EmailServiceException;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void send(final SimpleMailMessage mailMessage) {
        if (Objects.nonNull(mailMessage)) {
            try {
                this.javaMailSender.send(mailMessage);
            } catch (Exception ex) {
                throw new EmailServiceException("Can`t send email", ex);
            }
        }
    }

    @Override
    @Async
    public void send(final MimeMessage mailMessage) {
        if (Objects.nonNull(mailMessage)) {
            try {
                this.javaMailSender.send(mailMessage);
            } catch (Exception ex) {
                throw new EmailServiceException("Can`t send email", ex);
            }
        }
    }

}
