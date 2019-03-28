package org.github.saphyra.ipchecker.config;

import org.github.saphyra.ipchecker.checker.IpProvider;
import org.github.saphyra.ipchecker.dev.NoOperationMailSender;
import org.github.saphyra.ipchecker.dev.RandomIpProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@Profile("!prod")
public class DevBeanConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        return new NoOperationMailSender();
    }

    @Bean
    public IpProvider ipProvider(){
        return new RandomIpProvider();
    }
}
