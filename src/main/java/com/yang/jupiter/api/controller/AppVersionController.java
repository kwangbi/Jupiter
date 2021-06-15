package com.yang.jupiter.api.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.jupiter.api.service.AppVersionService;
import com.yang.jupiter.core.redis.CacheConstants.RedisType;
import com.yang.jupiter.core.redis.util.CacheUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
* @packageName : com.yang.jupiter.controller 
* @fileName : AppVersionController.java 
* @author : Jerry Yang 
* @date : 2021.04.30 
* @description : 
* =========================================================== 
* DATE 					AUTHOR 			NOTE 
* ----------------------------------------------------------- 
* 2021.04.30		Jerry Yang 		최초 생성
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/redis")
public class AppVersionController {
	
	private final AppVersionService appVersionService;
	
	/**
	 * 
	* @methodName : setAppVersion 
	* @author : Jerry Yang
	* @date : 2021.04.30 
	* @return
	 */
	@GetMapping("/appversion")
	public int setAppVersion() {
		
		// redis 등록
		int i = appVersionService.createKeysWithEntryName("RS1Test", RedisType.RS1);
		
		// 등록한 데이터 조회
		String jsonStr = CacheUtils.getCacheData("RS1Test", "appLoad", String.class, RedisType.RS1);
		log.debug("jsonStr  : {}",jsonStr );
		
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(jsonStr);
            JSONObject jsonObj = (JSONObject) obj;
            Object appObj = jsonObj.get("appVersion");
            log.debug("appObj  : {}",appObj.toString());
            
            ObjectMapper mapper = new ObjectMapper();
            try {
            	Map<String, Object> dataMap = null;
            	
            	dataMap = mapper.readValue(appObj.toString(), Map.class);
            	
            	log.debug("dataMap  : {}",dataMap.toString());
                	
            }catch(Exception e) {
            	
            }
            
            //String appVersion = (String) jsonObj.get("appVersion");
            String signGateGW = (String) jsonObj.get("signGateGW");
            
            //log.debug("appVersion  : {}",appVersion );
            log.debug("signGateGW  : {}",signGateGW );
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
		return i;
		
	}

}
