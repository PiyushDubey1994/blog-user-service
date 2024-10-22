//package com.blogsite.configure;
//
//
//import com.akeyless.AkeylessClient;
//import com.akeyless.auth.swagger.auth.ApiKeyAuth;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AkeylessConfig {
//
//    @Value("${akeyless.api-url}")
//    private String apiUrl;
//
//    @Value("${akeyless.access-token}")
//    private String accessToken;
//
//    @Bean
//    public AkeylessClient akeylessClient() {
//        AkeylessClient client = new AkeylessClient();
//        client.setBasePath(apiUrl);
//
//        // Use access token for authentication
//        ApiKeyAuth token = (ApiKeyAuth) client.getAuthentication("Bearer");
//        token.setApiKey(accessToken);
//
//        return client;
//    }
//}
