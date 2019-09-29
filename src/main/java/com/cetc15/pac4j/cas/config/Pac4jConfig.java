package com.cetc15.pac4j.cas.config;

import com.cetc15.pac4j.cas.filter.CustomContextThreadLocalFilter;
import com.cetc15.pac4j.cas.properties.CasProperties;
import com.cetc15.pac4j.cas.store.CasSessionStore;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.Collections;


/**
 * @author Gahon
 */
@Configuration
@EnableConfigurationProperties(CasProperties.class)
public class Pac4jConfig {

    private final CasProperties casProperties;

    Pac4jConfig(CasProperties casProperties) {
        this.casProperties = casProperties;
        //  参数配置初始化
        this.casProperties.init();
    }

    @Bean
    public CasConfiguration casConfiguration() {
        // CAS
        final CasConfiguration configuration = new CasConfiguration();
        //CAS server登录地址
        configuration.setLoginUrl(casProperties.getServerLoginUrl());
        //CAS 版本
//        configuration.setProtocol(CasProtocol.CAS30);
        configuration.setAcceptAnyProxy(true);
        configuration.setPrefixUrl(casProperties.getServerUrlPrefix());
        return configuration;
    }

    @Bean
    public CasClient casClient(CasConfiguration configuration) {
        final CasClient casClient = new CasClient(configuration);
        casClient.setCallbackUrl(casProperties.getProjectUrl() + "/callback?client_name=" + casProperties.getClientName());
        casClient.setName(casProperties.getClientName());
        return casClient;
    }


    /**
     * 以后可以在这里添加client， 从而达到添加其他验证方式
     * final Clients clients = new Clients("http://cas.client.com:8080/callback?client_name=casClient", casClient);
     *
     * @param casClient
     * @return
     */
    @Bean
    public Config config(CasClient casClient) {
        Config config = new Config(casClient);
        config.setSessionStore(new CasSessionStore());
        return config;
    }


    @Bean
    public FilterRegistrationBean<CustomContextThreadLocalFilter> casAssertionThreadLocalFilter() {
        final FilterRegistrationBean<CustomContextThreadLocalFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CustomContextThreadLocalFilter());
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/*"));
        return filterRegistrationBean;
    }
}
