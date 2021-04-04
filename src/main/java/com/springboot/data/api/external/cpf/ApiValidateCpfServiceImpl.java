package com.springboot.data.api.external.cpf;


import com.springboot.data.configuration.properties.ApiConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiValidateCpfServiceImpl implements ApiValidateCpfService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean validateCpf(int cpf) {
        String url = ApiConfigurationProperties.getProperty("url.validate.cpf");
        Status valid = restTemplate.getForObject(url, Status.class, cpf);
        return valid.isValid();
    }

}
