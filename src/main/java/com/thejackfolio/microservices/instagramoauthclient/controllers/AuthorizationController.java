package com.thejackfolio.microservices.instagramoauthclient.controllers;

import com.thejackfolio.microservices.instagramoauthclient.clients.TheJackFolioDBClient;
import com.thejackfolio.microservices.instagramoauthclient.exceptions.AccessDeniedException;
import com.thejackfolio.microservices.instagramoauthclient.models.Instagram_Token;
import com.thejackfolio.microservices.instagramoauthclient.services.OAuthClientService;
import com.thejackfolio.microservices.instagramoauthclient.utilities.StringConstants;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "Oauth Instagram", description = "Instagram Oauth APIs")
@Controller
@RequestMapping("/instagram")
public class AuthorizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);
    private boolean isRetryEnabled = false;

    @Autowired
    private OAuthClientService service;
    @Autowired
    private TheJackFolioDBClient dbClient;

    @Operation(
            summary = "Get Authorization Page",
            description = "Gets Instagram Authorization Page"
    )
    @GetMapping("/authorize")
    @Retry(name = "authorize-retry", fallbackMethod = "authorizeRetry")
    public String redirectToInstagramAuth() {
        if(isRetryEnabled){
            LOGGER.info(StringConstants.RETRY_MESSAGE);
        }
        if(!isRetryEnabled){
            isRetryEnabled = true;
        }
        ResponseEntity<Instagram_Token> response = dbClient.getInstagramToken();
        Instagram_Token token = response.getBody();
        if(token.getToken() == null || (token != null && token.getMessage().equals(StringConstants.DATABASE_ERROR)) ||
                (token != null && token.getMessage().equals(StringConstants.MAPPING_ERROR))){
            String authorizationUrl = service.constructAuthorizationUrl();
            isRetryEnabled = false;
            return "redirect:" + authorizationUrl;
        }
        else{
            String getPostsUrl = service.constructGetPostsUrl();
            isRetryEnabled = false;
            return "redirect:" + getPostsUrl;
        }
    }

    public String authorizeRetry(Exception exception){
        isRetryEnabled = false;
        LOGGER.info(StringConstants.FALLBACK_MESSAGE, exception);
        return StringConstants.FALLBACK_MESSAGE;
    }

    @Operation(
            summary = "Get Access Token",
            description = "Gets Access Token"
    )
    @GetMapping("/get-access-token")
    @Retry(name = "get-access-token-retry", fallbackMethod = "getAccessTokenRetry")
    public String getAccessTokenAfterAuthorization(@RequestParam(value = "code")String code,
                                                  @RequestParam(value = "error", required = false)String error,
                                                  @RequestParam(value = "error_reason", required = false)String error_reason,
                                                  @RequestParam(value = "error_description", required = false)String error_description){
        String accessToken = null;
        try{
            if(isRetryEnabled){
                LOGGER.info(StringConstants.RETRY_MESSAGE);
            }
            if(!isRetryEnabled){
                isRetryEnabled = true;
            }
            accessToken = service.getAccessToken(code, error,error_reason, error_description);
        } catch (AccessDeniedException exception){
            String accessDeniedURl = service.constructAccessDeniedURL();
            isRetryEnabled = false;
            return "redirect:" + accessDeniedURl;
        }
        String getPostsNewTokenUrl = service.constructGetPostNewTokenURL(accessToken);
        isRetryEnabled = false;
        return "redirect:" + getPostsNewTokenUrl;
    }

    public String getAccessTokenRetry(String code,String error,String error_reason,String error_description,Exception exception){
        isRetryEnabled = false;
        LOGGER.info(StringConstants.FALLBACK_MESSAGE, exception);
        return StringConstants.FALLBACK_MESSAGE;
    }
}
