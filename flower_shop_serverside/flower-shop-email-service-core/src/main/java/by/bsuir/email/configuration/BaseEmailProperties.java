package by.bsuir.email.configuration;

public final class BaseEmailProperties {

    private BaseEmailProperties() {

    }

    //@Value("${spring.mail.username}")
    public static final String EMAIL_SENDER = "cargo_transportation_worker@inbox.ru";
    // @Value("${spring.mail.host}")
    public static final String SMTP_HOST = "smtp.mail.ru";
    // @Value("${spring.mail.port}")
    public static final int SMTP_PORT = 587;


}
