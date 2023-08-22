package com.thejackfolio.microservices.instagramoauthclient.services;

import com.thejackfolio.microservices.instagramoauthclient.exceptions.AccessDeniedException;
import com.thejackfolio.microservices.instagramoauthclient.exceptions.AccessTokenException;
import com.thejackfolio.microservices.instagramoauthclient.models.AccessTokenResponse;
import com.thejackfolio.microservices.instagramoauthclient.servicehelpers.OAuthClientServiceHelper;
import com.thejackfolio.microservices.instagramoauthclient.utilities.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthClientService {

    @Autowired
    private OAuthClientServiceHelper helper;

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthClientService.class);

    public String getAccessToken(String code,
                                   String error,
                                   String error_reason,
                                   String error_description) throws AccessDeniedException {
        if(error != null && error.equals(StringConstants.ACCESS_DENIED_MESSAGE)){
            LOGGER.info(error_description);
            throw new AccessDeniedException(error_description);
        }
        AccessTokenResponse response = null;
        try{
            response = helper.getAccessToken(code);
        } catch (AccessTokenException exception){
            LOGGER.info(exception.getMessage());
        }
        return response.getAccess_token();
    }

    public String constructAuthorizationUrl(){
        return helper.constructAuthorizationUrl();
    }

    public String constructGetPostsUrl(){
        return helper.constructGetPostsUrl();
    }

    public String constructGetPostNewTokenURL(String accessToken){
        return helper.constructGetPostNewTokenURL(accessToken);
    }

    public String constructAccessDeniedURL(){
        return helper.constructAccessDeniedURL();
    }
}
