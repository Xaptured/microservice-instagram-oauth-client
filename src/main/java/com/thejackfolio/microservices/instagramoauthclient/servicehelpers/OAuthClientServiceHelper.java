package com.thejackfolio.microservices.instagramoauthclient.servicehelpers;

import com.thejackfolio.microservices.instagramoauthclient.clients.InstagramAPIClient;
import com.thejackfolio.microservices.instagramoauthclient.exceptions.AccessTokenException;
import com.thejackfolio.microservices.instagramoauthclient.models.AccessTokenRequest;
import com.thejackfolio.microservices.instagramoauthclient.models.AccessTokenResponse;
import com.thejackfolio.microservices.instagramoauthclient.utilities.PropertiesReader;
import com.thejackfolio.microservices.instagramoauthclient.utilities.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthClientServiceHelper {

    @Autowired
    private InstagramAPIClient apiClient;

    public AccessTokenResponse getAccessToken(String code) throws AccessTokenException {
        try{
            AccessTokenRequest request = new AccessTokenRequest(PropertiesReader.getProperty(StringConstants.CLIENT_ID),PropertiesReader.getProperty(StringConstants.CLIENT_SECRET),
                    PropertiesReader.getProperty(StringConstants.GRANT_TYPE_ACCESS_TOKEN), PropertiesReader.getProperty(StringConstants.REDIRECT_URI), code);
            return apiClient.getAccessToken(request);
        } catch (Exception exception){
            throw new AccessTokenException(StringConstants.ACCESS_TOKEN_EXCEPTION);
        }
    }

    public String constructAuthorizationUrl(){
        String clientId = PropertiesReader.getProperty(StringConstants.CLIENT_ID);
        String redirectUri = PropertiesReader.getProperty(StringConstants.REDIRECT_URI);
        String scope = PropertiesReader.getProperty(StringConstants.SCOPE);
        String responseType = PropertiesReader.getProperty(StringConstants.RESPONSE_TYPE);
        String authorizationUrl = "https://api.instagram.com/oauth/authorize"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=" + scope
                + "&response_type=" + responseType;
        return authorizationUrl;
    }

    public String constructGetPostsUrl(){
        String getPostsUrl = "http://localhost:8084/instagram/get-posts";
        return getPostsUrl;
    }

    public String constructGetPostNewTokenURL(String accessToken){
        String getPostsNewTokenUrl = "http://localhost:8084/instagram/get-posts-new-token?access_token="+accessToken;
        return getPostsNewTokenUrl;
    }

    public String constructAccessDeniedURL(){
        String accessDeniedURL = "http://localhost:8085/access-denied";
        return  accessDeniedURL;
    }
}
