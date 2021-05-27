package com.yang.jupiter.core.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.yang.jupiter.core.constants.CommonConstants;

import lombok.AllArgsConstructor;

@EnableCaching
@Configuration
@AllArgsConstructor
public class CacheConfiguration extends CachingConfigurerSupport {

	final RedisConnectionFactory redisConnectionFactory;

	@Bean("cacheManager")
	public CacheManager redisCacheManager() {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeKeysWith(
						RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()))
						.prefixCacheNameWith(CommonConstants.PREFIX_CACHE_NAME_WITH)
						.entryTtl(Duration.ofSeconds(CommonConstants.DEFAULT_EXPIRE_SEC));

		RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
		return redisCacheManager;
	}
	
	@Bean("customKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}	
}
