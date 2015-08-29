/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Timestamp registrationTime;
    private Timestamp lastTimeChangePassword;
    private String lastIPLogin;
    private boolean activated;
    private String roleName;
    @OneToMany(mappedBy = "accountId")
    private Set<UserProfile> userProfiles;
    @OneToMany(mappedBy = "accountId")
    private Set<UserHobby> userHobbys;
    @OneToMany(mappedBy = "accountId")
    private Set<UserFriend> userFriends;
    //@OneToMany(mappedBy = "accountId")
    //private Set<InviteAddFriend> inviteAddFriends;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
//    public Set<InviteAddFriend> getInviteAddFriends() {
//        return inviteAddFriends;
//    }
    
//
//    public void setInviteAddFriends(Set<InviteAddFriend> inviteAddFriends) {
//        this.inviteAddFriends = inviteAddFriends;
//    }
    
    
    public Timestamp getLastTimeChangePassword() {
        return lastTimeChangePassword;
    }

    public Set<UserFriend> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(Set<UserFriend> userFriends) {
        this.userFriends = userFriends;
    }

    public void setLastTimeChangePassword(Timestamp lastTimeChangePassword) {
        this.lastTimeChangePassword = lastTimeChangePassword;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public Set<UserHobby> getUserHobbys() {
        return userHobbys;
    }

    public void setUserHobbys(Set<UserHobby> userHobbys) {
        this.userHobbys = userHobbys;
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
