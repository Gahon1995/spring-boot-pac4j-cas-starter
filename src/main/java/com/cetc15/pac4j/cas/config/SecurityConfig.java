package com.cetc15.pac4j.cas.config;

import com.cetc15.pac4j.cas.properties.CasProperties;
import com.cetc15.pac4j.cas.web.SecurityInterceptor;
import org.pac4j.core.config.Config;
import org.pac4j.core.http.adapter.JEEHttpActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gahon
 */
@Configuration

public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private Config config;

    @Autowired
    private CasProperties casProperties;


    /**
     * 添加Interceptor， 可以添加自定义Interceptor，达到增加验证方式的目的
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(buildInterceptor(casProperties.getClientName()))
                .addPathPatterns(casProperties.getIncludePath())
                .excludePathPatterns(casProperties.getExcludePath());
    }

    private SecurityInterceptor buildInterceptor(final String client) {
        return new SecurityInterceptor(config, client, JEEHttpActionAdapter.INSTANCE);
    }
}
