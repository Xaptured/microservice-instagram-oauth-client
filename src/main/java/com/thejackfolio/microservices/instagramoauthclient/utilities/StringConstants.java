package com.thejackfolio.microservices.instagramoauthclient.utilities;

public class StringConstants {

    // Mapper and Database error messages
    public static final String MAPPING_ERROR = "Error occurred while mapping";
    public static final String DATABASE_ERROR = "Error occurred while database operation";
    public static final String REQUEST_PROCESSED = "Request Processed";
    public static final String MAPPING_ERROR_MODEL_TO_ENTITY = "Error occurred while converting model to entity";
    public static final String MAPPING_ERROR_ENTITY_TO_MODEL = "Error occurred while converting entity to model";
    public static final String VALIDATION_ERROR = "Error occurred while validation";
    public static final String ACCESS_DENIED = "Access Denied by the instagram user";
    public static final String ACCESS_TOKEN_EXCEPTION = "Exception occurred while getting access token";
    public static final String ACCESS_DENIED_MESSAGE = "access_denied";

    public static final String FIELDS = "FIELDS";
    public static final String LIMIT = "LIMIT";
    public static final String GRANT_TYPE_REFRESH_ACCESS_TOKEN = "GRANT_TYPE_REFRESH_ACCESS_TOKEN";
    public static final String CLIENT_ID = "CLIENT_ID";
    public static final String CLIENT_SECRET = "CLIENT_SECRET";
    public static final String REDIRECT_URI = "REDIRECT_URI";
    public static final String SCOPE = "SCOPE";
    public static final String RESPONSE_TYPE = "RESPONSE_TYPE";
    public static final String GRANT_TYPE_ACCESS_TOKEN = "GRANT_TYPE_ACCESS_TOKEN";
    public static final String GRANT_TYPE_LONG_LIVE_ACCESS_TOKEN = "GRANT_TYPE_LONG_LIVE_ACCESS_TOKEN";
    public static final String FALLBACK_MESSAGE = "Something went wrong. Please try again later";
    public static final String RETRY_MESSAGE = "Something went wrong. Doing retry...";

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE = "ROLE";

    private StringConstants(){}
}
