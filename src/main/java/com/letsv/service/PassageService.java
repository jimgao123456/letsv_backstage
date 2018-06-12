package com.letsv.service;

import com.letsv.model.Passage;

import java.util.Date;
import java.util.List;

public interface PassageService {
    List<Passage> getPassageList();
    Passage getPassageById(String passageId);
    boolean savePassage(String title,String type, String date, String imageUrl, String content);
}
