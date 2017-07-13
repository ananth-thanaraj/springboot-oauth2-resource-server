package com.ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

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
		tokens.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
		tokens.setClientId("my-client");
		tokens.setClientSecret("password");
		return tokens;
	}


	
}
	
