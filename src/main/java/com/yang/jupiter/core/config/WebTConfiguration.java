package com.yang.jupiter.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "webt")
@Data
@ToString
public class WebTConfiguration {

    private String domain;
    private String apiKey;
}

