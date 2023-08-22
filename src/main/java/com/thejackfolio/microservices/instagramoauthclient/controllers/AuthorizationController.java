package com.thejackfolio.microservices.instagramoauthclient.controllers;

import com.thejackfolio.microservices.instagramoauthclient.clients.TheJackFolioDBClient;
import com.thejackfolio.microservices.instagramoauthclient.exceptions.AccessDeniedException;
import com.thejackfolio.microservices.instagramoauthclient.models.Instagram_Token;
import com.thejackfolio.microservices.instagramoauthclient.services.OAuthClientService;
import com.thejackfolio.microservices.instagramoauthclient.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Oauth Instagram", description = "Instagram Oauth APIs")
@Controller
@RequestMapping("/instagram")
public class AuthorizationController {

    @Autowired
    private OAuthClientService service;
    @Autowired
    private TheJackFolioDBClient dbClient;

    @Operation(
            summary = "Get Authorization Page",
            description = "Gets Instagram Authorization Page"
    )
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

    @Operation(
            summary = "Get Access Token",
            description = "Gets Access Token"
    )
    @GetMapping("/get-access-token")
    public String getAccessTokenAfterAuthorization(@RequestParam(value = "code")String code,
                                                  @RequestParam(value = "error", required = false)String error,
                                                  @RequestParam(value = "error_reason", required = false)String error_reason,
                                                  @RequestParam(value = "error_description", required = false)String error_description){
        String accessToken = null;
        try{
            accessToken = service.getAccessToken(code, error,error_reason, error_description);
        } catch (AccessDeniedException exception){
            String accessDeniedURl = service.constructAccessDeniedURL();
            return "redirect:" + accessDeniedURl;
        }
        String getPostsNewTokenUrl = service.constructGetPostNewTokenURL(accessToken);
        return "redirect:" + getPostsNewTokenUrl;
    }
}
