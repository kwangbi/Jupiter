package com.yang.jupiter.core.redis.cache.service;

import javax.annotation.Resource;

import com.yang.jupiter.core.redis.CacheConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommonCacheService extends BaseCacheService {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> template;

    @Override
    public RedisTemplate<String, Object> getRedisTemplate(final CacheConstants.RedisType redisType) {
        return this.template;
    }

    @Override
    public Object getKeysByEntryName(final String metaSet) {
        return getKeysByEntryName(metaSet, CacheConstants.RedisType.RS1);
    };

    @Override
    public <T> T getKeysByEntryName(final String metaSet, final Class<T> clazz) {
        return getKeysByEntryName(metaSet, clazz, CacheConstants.RedisType.RS1);
    };

    @Override
    public Object getKeyByEntryNameAndId(final String metaSet, final String id) {
        return getKeyByEntryNameAndId(metaSet, id, CacheConstants.RedisType.RS1);
    };

    @Override
    public <T> T getKeyByEntryNameAndId(final String metaSet, final String id, final Class<T> clazz) {
        return getKeyByEntryNameAndId(metaSet, id, clazz, CacheConstants.RedisType.RS1);
    };
}
