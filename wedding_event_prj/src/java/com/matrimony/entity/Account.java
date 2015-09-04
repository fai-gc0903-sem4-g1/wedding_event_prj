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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
    @Length(min = 4, max = 50)
    private String username;
    @Length(min = 4, max = 100)
    private String passwordHash;
    private String salt;
    @Email
    private String email;
    private String roleName;
    private String contactNumber;
    private String registrationIP;
    private String activeKey;
    private String lastIPLogin;
    private String regMethod;
    private Timestamp lastTimeLogin;
    private Timestamp registrationTime;
    private Timestamp lastTimeChangePassword;
    private Timestamp activeTime;
    private boolean activated;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountId")
    private Set<UserProfile> userProfiles;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountId")
    private Set<UserHobby> userHobbys;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountId")
    private Set<UserFriend> userFriends;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountId")
    private Set<InviteAddFriend> inviteAddFriends;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "senderId")
    private Set<Friend> friends;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiverId")
    private Set<Friend> receivers;

    public String getRegMethod() {
        return regMethod;
    }

    public void setRegMethod(String regMethod) {
        this.regMethod = regMethod;
    }

    public String getRegistrationIP() {
        return registrationIP;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public Set<Friend> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<Friend> receivers) {
        this.receivers = receivers;
    }

    public String getActiveKey() {
        return activeKey;
    }

    public void setActiveKey(String activeKey) {
        this.activeKey = activeKey;
    }

    public void setRegistrationIP(String registrationIP) {
        this.registrationIP = registrationIP;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<UserFriend> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(Set<UserFriend> userFriends) {
        this.userFriends = userFriends;
    }

    public Set<InviteAddFriend> getInviteAddFriends() {
        return inviteAddFriends;
    }

    public void setInviteAddFriends(Set<InviteAddFriend> inviteAddFriends) {
        this.inviteAddFriends = inviteAddFriends;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Timestamp getLastTimeChangePassword() {
        return lastTimeChangePassword;
    }

    public void setLastTimeChangePassword(Timestamp lastTimeChangePassword) {
        this.lastTimeChangePassword = lastTimeChangePassword;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", username=" + username + ", passwordHash=" + passwordHash + ", salt=" + salt + ", email=" + email + ", lastTimeLogin=" + lastTimeLogin + ", registrationTime=" + registrationTime + ", lastTimeChangePassword=" + lastTimeChangePassword + ", lastIPLogin=" + lastIPLogin + ", activated=" + activated + ", roleName=" + roleName + ", userProfiles=" + userProfiles + ", userHobbys=" + userHobbys + ", userFriends=" + userFriends + ", inviteAddFriends=" + inviteAddFriends + '}';
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
