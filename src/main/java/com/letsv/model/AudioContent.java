package com.letsv.model;

import java.io.Serializable;

public class AudioContent implements Serializable {
    private String audiocontentId;
    private String time;
    private String content;
    private String audio;

    public AudioContent(String audiocontentId, String time, String content, String audio){
        this.audiocontentId = audiocontentId;
        this.time = time;
        this.content = content;
        this.audio = audio;
    }


    public String getAudiocontentId() {
        return audiocontentId;
    }

    public void setAudiocontentId(String audiocontentId) {
        this.audiocontentId = audiocontentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
