package by.bsuir.email.service.core;


import by.bsuir.email.exception.EmailServiceException;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

/**
 * Base core service which provides sending mails of two types.
 * Throwing runtime exception if something goes wrong
 */
public interface EmailSenderService {

    void send(final SimpleMailMessage mailMessage) throws EmailServiceException;

    void send(final MimeMessage mailMessage) throws EmailServiceException;

}
