package org.github.saphyra.ipchecker.checker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class Monitoring {
    private final IpQueryService ipQueryService;
    private final MailSender mailSender;

    private String actualIp = "";
    private boolean isSendSuccess = false;

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void monitorIp() {
        log.info("Checking IP address...");
        String ip = ipQueryService.getIp();
        if(!actualIp.equals(ip) || !isSendSuccess){
            log.info("IP address has been changed. Sending mail.");
            actualIp = ip;
            isSendSuccess = false;
            isSendSuccess = mailSender.sendMail(ip);
        }else {
            log.info("IP address has not changed.");
        }
    }
}
