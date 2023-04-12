package com.ares.service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * scheduler properties
 * @author saber
 */
@Data
@Component
@ConfigurationProperties(prefix = SchedulerProperties.SCHEDULER_PREFIX)
public class SchedulerProperties {
    public static final String SCHEDULER_PREFIX = "scheduler";

    private Integer poolSize = 5;
    private String threadNamePrefix = "node-task-";
    private Integer awaitTerminationSeconds = 60;
    private Boolean waitForTasksToCompleteOnShutdown = true;

}
