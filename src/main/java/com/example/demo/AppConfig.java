package com.example.demo;

import javax.annotation.PostConstruct;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AppConfig {

    @Autowired
    private ServletWebServerApplicationContext server;
    
    private static FilterConfig filterConfig = new FilterConfig();
    
    @PostConstruct
    void init() {
    	//setting default port config
    	filterConfig.addNewPortConfig(8080, "/admin");
    }
    
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public FilterConfig createFilterConfig() {
    	return filterConfig;
    }
    
    public void addPort(String schema, String domain, int port, boolean secure) {
    	TomcatWebServer ts = (TomcatWebServer) server.getWebServer();
    	synchronized (this) {
    		ts.getTomcat().setConnector(createConnector(schema, domain, port, secure));
		}
    }
    
    public void addContextAllowed(FilterConfig filterConfig, int port, String context) {
    	filterConfig.addNewPortConfig(port, context);
    }
    
    private Connector createConnector(String schema, String domain, int port, boolean secure) {
		Connector conn = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		conn.setScheme(schema);
		conn.setPort(port);
		conn.setSecure(true);
		conn.setDomain(domain);
		if (secure) {
		    // config secure port...
		}
		return conn;
    }
    

}
