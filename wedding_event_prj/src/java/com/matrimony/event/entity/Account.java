/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.event.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author SON
 */
@Entity(name = "account")
public class Account implements Serializable {

    @Id
    @Column(nullable = false)
    @GenericGenerator(name = "id", strategy = "uuid")
    @GeneratedValue(generator = "id")
    private String accountId;
    private String username;
    private String passwordHash;
    private String email;
    private Timestamp lastTimeLogin;
    private String lastIPLogin;
    private boolean activated;
    @OneToOne(mappedBy = "accountId")
    private Set<UserProfile> userProfile;

    public Set<UserProfile> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Set<UserProfile> userProfile) {
        this.userProfile = userProfile;
    }

    

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(Timestamp lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public String getLastIPLogin() {
        return lastIPLogin;
    }

    public void setLastIPLogin(String lastIPLogin) {
        this.lastIPLogin = lastIPLogin;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

}
