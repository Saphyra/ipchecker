package org.github.saphyra.ipchecker.checker;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class Monitoring {
    private final IpProvider ipProvider;
    private final MailSender mailSender;
    private final SuccessStore successStore;

    private String actualIp = "";

    @Scheduled(fixedDelayString = "${mail.interval}")
    public void monitorIp() {
        log.info("Checking IP address...");
        String ip = ipProvider.getIp();
        boolean ipChanged = !actualIp.equals(ip);

        if (ipChanged) {
            log.info("IP address has been changed. New IP: {}", ip);
            actualIp = ip;
            successStore.invalidate();
            mailSender.sendMails(ip);
        } else if (successStore.hasFailed()) {
            log.info("Sending mails to failed users...");
            mailSender.sendMails(ip);
        } else {
            log.info("Mail sending is not necessary.");
        }
    }
}
