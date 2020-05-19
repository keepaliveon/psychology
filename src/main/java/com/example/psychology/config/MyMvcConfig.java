package com.example.psychology.config;

import com.example.psychology.web.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // /**  表示拦截所有路径下的所有请求
                registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns("/static/**");
            }
        };
    }

}
