package com.letsv.service;

import com.letsv.model.Audio;
import com.letsv.model.AudioContent;

import java.util.List;
import java.util.Map;

public interface AudioService {
    List<Audio> getAudioList();

    Audio findAudioById(String audioId);
    List<AudioContent> getAudioContent(String audioId);

    Map<String, Object> getWholeAudio(String audioId);

    boolean saveAudio(String title, String date, String imageUrl, String audioUrl, String content);
}
