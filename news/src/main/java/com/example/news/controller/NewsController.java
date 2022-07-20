package com.example.news.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.news.service.NewsService;

@Controller
public class NewsController implements ErrorController {
	
	@Autowired
	NewsService newsService;
	
	@GetMapping("/news/{topic}")
	@ResponseBody
	public String getNews(@PathVariable String topic) {
		String result = "";
		try {
			if(StringUtils.isAlpha(topic)) {
			 result = newsService.getResult(topic);
			} else {
				return "Please pass the topic in string format without any spaces";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	    @RequestMapping("/error")
	    @ResponseBody
	    public String getErrorPath() {
	        // TODO Auto-generated method stub
	        return "404 :-No Mapping Found";
	    }
	
}
