package com.ares.service.config;

import com.ares.service.config.properties.ConstantProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * config about bean.
 * @author saber
 */
@Slf4j
@Configuration
public class BeanConfig {

    @Autowired
    private ConstantProperties constantProperties;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    /**
     * resttemplate for generic http request.
     */
    @Bean(name = "genericRestTemplate")
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = getHttpFactoryForDeploy();
        // ms
        factory.setReadTimeout(constantProperties.getHttpTimeOut());
        // ms
        factory.setConnectTimeout(constantProperties.getHttpTimeOut());
        return new RestTemplate(factory);
    }

    /**
     * factory for deploy.
     */
    @Bean()
    @Scope("prototype")
    public SimpleClientHttpRequestFactory getHttpFactoryForDeploy() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        return factory;
    }
}
