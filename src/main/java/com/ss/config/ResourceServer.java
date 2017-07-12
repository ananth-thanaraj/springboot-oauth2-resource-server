package com.ss.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.ss.model.CustomClientResourceDetails;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter{	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenService());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
		.requestMatchers()
		.and()
		.authorizeRequests()
		/* Client based access restriction
		 .antMatchers("/studs/home/").access("#oauth2.clientHasRole('user')")
		.antMatchers("/studs/public/**").access("#oauth2.clientHasRole('admin')")*/
		.antMatchers("/studs/home/").hasAnyAuthority("user")
		.antMatchers("/studs/public/**").hasAnyAuthority("admin")
		.anyRequest()
		.authenticated();
	}
	
	@Primary
	@Bean
	public RemoteTokenServices tokenService(){
		RemoteTokenServices tokens = new RemoteTokenServices();
		tokens.setCheckTokenEndpointUrl("http://localhost:8080/auth/oauth/check_token");
		tokens.setClientId("my-client");
		tokens.setClientSecret("password");
		return tokens;
	}


	
}
	
