package com.example.demo;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private static int newPort = 8181;
    
    public static int getPort() {
	return newPort;
    }
    
    @Autowired
    ServletWebServerApplicationContext server;

    @Bean
    public void tomcatCustomizer() {
	TomcatWebServer ts = (TomcatWebServer) server.getWebServer();
	addPort(ts.getTomcat(), "http", "localhost", newPort, false);
    }

    public void addPort(Tomcat tomcat, String schema, String domain, int port, boolean secure) {
	tomcat.setConnector(createConnector(schema, domain, port, secure));
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
