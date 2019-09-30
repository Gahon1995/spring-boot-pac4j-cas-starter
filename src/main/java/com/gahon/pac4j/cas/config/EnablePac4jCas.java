package com.gahon.pac4j.cas.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Gahon
 * @date 2019/9/29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(Pac4jCasConfiguration.class)
public @interface EnablePac4jCas {
}
