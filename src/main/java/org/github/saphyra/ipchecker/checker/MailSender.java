package org.github.saphyra.ipchecker.checker;

import javax.mail.internet.MimeMessage;

import org.github.saphyra.ipchecker.config.AddresseesConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
class MailSender {
    private static final String SUBJECT = "Your IP address has been changed!";

    private final JavaMailSender mailSender;
    private final AddresseesConfiguration addresseesConfiguration;
    private final SuccessStore successStore;

    void sendMails(String newIp) {
        addresseesConfiguration.getAddressees().stream()
            .filter(successStore::needToSendMail)
            .forEach(addressee -> sendMail(addressee, newIp));
    }

    private void sendMail(String addressee, String ip) {
        boolean success = true;
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
            helper.setTo(addressee);
            helper.setSubject(SUBJECT);
            helper.setText("Your new IP address: http://" + ip + ":9002");
            mailSender.send(mail);
            log.info("Mail sent.");
        } catch (Exception e) {
            log.warn("Error sending the mail: {}", e);
            success = false;
        } finally {
            successStore.updateSentStatus(addressee, success);
        }
    }
}
