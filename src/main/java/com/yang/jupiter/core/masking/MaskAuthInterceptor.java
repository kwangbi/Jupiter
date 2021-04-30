package com.yang.jupiter.core.masking;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaskAuthInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {

		log.info("MaskAuthInterceptor");
		
		Enumeration<String> header = request.getHeaders(MaskFilter.KEY);
		
		if(header == null) {
			log.info("header is null");
		}
		
		if(header.hasMoreElements()) {
			log.info("header hasMoreElements true");
		}
		
		if(header != null && header.hasMoreElements()) {
			String masked = header.nextElement();
			log.info("masked: {}", masked);

			RequestContextHolder.getRequestAttributes().setAttribute(MaskFilter.KEY, Boolean.valueOf(masked), RequestAttributes.SCOPE_REQUEST);
		}
		
		
		return true;
	}	

}
