package com.letsv.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.letsv.common.HttpUtils;
import com.letsv.dao.UserDao;
import com.letsv.dao.WordDao;
import com.letsv.model.Word;
import com.letsv.service.WordService;
import org.dom4j.io.SAXReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("WordService")
public class WordServiceImpl implements WordService {
	private final WordDao wordDao ;

	@Autowired
	public WordServiceImpl(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	@Override
	public String getWord(String word) {
		Word word1=wordDao.findWordByName(word);
		if(word1!=null) return word1.getWordJson();
		else {
			word1=new Word();
			word1.setWord(word);
			word1.setWordJson(getWordXML(word));
			wordDao.saveWord(word1);
		}
		return word1.getWordJson();
	}

	@Override
	public void saveWord(Word word) {
		wordDao.saveWord(word);
	}

	@Override
	public Map<String,Object> wordToJson(String word) {
		HashMap<String,Object> map=new HashMap<>();
		map.put("word",word);
		map.put("music","http://media.shanbay.com/audio/us/"+word+".mp3");
		String xml=getWord(word);
		SAXBuilder reader = new SAXBuilder();
		try {
			Document document = reader.build(new StringReader(xml));
			Element root=document.getRootElement();
			Element ps=root.getChild("ps");
			map.put("ps","["+ps.getText()+"]");
			List pos=root.getChildren("pos");
			List acceptation=root.getChildren("acceptation");
			StringBuilder meaning= new StringBuilder();
			for (int i=0;i<pos.size();i++){
				Element opos= (Element) pos.get(i);
				Element oacceptation= (Element) acceptation.get(i);
				System.out.println(opos.getText()+" : "+oacceptation.getText());
				meaning.append(opos.getText()).append("  ").append(oacceptation.getText()).append("\r\n");
			}
			map.put("meaning",meaning.toString());
			List sent=root.getChildren("sent");
			int num=0;
			for (Object o :sent){
				Element element= (Element) o;
				Element orig=element.getChild("orig");
				Element trans=element.getChild("trans");
				map.put("title"+num,"例句"+num);
				map.put("content"+num,"<font color='#cccccc'>"+orig.getText()+"</font><font color='#8f8f8f'>   [来自官方]</font><br><font color='#cccccc'>"+trans.getText()+"</font><br>");
				num++;
			}

		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	private static String getWordXML(String word){
		HashMap<String,String> map= new HashMap<>();
		map.put("key","3BB2876813D6B3623E1AA46BDF6CC59C");
		map.put("w",word);
		return HttpUtils.sendGet("http://dict-co.iciba.com/api/dictionary.php",map);
	}
}
