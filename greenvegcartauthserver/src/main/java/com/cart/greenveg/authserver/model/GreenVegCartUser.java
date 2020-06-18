package com.cart.greenveg.authserver.model;

import org.springframework.security.core.userdetails.User;

public class GreenVegCartUser extends User {

    private String id;
    private String name;

    public GreenVegCartUser(UserEntity userEntity) {
        super(userEntity.getEmailId(), userEntity.getPassword(), userEntity.getGrantedAuthoritiesList());
       // super(userEntity.getEmailId(), userEntity.getPassword(), false, true, true, true, userEntity.getGrantedAuthoritiesList());
        this.id=userEntity.getId();
        this.name=userEntity.getName();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
