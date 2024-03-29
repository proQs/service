package com.ares.service.config.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * constants in yml and static constants
 *
 * @author saber
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = ConstantProperties.CONSTANT_PREFIX)
public class ConstantProperties {
    // constant
    public static final String CONSTANT_PREFIX = "constant";

    private Integer httpTimeOut = 5000;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("Init constant properties,httpTimeOut: [{}]", httpTimeOut);
    }
}