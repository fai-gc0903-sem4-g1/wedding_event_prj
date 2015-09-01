/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author SON
 */
@Entity(name = "user_profile")
public class UserProfile implements Serializable {
    @Id
    @Column(nullable = false)
    @GenericGenerator(name="id", strategy="uuid")
    @GeneratedValue(generator="id")
    private String userProfileId;
    private String accountId;//foreign key
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date birthday;
    private String maritalStatus;
    private int height;
    private int weight;
    private String country;
    private String city;
    private String countryLiveIn;
    private String cityLiveIn;
    private String religion;
    private String caste;
    private String introduction;
    private Timestamp lastUpdateProfile;

    public Timestamp getLastUpdateProfile() {
        return lastUpdateProfile;
    }

    @Override
    public String toString() {
        return "UserProfile{" + "userProfileId=" + userProfileId + ", accountId=" + accountId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", birthday=" + birthday + ", maritalStatus=" + maritalStatus + ", height=" + height + ", weight=" + weight + ", country=" + country + ", city=" + city + ", countryLiveIn=" + countryLiveIn + ", cityLiveIn=" + cityLiveIn + ", religion=" + religion + ", caste=" + caste + ", introduction=" + introduction + ", lastUpdateProfile=" + lastUpdateProfile + '}';
    }

    public void setLastUpdateProfile(Timestamp lastUpdateProfile) {
        this.lastUpdateProfile = lastUpdateProfile;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    
    public String getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(String userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryLiveIn() {
        return countryLiveIn;
    }

    public void setCountryLiveIn(String countryLiveIn) {
        this.countryLiveIn = countryLiveIn;
    }

    public String getCityLiveIn() {
        return cityLiveIn;
    }

    public void setCityLiveIn(String cityLiveIn) {
        this.cityLiveIn = cityLiveIn;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }
    
    
}
