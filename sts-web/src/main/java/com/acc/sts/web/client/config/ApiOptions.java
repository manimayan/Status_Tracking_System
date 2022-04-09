package com.acc.sts.web.client.config;

import java.net.URI;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.acc.sts.resource.BaseResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ApiOptions {
    private ApiType apiType;

    private BaseResource baseResource;

    public ApiOptions(ApiType apiType) {
        this.apiType = apiType;
    }

    private URI baseUri(ApiType apiType) {
        return apiType.getUri();
    }

    protected abstract RestTemplate getRestTemplate();

    public String linkTo(Link link) {
        return baseUri(apiType) + link.getHref();
    }

    public String linkTo(String resource) {
        URI baseUri = baseUri(apiType);

        if (baseResource == null) {
            ResponseEntity<BaseResource> response = getRestTemplate().getForEntity(baseUri, BaseResource.class);
            baseResource = response.getBody();
        }

        String link = baseUri + baseResource.getLink(resource).getHref();
        log.info("Calling: {}", link);
        return link;
    }
}
