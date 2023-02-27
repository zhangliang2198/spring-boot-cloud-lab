package com.zhen777.www.admin.demoapplication.byheader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 针对actuator接口做安全限制，只允许服务端调用
 */

@Component
public class ActuatorFilter extends OncePerRequestFilter {
    @Value("${server.admin.token}")
    private String token;

    @Value("${server.admin.token-name}")
    private String tokenName;

    private final static String ACTUATOR = "/actuator";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!token.equals(request.getHeader(tokenName))) {
            throw new RuntimeException("抱歉，你无权限访问，Actuator端口受保护！ Sorry, you have no permission to access it，Actuator port protected！");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return isExcludeUrl(request.getServletPath());
    }

    private boolean isExcludeUrl(String url) {
        if (url == null || "".equals(url)) {
            return true;
        }
        return !Pattern.compile(ACTUATOR).matcher(url).find();
    }
}
