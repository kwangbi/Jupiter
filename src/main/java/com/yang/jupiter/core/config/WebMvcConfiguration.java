package com.yang.jupiter.core.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yang.jupiter.core.masking.MaskAuthInterceptor;
import com.yang.jupiter.core.masking.MaskAuthStore;
import com.yang.jupiter.core.masking.MaskFilter;
import com.yang.jupiter.core.masking.MaskJsonSerializer;
import com.yang.jupiter.core.masking.serializer.LocalDateSerializer;
import com.yang.jupiter.core.masking.serializer.LocalDateTimeSerializer;
import com.yang.jupiter.core.masking.serializer.YearMonthSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// masking Interceptor 추가
		registry.addInterceptor(new MaskAuthInterceptor());
	}

	/**
	 * 
	* 업무명 : masking mapper convert
	* 최초작성자 : P042360
	* 최초작성일 : 2021. 4. 29.
	* @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

		MaskAuthStore store = new MaskAuthStore() {
			@Override
			public boolean hasAuth() {
				Boolean result = (Boolean) RequestContextHolder.getRequestAttributes().getAttribute(MaskFilter.KEY,
						RequestAttributes.SCOPE_REQUEST);
				return result != null && result;
			}
		};

		ObjectMapper masterMapper = new ObjectMapper();
		ObjectMapper slaveMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(String.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(int.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(float.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(long.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(double.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(Integer.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(Float.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(Double.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(Long.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(Number.class, new MaskJsonSerializer<>(slaveMapper, store));
		module.addSerializer(LocalDate.class, new LocalDateSerializer(slaveMapper, store));
		module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(slaveMapper, store));
		module.addSerializer(YearMonth.class, new YearMonthSerializer(slaveMapper, store));
		masterMapper.registerModule(module);

		jsonConverter.setObjectMapper(masterMapper);
		return jsonConverter;
	}

}
