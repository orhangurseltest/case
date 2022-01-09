package com.getir.readingisgood.service;


import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AuthenticationServiceImplTest extends AbstractTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    @Order(1)
    public void testAuthentication_Pass() {
        UserDetails user = authenticationService.loadUserByUsername("getir");

        Assertions.assertEquals("getir", user.getUsername());
        Assertions.assertEquals(0, user.getAuthorities().size());
        Assertions.assertTrue(user.isEnabled());
        Assertions.assertTrue(user.isAccountNonExpired());
        Assertions.assertTrue(user.isAccountNonLocked());
        Assertions.assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    @Order(2)
    public void testAuthentication_Failed() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> authenticationService.loadUserByUsername("123456"));
    }

}
