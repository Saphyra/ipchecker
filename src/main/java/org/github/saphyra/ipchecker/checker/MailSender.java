package org.github.saphyra.ipchecker.checker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.saphyra.ipchecker.config.AddresseesConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
class MailSender {
    private static final String SUBJECT = "IP address changed";

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
            helper.setText(getMessage(ip));
            mailSender.send(mail);
            log.info("Mail sent.");
        } catch (Exception e) {
            log.warn("Error sending the mail: {}", e);
            success = false;
        } finally {
            successStore.updateSentStatus(addressee, success);
        }
    }

    private String getMessage(String ip) {
        return "IP address of the server has changed. New IP address is: " +
            ip +
            "\n" +
            "SkyXplore: http://" + ip + ":9001" +
            "\n" +
            "Bookmarks: http://" + ip + ":9002" +
            "\n" +
            "Training: http://" + ip + ":9003";
    }
}
