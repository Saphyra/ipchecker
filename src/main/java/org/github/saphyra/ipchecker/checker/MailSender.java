package org.github.saphyra.ipchecker.checker;

import org.github.saphyra.ipchecker.config.MailClientConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
class MailSender {
    private static final String SUBJECT = "Your IP address has been changed!";

    private final JavaMailSender mailSender;
    private final MailClientConfiguration configuration;

    boolean sendMail(String ip) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
            helper.setTo(configuration.getAddressee());
            helper.setSubject(SUBJECT);
            helper.setText("Your new IP address: http://" + ip +  ":9002");
            mailSender.send(mail);
            log.info("Mail sent.");
        } catch (Exception e) {
            log.warn("Error sending the mail: {}", e);
            return false;
        }
        return true;
    }
}
