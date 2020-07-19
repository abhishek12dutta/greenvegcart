package com.cart.greenveg.authserver.service;

import com.cart.greenveg.authserver.dao.OAuthDAO;
import com.cart.greenveg.authserver.model.CartUser;
import com.cart.greenveg.authserver.model.GreenVegCartUser;
import com.cart.greenveg.authserver.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OAuthDAO oAuthDAO;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = null;

        try {
            userEntity = oAuthDAO.getUserDetails(username);

            if (userEntity != null && userEntity.getId().intValue()>0){
                GreenVegCartUser user = new GreenVegCartUser(userEntity);
                return user;
            } else {
                throw new UsernameNotFoundException("User " + username + " was not found in the database");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }

    /**
     * @param user
     */
    public void createUser(CartUser user) {
        oAuthDAO.createUser(user);
    }
}
