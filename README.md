# microservice-instagram-oauth-client

This microservice is used as a layer above the Instagram Authorization APIs which are used to do authorization and get access token.  

 

Please keep in mind that this service is a https protected so all the URLs which access the resources in this service will be https enabled. 

 

If you want to clone this repository and use it in your local then please follow these steps: 

 

Step 1: Please go to the path src/main/java/resources and there you will find keys_dummy.properties file. 

 

Step 2: Rename the file from keys_dummy.properties to keys.properties and add your values accordingly. You can also take reference from Google to fill the appropriate keys in the file. 

 

Other links that can be useful while running this repo in local: 

Swagger UI: http://localhost:{your-port}/swagger-ui/index.html 

All the APIs in this service are secured with https:// because of security reasons that deals with the Instagram Oauth APIs. So if you are making any request to this service then please make a https:// request

All the APIs related to this service has been documented in a postman_collection file which can be viewed by importing in postman application. It gives you better idea regarding APIs and the parameters that we are passing into them.
This doscument will be uploaded in the TheJackFolioParentReposiotry as well as in each microservice. Please go through it and also you can directly test the APIs after importing into Postman.

### Will be adding more information here if I add any new feature 
