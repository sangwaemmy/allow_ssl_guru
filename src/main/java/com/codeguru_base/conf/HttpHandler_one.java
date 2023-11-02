/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.conf;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 *
 * @author HP
 */
public class HttpHandler_one implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        // Implement your custom hostname verification logic here
        // Return true if the hostname is considered valid; false if not
        return true;
    }
}
