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

    public void sendMail(String ip){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(configuration.getAddressee());
        message.setSubject(SUBJECT);
        message.setText("Your new IP address: " + ip);
        mailSender.send(message);
        log.info("Mail sent.");
    }
}
