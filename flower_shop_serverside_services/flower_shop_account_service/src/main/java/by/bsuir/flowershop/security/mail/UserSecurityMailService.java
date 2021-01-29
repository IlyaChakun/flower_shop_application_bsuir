package by.bsuir.flowershop.security.mail;

public interface UserSecurityMailService {

    void sendConfirmAccountEmail(final String recipient, final String confirmationToken);

}
