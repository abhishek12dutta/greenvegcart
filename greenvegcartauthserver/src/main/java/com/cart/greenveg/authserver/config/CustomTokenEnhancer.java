package com.cart.greenveg.authserver.config;

import com.cart.greenveg.authserver.model.GreenVegCartUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        GreenVegCartUser user = (GreenVegCartUser) authentication.getPrincipal();
        Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
        if (user.getId() != null) {
            info.put("id", user.getId());
        }
//        if (user.getFirstName() != null) {
//            info.put("fname", user.getFirstName());
//        }
//
//        if (user.getFirstName() != null) {
//            info.put("lname", user.getLastName());
//        }
//
//        if (user.getFirstName() != null) {
//            info.put("mname", user.getMiddleName());
//        }
//
//        if (user.getUsername() != null) {
//            info.put("userName", user.getUsername());
//        }
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);
        return super.enhance(customAccessToken, authentication);
    }

}
