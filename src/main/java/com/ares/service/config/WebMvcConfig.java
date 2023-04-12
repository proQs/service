///**
// * description: TChainBase
// * <p>
// * author: saberqu
// * date: 2022/3/31 下午4:02
// * copyright Copyright@2022
// **/
//
//package com.ares.service.config;
//
//import com.tencent.tusi.tchainbase.node.base.annotation.CurrentAccountMethodArgumentResolver;
//import com.tencent.tusi.tchainbase.node.config.properties.ConstantProperties;
//import com.tencent.tusi.tchainbase.node.config.security.filter.AccountFilter;
//import com.tencent.tusi.tchainbase.node.config.security.filter.AppIntegrationFilter;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
///**
// * web configuration.
// *
// * @author saber
// */
//@Data
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Value("${server.port}")
//    private int port;
//
//    @Autowired
//    private AppIntegrationFilter appIntegrationFilter;
//    @Autowired
//    private ConstantProperties constants;
//
//    @Bean
//    public AccountFilter setAccountFilter() {
//        return new AccountFilter();
//    }
//
//    /**
//     * 注册拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(appIntegrationFilter).addPathPatterns("/api/**");// 自定义拦截的url路径
//        registry.addInterceptor(setAccountFilter()).addPathPatterns("/**")
//                .excludePathPatterns(constants.getPermitUrlArray());
//    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(currentAccountMethodArgumentResolver());
//    }
//
//    @Bean
//    public CurrentAccountMethodArgumentResolver currentAccountMethodArgumentResolver() {
//        return new CurrentAccountMethodArgumentResolver();
//    }
//}
