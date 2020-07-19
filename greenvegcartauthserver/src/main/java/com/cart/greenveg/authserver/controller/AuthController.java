package com.cart.greenveg.authserver.controller;

import com.cart.greenveg.authserver.model.CartUser;
import com.cart.greenveg.authserver.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody CartUser user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsService.createUser(user);
        return ResponseEntity.ok().build();
    }
}
