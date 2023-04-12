package com.ares.service.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author saber
 */
@Slf4j
@Configuration
public class SchedulerPoolConfig implements SchedulingConfigurer {
    @Autowired
    @Qualifier("mgrTaskScheduler")
    private ThreadPoolTaskScheduler taskScheduler;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        log.info("config scheduler parallel task");
        scheduledTaskRegistrar.setScheduler(taskScheduler);
    }
}

