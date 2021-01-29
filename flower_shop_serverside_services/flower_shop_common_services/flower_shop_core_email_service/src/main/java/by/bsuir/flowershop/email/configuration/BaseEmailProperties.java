package by.bsuir.flowershop.email.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public final class BaseEmailProperties {

    @Value("${spring.mail.username}")
    public String emailSender;// = "warehouse_logistic_worker@inbox.ru";
    @Value("${spring.mail.host}")
    public String smtpHost;// = "smtp.mail.ru";
    @Value("${spring.mail.port}")
    public int smtpPort;// = 587;

}
