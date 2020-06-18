package com.cart.greenveg.authserver.dao;

import com.cart.greenveg.authserver.model.UserEntity;

public interface OAuthDAO {
    UserEntity getUserDetails(String username);
}
