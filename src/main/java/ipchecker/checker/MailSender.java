package ipchecker.checker;

import ipchecker.config.MailClientConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSender {
    private static final String SUBJECT = "Your IP address has been changed!";

    private final JavaMailSender mailSender;
    private final MailClientConfiguration configuration;

    public boolean sendMail(String ip) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(configuration.getAddressee());
            message.setSubject(SUBJECT);
            message.setText("Your new IP address: <A href='http://" + ip +  ":9002'" + ip + "</A>");
            mailSender.send(message);
            log.info("Mail sent.");
        } catch (Exception e) {
            log.warn("Error sending the mail: {}", e);
            return false;
        }
        return true;
    }
}
