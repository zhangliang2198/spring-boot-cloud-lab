package com.zhen777.www.springmvc.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zhen777.www.springmvc.core.interceptor.FirstInterceptor;
import com.zhen777.www.springmvc.core.interceptor.SecondInterceptor;
import com.zhen777.www.springmvc.core.interceptor.ThirdInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public FirstInterceptor firstInterceptor() {
        return new FirstInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor() {
        return new SecondInterceptor();
    }

    @Bean
    public ThirdInterceptor thirdInterceptor() {
        return new ThirdInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ????????????
        registry.addInterceptor(this.firstInterceptor()).addPathPatterns("/**");
        // ????????????
        registry.addInterceptor(this.secondInterceptor()).addPathPatterns("/users/current_user");
        // ????????????
        registry.addInterceptor(this.thirdInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ServletRegistrationBean testServlet01() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new HttpServlet() {

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                logger.info("[doGet][uri: {}]", req.getRequestURI());
            }

        });
        servletRegistrationBean.setUrlMappings(Collections.singleton("/test/01"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean testFilter01() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new Filter() {

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                logger.info("[doFilter]");
                filterChain.doFilter(servletRequest, servletResponse);
            }

        });
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/test/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<?> testListener01() {
        return new ServletListenerRegistrationBean<>(new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("[contextInitialized]");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {

            }

        });
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // ??????????????? CORS ??????
//        registry.addMapping("/**") // ???????????? URL ????????????????????????
//                .allowedOrigins("*") // ????????????????????????
//                .allowCredentials(true) // ???????????? Cookie
//                .allowedMethods("*") // ?????????????????? Method
//                .allowedHeaders("*") // ?????????????????? Header
////                .exposedHeaders("*") // ?????????????????? Header
//                .maxAge(1800L); // ????????? 1800 ??????2 ??????
//    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // ?????? UrlBasedCorsConfigurationSource ?????????????????? CorsRegistry ?????????
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // ?????? CorsConfiguration ?????????????????? CorsRegistration ????????????
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*")); // ????????????????????????
        config.setAllowCredentials(true); // ???????????? Cookie
        config.addAllowedMethod("*"); // ?????????????????? Method
        config.setAllowedHeaders(Collections.singletonList("*")); // ?????????????????? Header
//        config.setExposedHeaders(Collections.singletonList("*")); // ?????????????????? Header
        config.setMaxAge(1800L); // ????????? 1800 ??????2 ??????
        source.registerCorsConfiguration("/**", config);
        // ?????? FilterRegistrationBean ??????
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(source)); // ?????? CorsFilter ?????????
        bean.setOrder(0); // ?????? order ?????????????????????????????????????????????????????????????????????
        return bean;
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // ?????? XML ???????????????
//        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
//        xmlBuilder.indentOutput(true);
//        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlBuilder.build()));
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // ?????? XML ???????????????
//        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
//        xmlBuilder.indentOutput(true);
//        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlBuilder.build()));
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // ?????? FastJsonHttpMessageConverter ??????
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // ????????? FastJson ??????
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset()); // ???????????????
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect); // ??????????????????
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // ??????????????? MediaType
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_JSON_UTF8));
        // ????????? converters ???
        converters.add(0, fastJsonHttpMessageConverter); // ???????????????????????????????????? MappingJackson2XmlHttpMessageConverter ??????
    }

}
