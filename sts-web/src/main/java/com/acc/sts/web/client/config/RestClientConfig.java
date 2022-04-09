package com.acc.sts.web.client.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.acc.sts.web.client.ApiClientRequestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@PropertySource(value = "classpath:properties/application.properties")
public class RestClientConfig {

    @Value("${sts.environment}")
    private String apiEnvironment = "LOCAL";

    @Bean
    public ApiConfiguration getApiConfiguration() {
        ApiConfiguration apiConfig = null;
        try {
            apiEnvironment = apiEnvironment.toUpperCase();
            StringBuilder filePath = new StringBuilder("/properties/");
            filePath.append(apiEnvironment);
            filePath.append(".json");
            apiConfig = new ApiConfiguration(filePath.toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return apiConfig;
    }

    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @Bean
    public List<ClientHttpRequestInterceptor> getCoreInterceptors(String apiKey) {
        List<ClientHttpRequestInterceptor> interceptor = new ArrayList<>();
        interceptor.add(new ApiClientRequestInterceptor(apiKey, getApiConfiguration()));
        return interceptor;
    }

    @Bean(name = "coreRestTemplate")
    public RestTemplate getCoreRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setMessageConverters(getMessageConverters());
        String apiKey = "sts.core.api";
        restTemplate.setInterceptors(getCoreInterceptors(apiKey));
        return restTemplate;
    }

    @Bean
    public List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }
}
