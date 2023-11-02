/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author HP
 */
@Configuration
@EnableWebSecurity
public class HttpsHandle extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Secure specific endpoints with HTTPS
//        http
//                .authorizeRequests()
//                .antMatchers("/secure-endpoint1", "/secure-endpoint2")
//                .requiresSecure() // Enforces HTTPS for these endpoints
//                .anyRequest().permitAll(); // Allows other endpoints to be accessed over HTTP

        http
                .requiresChannel()
                .anyRequest()
                .requiresSecure();
    }

}
