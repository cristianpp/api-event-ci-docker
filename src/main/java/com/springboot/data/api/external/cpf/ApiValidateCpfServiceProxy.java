package com.springboot.data.api.external.cpf;

import com.springboot.data.configuration.properties.ApiConfigurationProperties;
import org.springframework.web.client.ResourceAccessException;

public class ApiValidateCpfServiceProxy implements ApiValidateCpfService {

    private ApiValidateCpfService apiValidateCpfService;

    private Integer maxAttempts;

    public ApiValidateCpfServiceProxy(ApiValidateCpfService apiValidateCpfService) {
        this.apiValidateCpfService = apiValidateCpfService;

    }

    @Override
    public boolean validateCpf(int cpf) {
        int attempts = 0;
        ResourceAccessException lastError = null;
        maxAttempts = Integer.valueOf(ApiConfigurationProperties.getProperty("api.cpf.number.of.attempts"));

        while (attempts < maxAttempts) {
            try {
                return apiValidateCpfService.validateCpf(cpf);
            } catch (ResourceAccessException ex) {
                attempts++;
                lastError = ex;
            }
        }
        throw lastError;
    }
}
