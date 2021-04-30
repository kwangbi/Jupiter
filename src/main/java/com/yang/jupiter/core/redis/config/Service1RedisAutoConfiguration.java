package com.yang.jupiter.core.redis.config;

import com.yang.jupiter.core.redis.CacheConstants;
import com.yang.jupiter.core.redis.util.RedisLoaderUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class Service1RedisAutoConfiguration extends BaseRedisConfiguration {

    @Bean("Service1RedisProperties")
    @ConditionalOnMissingBean(name = "Service1RedisProperties")
    @ConfigurationProperties(prefix = CacheConstants.PREFIX_SERVICE1_REDIS_PROPERTIES)
    public RedisProperties redisProperties() {
        return new RedisProperties();
    };


    @Override
    @Bean("service1ConnectionFactory")
    @DependsOn(value = {"Service1RedisProperties"})
    @ConditionalOnMissingBean(name = "service1ConnectionFactory")
    public LettuceConnectionFactory redisConnectionFactory(@Qualifier("Service1RedisProperties") final RedisProperties properties) {
        return createLettuceConnectionFactory(lettuceClientResources(), properties);
    }

    @Override
    @Bean("service1RedisTemplate")
    @DependsOn(value = {"service1ConnectionFactory"})
    @ConditionalOnMissingBean(name = "service1RedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("service1ConnectionFactory") final RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisLoaderUtils.redisTemplateKeySerializer(CacheConstants.RedisSerializerTypes.String, template);
        RedisLoaderUtils.redisTemplateValueSerializer(CacheConstants.RedisSerializerTypes.GenericJackson2Json, template);
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
