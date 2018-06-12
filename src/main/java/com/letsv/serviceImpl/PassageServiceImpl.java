package com.letsv.serviceImpl;

import com.letsv.dao.PassageDao;
import com.letsv.model.Passage;
import com.letsv.service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("PassageService")
public class PassageServiceImpl implements PassageService {
    private final PassageDao passageDao;

    @Autowired
    public PassageServiceImpl(PassageDao passageDao) {
        this.passageDao = passageDao;
    }

    @Override
    public List<Passage> getPassageList() {
        List<Passage> list = passageDao.getPassageList();
        List<Passage> passagelist = new LinkedList<>();
        Random rand = new Random();
        if (list.size() > 10) {
            Set<Integer> set = new HashSet<>();
            while(passagelist.size() < 10) {
                int index = rand.nextInt(list.size());
                if (!set.contains(index)) {
                    passagelist.add(list.get(index));
                    set.add(index);
                }
            }
            return passagelist;
        } else {
            return list;
        }
    }

    @Override
    public Passage getPassageById(String passageId) {
        return passageDao.findPassageByid(passageId);
    }

    @Override
    public boolean savePassage(String title,String type, String date, String imageUrl, String content) {
        String passageId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        int wordNumber = content.split(" ").length;
        Passage passage = new Passage(passageId,title,type,date,imageUrl,content,wordNumber);
        return passageDao.savePassage(passage);
    }
}
