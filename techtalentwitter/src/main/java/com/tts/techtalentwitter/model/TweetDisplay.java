package com.tts.techtalentwitter.model;

import java.util.List;




public class TweetDisplay {
    private User user; 
    private String message; 
    private String date; 
    private List<Tag> tag; 

    public TweetDisplay(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TweetDisplay [date=" + date + ", message=" + message + ", tag=" + tag + ", user=" + user + "]";
    }
    
    
    
}

