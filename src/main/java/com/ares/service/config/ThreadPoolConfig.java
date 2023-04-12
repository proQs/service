package com.ares.service.config;

import com.ares.service.config.properties.ExecutorProperties;
import com.ares.service.config.properties.SchedulerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定时任务并行执行
 *
 * @author saber
 */
@Configuration
@EnableScheduling
@Slf4j
public class ThreadPoolConfig {

    @Autowired
    private ExecutorProperties executorProperties;
    @Autowired
    private SchedulerProperties schedulerProperties;

    /**
     * pull block and trans from chain async
     *
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    public ThreadPoolTaskExecutor mgrAsyncExecutor() {
        log.info("start mgrAsyncExecutor init..");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(executorProperties.getCorePoolSize());
        executor.setMaxPoolSize(executorProperties.getMaxPoolSize());
        executor.setQueueCapacity(executorProperties.getQueueSize());
        executor.setThreadNamePrefix(executorProperties.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // init executor
        executor.initialize();
        return executor;
    }


    /**
     * thread pool for scheduler parallel task (not async):
     * pull block, trans monitor, statistic trans, delete info, reset groupList
     *
     * @return ThreadPoolTaskScheduler
     */
    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler mgrTaskScheduler() {
        log.info("start mgrTaskScheduler init..");
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(schedulerProperties.getPoolSize());
        scheduler.setThreadNamePrefix(schedulerProperties.getThreadNamePrefix());
        scheduler.setAwaitTerminationSeconds(schedulerProperties.getAwaitTerminationSeconds());
        scheduler.setWaitForTasksToCompleteOnShutdown(
                schedulerProperties.getWaitForTasksToCompleteOnShutdown());
        return scheduler;
    }


    @Bean
    public ThreadPoolTaskScheduler deployAsyncScheduler() {
        log.info("start deployAsyncScheduler init...");
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.afterPropertiesSet();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler-async-deploy:");
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return scheduler;
    }
}
