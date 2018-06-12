package com.letsv.controller;


import com.letsv.model.Audio;
import com.letsv.serviceImpl.AudioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AudioController {
    private AudioServiceImpl audioService;

    @Autowired
    public AudioController(AudioServiceImpl audioService) {
        this.audioService= audioService;
    } 
    
	@PostMapping("/audiolist")
	public @ResponseBody Map<Integer,Object> getAudioList() {

		List<Audio> audioList = audioService.getAudioList();

        Map<Integer, Object> audioListMap = new HashMap<>();
        for (int i = 0; i < audioList.size(); i++){
            Audio audio = audioList.get(i);
            Map<String, String> audiomap = new HashMap<>();
            audiomap.put("audioId", audio.getAudioId());
            audiomap.put("title", audio.getTitle());
            audiomap.put("date", audio.getDate());
            audiomap.put("imageUrl", audio.getImageUrl());
            audioListMap.put(i, audiomap);
        }
        return audioListMap;
	}

    @PostMapping("/audio")
    public @ResponseBody Map<String,Object> getAudio(String audioid) {
        return audioService.getWholeAudio(audioid);
    }

    @PostMapping("/saveaudio")
    public @ResponseBody void saveAudio(String title, String date, String imageUrl, String audioUrl, String content) {
       audioService.saveAudio(title, date, imageUrl, audioUrl, content);
    }
}
