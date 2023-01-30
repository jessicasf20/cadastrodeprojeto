package ifrn.pi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
public class ProjetoSecurityConfig extends WebSecurityConfiguration {
	
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeHttpRequests()
				.anyRequest().authenticated();
		
		
	}

}
