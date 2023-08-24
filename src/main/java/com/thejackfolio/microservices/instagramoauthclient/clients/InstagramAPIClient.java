package com.thejackfolio.microservices.instagramoauthclient.clients;


import com.thejackfolio.microservices.instagramoauthclient.models.AccessTokenRequest;
import com.thejackfolio.microservices.instagramoauthclient.models.AccessTokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INSTAGRAM-API-SERVICE", url = "https://api.instagram.com")
public interface InstagramAPIClient {

    @PostMapping(path = "/oauth/access_token", consumes = "application/x-www-form-urlencoded")
    public AccessTokenResponse getAccessToken(@RequestBody AccessTokenRequest request);
}
