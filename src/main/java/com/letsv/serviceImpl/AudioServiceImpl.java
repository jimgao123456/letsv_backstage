package com.letsv.serviceImpl;

import com.letsv.dao.AudioDao;
import com.letsv.daoImpl.AudioDaoImpl;
import com.letsv.model.Audio;
import com.letsv.model.AudioContent;
import com.letsv.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("AudioService")
public class AudioServiceImpl implements AudioService {
    private final AudioDao audioDao;

    @Autowired
    public AudioServiceImpl(AudioDao audioDao){
        this.audioDao = audioDao;
    }

    @Override
    public List<Audio> getAudioList() {
        List<Audio> list = audioDao.getAudioList();
        List<Audio> audiolist = new LinkedList<>();
        Random rand = new Random();
        if (list.size() > 10) {
            Set<Integer> set = new HashSet<>();
            while(audiolist.size() < 10) {
                int index = rand.nextInt(list.size());
                if (!set.contains(index)) {
                    audiolist.add(list.get(index));
                    set.add(index);
                }
            }
            return audiolist;
        } else {
            return list;
        }
    }

    @Override
    public Audio findAudioById(String audioId) {
        return audioDao.findAudioById(audioId);
    }

    @Override
    public List<AudioContent> getAudioContent(String audioId) {
        return audioDao.getAudioContent(audioId);
    }

    @Override
    public Map<String, Object> getWholeAudio(String audioId) {
        Audio audio = findAudioById(audioId);
	    List<AudioContent> audioContentList;
	    Map<String, Object> audiomap = new HashMap<>();
	    if(audio!=null){
	    	audioContentList=getAudioContent(audioId);
		    audiomap.put("audioId", audio.getAudioId());
		    audiomap.put("title", audio.getTitle());
		    audiomap.put("date", audio.getDate());
		    audiomap.put("imageUrl", audio.getImageUrl());
		    audiomap.put("audioUrl", audio.getAudioUrl());
		    Map<String,String> audioContentMap = new HashMap<>();
		    for (AudioContent audioContent : audioContentList){
			    audioContentMap.put(audioContent.getTime(),audioContent.getContent());
		    }
		    audiomap.put("content", audioContentMap);
		    audiomap.put("success",0);
	    }else {
		    audiomap.put("success",1);
	    }
        return audiomap;
    }

    @Override
    public boolean saveAudio(String title, String date, String imageUrl, String audioUrl, String content) {
        String audioId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        Audio audio = new Audio(audioId, title, date, imageUrl, audioUrl);
        List<AudioContent> audioContentList = new LinkedList<>();

        String[] contents = content.split("###");
        for(String st : contents){
            String[] cells = st.split("\t");
            String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            AudioContent audioContent = new AudioContent(id, cells[0], cells[1], audioId);
            audioContentList.add(audioContent);
        }
        return audioDao.saveAudio(audio,audioContentList);
    }
}
