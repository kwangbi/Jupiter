package com.yang.jupiter.core.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
@ControllerAdvice("com.yang.jupiter.controller")
public class GlobalHtmlExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
    public String NoHandlerFoundException(Exception e, Model model, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("error", sw.toString());
        model.addAttribute("path", request.getRequestURI());
        model.addAttribute("message", e.getMessage());
        return "/error/4xx";
    }

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("error", sw.toString());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("path", request.getRequestURI());
        model.addAttribute("message", e.getMessage());
        return "/error/5xx";
    }	
}
