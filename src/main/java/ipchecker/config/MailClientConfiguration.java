package ipchecker.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MailClientConfiguration {

    @Value("${mail.addressee}")
    private String addressee;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;
}
