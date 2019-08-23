package com.example.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

public class NewPortFilter {

    @Bean(name = "restrictFilter")
    public FilterRegistrationBean retstrictFilter(FilterRegistrationBean javaMelodyFilter) {
	Filter filter = new OncePerRequestFilter() {

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		    FilterChain filterChain) throws ServletException, IOException {

		if (request.getLocalPort() == AppConfig.getPort()) {
		    if (!request.getPathInfo().startsWith("/alternative")) {
			response.sendError(403);
		    } else {
			filterChain.doFilter(request, response);
		    }
		} else {
		    if (request.getPathInfo().startsWith("/alternative")) {
			response.sendError(403);
		    } else {
			filterChain.doFilter(request, response);
		    }
		}
	    }
	};
	FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	filterRegistrationBean.setFilter(filter);
	filterRegistrationBean.setOrder(-100);
	filterRegistrationBean.setName("restrictFilter");
	return filterRegistrationBean;
    }
}