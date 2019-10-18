package kr.co.itcen.mysite.config;



import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import kr.co.itcen.mysite.security.AuthInterceptor;
import kr.co.itcen.mysite.security.AuthUserHandlerMethodArgumentResolver;
import kr.co.itcen.mysite.security.LoginInterceptor;
import kr.co.itcen.mysite.security.LogoutInterceptor;


@Configuration
//@EnableAspectJAutoProxy
//@ComponentScan({"kr.co.itcen.mysite.controller"})
//@ImportResource({ "classpath:경로/Config.xml" })
//@Import({MVCConfig.class, SecurityConfig.class})
public class WebConfig implements WebMvcConfigurer{
	
	   // Default Servlet Handler
	   @Override
	   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {// override/implement Method
	                                                                     						// configureDefaultServletHandling
	      // TODO Auto-generated method stub
	      configurer.enable();
	   }
	   
	  @Bean // Message Converter
	   public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {// ParameterNamesModule()을 사용하기 위해서는 pom.xml에
	                                                                  // Jackson-module-parameter-names를 추가해줘야 한다
	      Jackson2ObjectMapperBuilder bulider = new Jackson2ObjectMapperBuilder().indentOutput(true)
	            .dateFormat(new SimpleDateFormat("yyyy-mm-dd")).modulesToInstall(new ParameterNamesModule());
	      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(bulider.build());
	      converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8"))));
	      return converter;
	   }
	   
	   //simpleHttp
	   @Bean
	   public StringHttpMessageConverter stringHttpMessageConverter() {
	      StringHttpMessageConverter converter = new StringHttpMessageConverter();
	      converter.setSupportedMediaTypes(
	         Arrays.asList(
	            new MediaType("text", "html", Charset.forName("UTF-8"))
	         )
	      );
	      
	      return converter;
	   }
	   
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