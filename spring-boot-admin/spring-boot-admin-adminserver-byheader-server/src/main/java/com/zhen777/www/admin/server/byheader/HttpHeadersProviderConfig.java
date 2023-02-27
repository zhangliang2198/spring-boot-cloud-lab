package com.zhen777.www.admin.server.byheader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;

@Component
public class HttpHeadersProviderConfig implements HttpHeadersProvider {
    @Value("${server.admin.token}")
    private String token;

    @Value("${server.admin.token-name}")
    private String tokenName;

    @Override
    public HttpHeaders getHeaders(Instance instance) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(tokenName, token);
        return httpHeaders;
    }
}
