package com.depromeet.mini_QR.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.depromeet.mini_QR", "com.depromeet.mini_QR.domain"})
@EnableAspectJAutoProxy
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers (ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}
	
//	@Override
//    public void addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry registry) {
//        // 404 오류가 발생했을때 보여줄 뷰
//        registry.addViewController("classpath:/META-INF/resources/html/**").setViewName("/404.html");
//    }
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
	    registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
	    registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
	    registry.addResourceHandler("/scss/**").addResourceLocations("/resources/scss/");
	    registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
	    registry.addResourceHandler("/resources/**")
        	.addResourceLocations("/WEB-INF/resources/");	    
	    
	    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");

	}
	
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}
}
