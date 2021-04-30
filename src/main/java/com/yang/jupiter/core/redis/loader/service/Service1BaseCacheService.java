package com.yang.jupiter.core.redis.loader.service;

import javax.annotation.Resource;

import com.yang.jupiter.core.exception.BusinessException;
import com.yang.jupiter.core.redis.CacheConstants.RedisType;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class Service1BaseCacheService extends BaseLoaderService {

    @Resource(name = "service1RedisTemplate")
    private RedisTemplate<String, Object> template;

    @Override
    public RedisTemplate<String, Object> getRedisTemplate(final RedisType redisType) {
        return this.template;
    }
    @Override
    public int createKeysWithEntryName(final String metaSet) throws BusinessException {
        return createKeysWithEntryName(metaSet, RedisType.RS1);
    }

    @Override
    public int modifyKeysByEntryName(final String metaSet, final Boolean reset) throws BusinessException {
        if (reset) {
            deleteKeysByEntryName(metaSet, RedisType.RS1);
        }
        return createKeysWithEntryName(metaSet, RedisType.RS1);
    }

    @Override
    public int modifyKeyByEntryNameAndId(final String metaSet, final String id) throws BusinessException {
        return modifyKeyByEntryNameAndId(metaSet, id, RedisType.RS1);
    }

    @Override
    public Object getKeysByEntryName(final String metaSet) {
        return getKeysByEntryName(metaSet, RedisType.RS1);
    };

    @Override
    public <T> T getKeysByEntryName(final String metaSet, final Class<T> clazz) {
        return getKeysByEntryName(metaSet, clazz, RedisType.RS1);
    }

    @Override
    public Object getKeyByEntryNameAndId(final String metaSet, final String id) {
        return getKeyByEntryNameAndId(metaSet, id, RedisType.RS1);
    }

    @Override
    public <T> T getKeyByEntryNameAndId(final String metaSet, final String id, final Class<T> clazz) {
        return getKeyByEntryNameAndId(metaSet, id, clazz, RedisType.RS1);
    }

    @Override
    public Boolean deleteKeysByEntryName(final String metaSet) {
        return deleteKeysByEntryName(metaSet, RedisType.RS1);
    }

    @Override
    public Boolean deleteKeyByEntryNameAndId(final String metaSet, final String id) {
        return deleteKeyByEntryNameAndId(metaSet, id, RedisType.RS1);
    }

}
