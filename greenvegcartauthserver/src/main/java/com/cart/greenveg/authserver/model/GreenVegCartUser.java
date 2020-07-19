package com.cart.greenveg.authserver.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

public class GreenVegCartUser extends User {

//    private String id;
//    private String name;

    private BigInteger id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();


    public GreenVegCartUser(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), userEntity.getGrantedAuthoritiesList());
        // super(userEntity.getEmailId(), userEntity.getPassword(), false, true, true, true, userEntity.getGrantedAuthoritiesList());
        this.id = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.middleName = userEntity.getMiddleName();
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
