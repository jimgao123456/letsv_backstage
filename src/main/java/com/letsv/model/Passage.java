package com.letsv.model;

import java.io.Serializable;
import java.util.Date;

public class Passage implements Serializable {

    private String passageId;
    private String title;
    private String type;
    private String date;
    private String imageUrl;
    private String content;
    private int wordNumber;

    public Passage(String passageId, String title,String type, String date, String imageUrl, String content, int wordNumber) {
        this.passageId = passageId;
        this.title = title;
        this.type = type;
        this.date = date;
        this.imageUrl = imageUrl;
        this.content = content;
        this.wordNumber = wordNumber;
    }

    public String getPassageId() {
        return passageId;
    }

    public void setpassageId(String passageId) {
        this.passageId = passageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }
}
