package com.letsv.controller;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> upload(@RequestParam("file") MultipartFile file,
	                             HttpServletRequest request) {
		Map<String,Object> map=new HashMap<>();
		String contentType = file.getContentType();
		String fileName = file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
		String filePath = request.getSession().getServletContext().getRealPath("upload/");
		System.out.println(filePath);
		try {
			boolean succ=FileController.uploadFile(file.getBytes(), filePath, fileName);
			if(!succ) throw new Exception();
		} catch (Exception e) {
			map.put("success",1);
			return map;
		}
		//返回json
		map.put("success",0);
		return map;
	}

	private static boolean uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if(!targetFile.exists()){
			boolean t=targetFile.mkdirs();
			if(!t) return false;
		}
		FileOutputStream out = new FileOutputStream(filePath+fileName);
		out.write(file);
		out.flush();
		out.close();
		return true;
	}
}
