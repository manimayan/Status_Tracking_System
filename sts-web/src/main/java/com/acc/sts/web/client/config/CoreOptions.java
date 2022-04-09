package com.acc.sts.web.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("CoreOptions")
public class CoreOptions extends ApiOptions {

    @Autowired(required = false)
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate;

    @Autowired
    public CoreOptions(ApiConfiguration apiConfiguration) {
        super(apiConfiguration.new Core());
    }

    @Override
    protected RestTemplate getRestTemplate() {
        return this.coreRestTemplate;
    }
}
