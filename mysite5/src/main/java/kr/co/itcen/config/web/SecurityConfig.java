package kr.co.itcen.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.co.itcen.mysite.security.AuthInterceptor;
import kr.co.itcen.mysite.security.AuthUserHandlerMethodArgumentResolver;
import kr.co.itcen.mysite.security.LoginInterceptor;
import kr.co.itcen.mysite.security.LogoutInterceptor;

/*
 * 1. Interceptor 설정
 * 2. Argument Resolver 설정 
 * 3. 
 * 
 * 
 */

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter {

	//Argument Resolver
	@Bean
	public AuthUserHandlerMethodArgumentResolver argumentResolvers() {
	
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
		argumentResolvers.add(argumentResolvers());
	}
	
	//Interceptors
	@Bean
	public LoginInterceptor loginInterceptor() {
		
		return new LoginInterceptor();
	}
		
	@Bean
	public LogoutInterceptor logutInterceptor() {
		
		return new LogoutInterceptor();
	}
	
	@Bean
	public AuthInterceptor authInterceptor() {
		
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(loginInterceptor())
		        .addPathPatterns("/user/auth");

		registry.addInterceptor(logutInterceptor())
        		.addPathPatterns("/user/logout");
		
		registry.addInterceptor(authInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/user/auth")
				.excludePathPatterns("/user/logout")
				.excludePathPatterns("/assets/**");

	}
}