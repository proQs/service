//package com.ares.service.config;
//
//import com.tencent.tusi.tchainbase.node.config.properties.ConstantProperties;
//import com.tencent.tusi.tchainbase.node.config.security.*;
//import com.tencent.tusi.tchainbase.node.config.security.customizeAuth.TokenAuthenticationProvider;
//import com.tencent.tusi.tchainbase.node.config.security.filter.TokenAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
///**
// * security config.
// *
// * @author saber
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    @Lazy
//    private AccountDetailsService userDetailService;
//    @Autowired
//    @Lazy
//    @Qualifier(value = "loginSuccessHandler")
//    private AuthenticationSuccessHandler loginSuccessHandler;
//    private final LoginFailHandler loginfailHandler;
//    private final JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint;
//    private final JsonAccessDeniedHandler jsonAccessDeniedHandler;
//    private final JsonLogoutSuccessHandler jsonLogoutSuccessHandler;
//    private final ConstantProperties constants;
//
//    public SecurityConfig(@Qualifier(value = "loginFailHandler") LoginFailHandler loginfailHandler, JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint, JsonAccessDeniedHandler jsonAccessDeniedHandler, JsonLogoutSuccessHandler jsonLogoutSuccessHandler, ConstantProperties constants) {
//        this.loginfailHandler = loginfailHandler;
//        this.jsonAuthenticationEntryPoint = jsonAuthenticationEntryPoint;
//        this.jsonAccessDeniedHandler = jsonAccessDeniedHandler;
//        this.jsonLogoutSuccessHandler = jsonLogoutSuccessHandler;
//        this.constants = constants;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 无权访问 JSON 格式的数据
//        http.exceptionHandling().accessDeniedHandler(jsonAccessDeniedHandler);
//
//        // login page
//        http.formLogin().loginPage("/login")
//                .loginProcessingUrl("/account/login") // login request uri
//                .usernameParameter("account").passwordParameter("accountPwd").permitAll()
//                .successHandler(loginSuccessHandler) // if login success
//                .failureHandler(loginfailHandler) // if login fail
//                .and().authorizeRequests()
//                .antMatchers(constants.getPermitUrlArray())
//                .permitAll()
//                .anyRequest().authenticated()
//                .and().csrf()
//                .disable() // close csrf
//                .addFilterBefore(new TokenAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
//                .httpBasic().authenticationEntryPoint(jsonAuthenticationEntryPoint).and().logout()
//                .logoutUrl("/account/logout")
//                .logoutSuccessHandler(jsonLogoutSuccessHandler)
//                .permitAll();
//    }
//
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//        if (!constants.getIsUseSecurity()) {
//            web.ignoring().antMatchers("/**");
//        }
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(userAuthenticationProvider());
//        auth.authenticationProvider(tokenAuthenticationProvider());
//    }
//
//    @Bean("bCryptPasswordEncoder")
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public AuthenticationProvider tokenAuthenticationProvider() {
//        return new TokenAuthenticationProvider();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider userAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailService);
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//}
