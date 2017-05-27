package com.superx.callorder.controller.manage;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.superx.callorder.entity.Dictionary;
import com.superx.callorder.service.DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;
	@RequestMapping("")
    public String index(HttpServletRequest request, 
    		HttpServletResponse response,
    		Dictionary dictionary,Model model) {
		
		List<Dictionary> dictionaryList = dictionaryService.selectDictionaryList(dictionary);
		model.addAttribute("dictionaryList", dictionaryList);
        return "/dictionary/index";
    }
	
	@RequestMapping("/editDictionary")
    public String editDictionary(HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer dictionaryid,String dictValue,Model model) {
		try {
			String val = URLDecoder.decode(dictValue, "utf-8");
			Dictionary dictionary = dictionaryService.selectByPrimaryKey(dictionaryid);
			dictionary.setDictValue(val);
			dictionaryService.updateByPrimaryKeySelective(dictionary);
		} catch (UnsupportedEncodingException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		
        return "redirect:/dictionary";
    }
}
