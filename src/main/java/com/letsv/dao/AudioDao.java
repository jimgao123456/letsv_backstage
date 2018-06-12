package com.letsv.dao;

import com.letsv.model.Audio;
import com.letsv.model.AudioContent;

import java.util.List;

public interface AudioDao {
    List<Audio> getAudioList();

    Audio findAudioById(String audioId);
    List<AudioContent> getAudioContent(String audioId);

    boolean saveAudio(Audio audio, List<AudioContent> audioContentList);
}
