package com.ares.service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * executor of node-mgr async
 *
 * @author saber
 */
@Data
@Component
@ConfigurationProperties(prefix = ExecutorProperties.EXECUTOR_PREFIX)
public class ExecutorProperties {
    public static final String EXECUTOR_PREFIX = "executor";

    private Integer corePoolSize = 3;
    private Integer maxPoolSize = 5;
    private Integer queueSize = 50;
    private String threadNamePrefix = "node-mgr-async-";

}
