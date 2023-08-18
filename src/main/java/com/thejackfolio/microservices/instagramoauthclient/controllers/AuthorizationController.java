package com.thejackfolio.microservices.instagramoauthclient.controllers;

import com.thejackfolio.microservices.instagramoauthclient.clients.TheJackFolioDBClient;
import com.thejackfolio.microservices.instagramoauthclient.models.Instagram_Token;
import com.thejackfolio.microservices.instagramoauthclient.services.OAuthClientService;
import com.thejackfolio.microservices.instagramoauthclient.utilities.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth")
public class AuthorizationController {

    @Autowired
    private OAuthClientService service;
    @Autowired
    private TheJackFolioDBClient dbClient;

    @GetMapping("/authorize")
    public String redirectToInstagramAuth() {
        ResponseEntity<Instagram_Token> response = dbClient.getInstagramToken();
        Instagram_Token token = response.getBody();
        if(token.getToken() == null || (token != null && token.getMessage().equals(StringConstants.DATABASE_ERROR)) ||
                (token != null && token.getMessage().equals(StringConstants.MAPPING_ERROR))){
            String authorizationUrl = service.constructAuthorizationUrl();
            return "redirect:" + authorizationUrl;
        }
        else{
            String getPostsUrl = service.constructGetPostsUrl();
            return "redirect:" + getPostsUrl;
        }
    }

    @GetMapping("/get-access-token")
    public String getAccessTokenAfterAuthorization(@RequestParam(value = "code")String code,
                                                  @RequestParam(value = "error", required = false)String error,
                                                  @RequestParam(value = "error_reason", required = false)String error_reason,
                                                  @RequestParam(value = "error_description", required = false)String error_description){
        String accessToken = service.getAccessToken(code, error,error_reason, error_description);
        String getPostsNewTokenUrl = service.constructGetPostNewTokenURL(accessToken);
        return "redirect:" + getPostsNewTokenUrl;
    }
}
