package com.letsv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Audio implements Serializable {
    private String audioId;
    private String title;
    private String date;
    private String imageUrl;
    private String audioUrl;

    public Audio(String audioId, String title, String date, String imageUrl, String audioUrl){
        this.audioId = audioId;
        this.title = title;
        this.date = date;
        this.imageUrl = imageUrl;
        this.audioUrl = audioUrl;
    }

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
