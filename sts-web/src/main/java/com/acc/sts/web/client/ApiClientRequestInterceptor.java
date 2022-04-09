package com.acc.sts.web.client;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import com.acc.sts.model.ApiInfo;
import com.acc.sts.web.client.config.ApiConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiClientRequestInterceptor implements ClientHttpRequestInterceptor {
    private String apiKey;

    private ApiConfiguration apiConfiguration;

    @Autowired
    public ApiClientRequestInterceptor(String apiIdentifier, ApiConfiguration apiConfiguration) {
        this.apiKey = apiIdentifier;
        this.apiConfiguration = apiConfiguration;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        ApiInfo apiInfo = apiConfiguration.getApiPropertyForApiKey(apiKey);
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().set("x-cat-authorization", apiInfo.getAccessToken());
        request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        getBasicAuthHeaders(requestWrapper, apiInfo);
        log.info(apiKey + " - " + apiInfo.getUri() + " - " + request.getURI().getPath());
        return execution.execute(requestWrapper, body);
    }

    protected void getBasicAuthHeaders(HttpRequest request, ApiInfo apiInfo) {
        // Intentionally left blank. Inheriting classess may implement this method if required.
    }

}
