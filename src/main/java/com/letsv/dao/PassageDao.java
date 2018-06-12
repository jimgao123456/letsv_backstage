package com.letsv.dao;

import com.letsv.model.Passage;
import com.letsv.model.User;

import java.util.List;

public interface PassageDao {

    Passage findPassageByid(String passageId);
    List<Passage> getPassageList();

    boolean savePassage(Passage passage);
}
