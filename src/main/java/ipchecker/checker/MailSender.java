package ipchecker.checker;

import ipchecker.config.MailClientConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSender {
    private static final String SUBJECT = "Your IP address has been changed!";

    private final JavaMailSender mailSender;
    private final MailClientConfiguration configuration;

    public boolean sendMail(String ip) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(configuration.getAddressee());
            helper.setSubject(SUBJECT);
            helper.setText("<HTML><BODY>Your new IP address: <A href='http://" + ip +  ":9002'" + ip + "</A></BODY></HTML>");
            mailSender.send(mail);
            log.info("Mail sent.");
        } catch (Exception e) {
            log.warn("Error sending the mail: {}", e);
            return false;
        }
        return true;
    }
}
