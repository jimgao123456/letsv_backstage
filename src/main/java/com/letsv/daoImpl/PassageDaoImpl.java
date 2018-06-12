package com.letsv.daoImpl;

import com.letsv.dao.PassageDao;
import com.letsv.model.Passage;
import com.letsv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassageDaoImpl implements PassageDao {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PassageDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Passage findPassageByid(String passageId) {
        Query query = new Query(Criteria.where("passageId").is(passageId));
        return mongoTemplate.findOne(query, Passage.class);
    }

    @Override
    public List<Passage> getPassageList() {
        return mongoTemplate.findAll(Passage.class);
    }

    @Override
    public boolean savePassage(Passage passage) {
        mongoTemplate.save(passage);
        return true;
    }
}
