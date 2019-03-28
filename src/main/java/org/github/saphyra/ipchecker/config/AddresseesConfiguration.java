package org.github.saphyra.ipchecker.config;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@EnableConfigurationProperties(AddresseesConfiguration.class)
@ConfigurationProperties(prefix = "mail")
@Validated
@Data
@Slf4j
public class AddresseesConfiguration {
    @NotNull
    private List<String> addressees;
}
