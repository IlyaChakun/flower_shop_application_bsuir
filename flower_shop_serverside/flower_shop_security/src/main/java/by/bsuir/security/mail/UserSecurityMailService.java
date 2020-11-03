package by.bsuir.security.mail;

public interface UserSecurityMailService {

    void sendConfirmAccountEmail(final String recipient, final String confirmationToken);

}
