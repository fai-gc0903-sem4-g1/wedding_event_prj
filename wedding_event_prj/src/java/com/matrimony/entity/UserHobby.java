/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.entity;

import java.io.Serializable;
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
@Entity(name="user_hobby")
public class UserHobby implements Serializable {
    @Id
    @Column(nullable = false)
    @GenericGenerator(name="id", strategy="uuid")
    @GeneratedValue(generator="id")
    private String userHobbyId;
    private String accountId;
    @Column(length = 1000)
    private String music;
    @Column(length = 1000)
    private String movie;
    @Column(length = 1000)
    private String sport;
    @Column(length = 1000)
    private String tvshow;
    @Column(length = 1000)
    private String book;
    private Timestamp lastUpdateHobby;

    public String getUserHobbyId() {
        return userHobbyId;
    }

    public void setUserHobbyId(String userHobbyId) {
        this.userHobbyId = userHobbyId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTvshow() {
        return tvshow;
    }

    public void setTvshow(String tvshow) {
        this.tvshow = tvshow;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Timestamp getLastUpdateHobby() {
        return lastUpdateHobby;
    }

    public void setLastUpdateHobby(Timestamp lastUpdateHobby) {
        this.lastUpdateHobby = lastUpdateHobby;
    }
    
}
