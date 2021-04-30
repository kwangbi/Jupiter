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
public class Service2RedisAutoConfiguration extends BaseRedisConfiguration {

    @Bean("Service2RedisProperties")
    @ConditionalOnMissingBean(name = "Service2RedisProperties")
    @ConfigurationProperties(prefix = CacheConstants.PREFIX_SERVICE2_REDIS_PROPERTIES)
    public RedisProperties redisProperties() {
        return new RedisProperties();
    };


    @Override
    @Bean("service2ConnectionFactory")
    @DependsOn(value = {"Service2RedisProperties"})
    @ConditionalOnMissingBean(name = "service2ConnectionFactory")
    public LettuceConnectionFactory redisConnectionFactory(@Qualifier("Service2RedisProperties") final RedisProperties properties) {
        return createLettuceConnectionFactory(lettuceClientResources(), properties);
    }

    @Override
    @Bean("service2RedisTemplate")
    @DependsOn(value = {"service2ConnectionFactory"})
    @ConditionalOnMissingBean(name = "service2RedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("service2ConnectionFactory") final RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisLoaderUtils.redisTemplateKeySerializer(CacheConstants.RedisSerializerTypes.String, template);
        RedisLoaderUtils.redisTemplateValueSerializer(CacheConstants.RedisSerializerTypes.GenericJackson2Json, template);
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
