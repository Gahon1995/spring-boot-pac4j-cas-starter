package com.gahon.pac4j.cas.config;

import com.gahon.pac4j.cas.properties.CasProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Gahon
 * @date 2019/9/29
 */
@Configuration
@EnableConfigurationProperties(CasProperties.class)
@ComponentScan(basePackages = "com.gahon.pac4j.cas.web")
@Import({
        Pac4jConfig.class,
        SecurityConfig.class
})
@Slf4j
public class Pac4jCasConfiguration {
    public Pac4jCasConfiguration() {
        log.info("开启Pac4jCas登录验证功能");
    }
}
