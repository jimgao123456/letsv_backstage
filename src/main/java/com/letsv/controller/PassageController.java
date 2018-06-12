package com.letsv.controller;

import com.letsv.model.Passage;
import com.letsv.serviceImpl.PassageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class PassageController {
    private final PassageServiceImpl passageService;

    @Autowired
    public PassageController(PassageServiceImpl passageService) {
        this.passageService= passageService;
    }

    @PostMapping("/passagelist")
    public @ResponseBody Map<Integer,Object> getPassageList() {
        List<Passage> passageList = passageService.getPassageList();

        Map<Integer, Object> passageListMap = new HashMap<>();
        for (int i = 0; i < passageList.size(); i++){
            Passage passage = passageList.get(i);
            Map<String,String> passagemap = new HashMap<>();
            passagemap.put("passageId", passage.getPassageId());
            passagemap.put("title", passage.getTitle());
            passagemap.put("date", passage.getDate());
            passagemap.put("type", passage.getType());
            passagemap.put("imageUrl", passage.getImageUrl());
            //passagemap.put("content", passage.getContent());
            passagemap.put("wordNumber", "" + passage.getWordNumber());
            passageListMap.put(i, passagemap);
        }

        return passageListMap;
    }

    @PostMapping("/passagecontent")
    public @ResponseBody Map<String,String> getPassage(String passageid) {
        Passage passage = passageService.getPassageById(passageid);
        Map<String,String> passagemap = new HashMap<>();
        if(passage!=null){
	        passagemap.put("passageId", passage.getPassageId());
	        passagemap.put("title", passage.getTitle());
	        passagemap.put("date", passage.getDate());
	        passagemap.put("type", passage.getType());
	        passagemap.put("imageUrl", passage.getImageUrl());
	        passagemap.put("content", passage.getContent());
	        passagemap.put("wordNumber", "" + passage.getWordNumber());
	        passagemap.put("success","0");
        }else {
        	passagemap.put("success","1");
        }
        return passagemap;
    }

    @PostMapping("/savepassage")
    public @ResponseBody void savePassage(String title, String type, String date, String imageUrl, String content) {
        passageService.savePassage(title,type,date,imageUrl,content);
    }


}
