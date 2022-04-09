package com.acc.sts.web.client.config;

import static com.acc.sts.web.util.JsonContentReader.readJson;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import com.acc.sts.model.ApiClientInfo;
import com.acc.sts.model.ApiInfo;

public class ApiConfiguration {

    private ApiInfo coreApiInfo;

    private String envPropertiesFile;

    private ApiClientInfo apiClientInfo;

    public class Core implements ApiType {
        @Override
        public String getToken() {
            return coreApiInfo.getAccessToken();
        }

        @Override
        public URI getUri() {
            return uriForString(coreApiInfo.getUri());
        }
    }

    public ApiConfiguration(String envPropertiesFile) throws IOException {
        this.envPropertiesFile = envPropertiesFile;
        readProperties();
        this.coreApiInfo = getApiPropertyForApiKey("sts.core.api");
    }

    public ApiInfo getApiPropertyForApiKey(String apiKey) {
        List<ApiInfo> apiProperties = apiClientInfo.getApiProperties();
        for (ApiInfo apiProperty : apiProperties) {
            if (apiProperty.getKey().equals(apiKey)) {
                return apiProperty;
            }
        }
        return null;
    }

    private void readProperties() throws IOException {
        apiClientInfo = readJson(envPropertiesFile, ApiClientInfo.class);
    }

    private URI uriForString(String uriString) {
        if (uriString == null)
            return null;
        return URI.create(uriString);
    }
}
