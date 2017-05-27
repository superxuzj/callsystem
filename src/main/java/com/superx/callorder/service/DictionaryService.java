package com.superx.callorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superx.callorder.entity.Dictionary;
import com.superx.callorder.repository.DictionaryMapper;

@Service
public class DictionaryService {
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	public List<Dictionary> selectDictionaryList(Dictionary dictionary){
		return dictionaryMapper.selectDictionaryList(dictionary);
	}

	public Dictionary selectByPrimaryKey(Integer id){
		return dictionaryMapper.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(Dictionary record){
    	return dictionaryMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 根据code 返回数据字典的value
     * @param code
     * @return
     */
    public String getDictvalueByCode(String code){
    	Dictionary record = new Dictionary();
    	record.setCode(code);
    	List<Dictionary> dictionaryList = dictionaryMapper.selectDictionaryList(record);
    	if(dictionaryList!=null && dictionaryList.size()>0){
    		Dictionary dictionary = dictionaryList.get(0);
    		return dictionary.getDictValue();
    	}else{
    		return "中国地震局。";
    	}
    }
}
