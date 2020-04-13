package com.deepbluebi.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 描述： 解决问题： Could not resolve placeholder 'palmUrl' in value "${palmUrl}"
 *
 * @author caojing
 * @create 2019-06-25-16:43
 */
@Configuration
public class PropertySourcePlaceholderConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        //忽略没有被解释的占位符
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

}