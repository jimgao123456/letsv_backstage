package com.letsv.daoImpl;

import com.letsv.dao.AudioDao;
import com.letsv.model.Audio;
import com.letsv.model.AudioContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AudioDaoImpl implements AudioDao {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AudioDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Audio findAudioById(String audioId) {
        Query query = new Query(Criteria.where("audioId").is(audioId));
        return mongoTemplate.findOne(query, Audio.class);
    }

    @Override
    public List<Audio> getAudioList() {
        return mongoTemplate.findAll(Audio.class);
    }

    @Override
    public List<AudioContent> getAudioContent(String audioId) {
        Query query = new Query(Criteria.where("audio").is(audioId));
        return mongoTemplate.find(query, AudioContent.class);
    }

    @Override
    public boolean saveAudio(Audio audio, List<AudioContent> audioContentList) {
        for (AudioContent audioContent : audioContentList) {
            mongoTemplate.save(audioContent);
        }
        mongoTemplate.save(audio);
        return true;
    }

}
