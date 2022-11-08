package com.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${uploadPath}")
	String uploadPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//웹 브라우저 url에 /images로 시작하는 경우 uploadPath에 설정한 폴더를
		//기준으로 파일 읽어오도록 설정함
		registry.addResourceHandler("/images/**")
				.addResourceLocations(uploadPath);
	}
	
	
}
