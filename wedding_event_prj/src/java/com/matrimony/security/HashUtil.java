/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.security;

/**
 *
 * @author SON
 */
public class HashUtil {
    public static String hashPassword(String password){
        return "";
    }
    
    public static void main(String[] args) {
        String passowrd="1234";
        String hashed=HashUtil.hashPassword(passowrd);
        System.out.println(hashed);
    }
}
