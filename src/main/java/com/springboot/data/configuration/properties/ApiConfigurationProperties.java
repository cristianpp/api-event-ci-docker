package com.springboot.data.configuration.properties;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = {"classpath:api.properties"}, ignoreResourceNotFound = false)
public class ApiConfigurationProperties implements EnvironmentAware {

    private static Environment env;

    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    @Override
    public void setEnvironment(Environment environment) {
      ApiConfigurationProperties.env = environment;
    }
}
