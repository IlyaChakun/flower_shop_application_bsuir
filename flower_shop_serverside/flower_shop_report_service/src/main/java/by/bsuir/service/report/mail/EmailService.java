package by.bsuir.service.report.mail;


import by.bsuir.service.report.dto.Report;

public interface EmailService {

    void sendConfirmAccountEmail(final String recipient, final String confirmationToken);

    void sendReportOnMail(final String recipient, final Report report);

}
