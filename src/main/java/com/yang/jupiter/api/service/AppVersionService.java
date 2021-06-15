package com.yang.jupiter.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.jupiter.api.dto.AppVersionDTO;
import com.yang.jupiter.core.exception.BusinessException;
import com.yang.jupiter.core.redis.CacheConstants;
import com.yang.jupiter.core.redis.loader.service.Service1BaseCacheService;
import com.yang.jupiter.core.redis.util.RedisLoaderUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppVersionService extends Service1BaseCacheService {

    @Override
    public int createKeysWithEntryName(final String metaSet, final CacheConstants.RedisType redisType) throws BusinessException {
        final List<Object> results = getRedisTemplate(CacheConstants.RedisType.RS1).executePipelined(
                new RedisCallback<Object>() {
                    @Override
                    public Object doInRedis(final RedisConnection connection) throws DataAccessException {
                        try{

                            Map map = new HashMap();
                            AppVersionDTO dto = new AppVersionDTO();
                            dto.setOsType("apple");
                            dto.setImgId("1");
                            dto.setWidth("500");
                            dto.setHeight("400");
                            dto.setImgSrc("/src/img/logo.png");
                            dto.setStartDt("20210401");
                            dto.setEndDt("20211231");
                            map.put("appVersion",dto);

                            String key = getEntryName(metaSet)+":appLoad";
                            final ObjectMapper mapper = new ObjectMapper();
                            String categoryFiltersString = null;

                            try{
                                map.put("signGateGW", "61.250.20.26:9014");
                                categoryFiltersString = mapper.writeValueAsString(map);
                            }catch(JsonProcessingException e){
                                log.error("JsonProcessingException : ",e);
                            }
                            connection.set(
                                    RedisLoaderUtils.serialize(key,getRedisTemplate(redisType).getKeySerializer()),
                                    RedisLoaderUtils.serialize(categoryFiltersString,getRedisTemplate(redisType).getValueSerializer())
                            );
                        }catch (Exception e){
                            throw e;
                        }
                        return null;
                    }
        });

        int count = 0;
        for(final Object rs:results) {
            if (rs.getClass() == Boolean.class) {
                count++;
            }
        }

        return count;
    }
	
}
