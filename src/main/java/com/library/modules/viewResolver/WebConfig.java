package com.library.modules.viewResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Bean
    public ViewResolver internalResourceViewResolver()
    {
        InternalResourceViewResolver bean=new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }
    @Bean
    public ViewResolver resourceBundleViewResolver()
    {
        ResourceBundleViewResolver bean =new ResourceBundleViewResolver();
        bean.setBasename("views");
        return bean;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/resources/");
    }
}
