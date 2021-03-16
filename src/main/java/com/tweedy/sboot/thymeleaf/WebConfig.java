package com.tweedy.sboot.thymeleaf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // @Override
    // public void addViewControllers(ViewControllerRegistry registry) {
    //     registry.addViewController("/").setViewName("home");
    // }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/emplpics/**")
            //.addResourceLocations("file:/C:/Users/Voja/Desktop/cmsshoppingcart/src/main/resources/static/media/");
            .addResourceLocations("file:/D:/JavaProjects/attendance-tracking-v2/src/main/resources/static/emplpics/");
                                         
        
    }
    
}