package ifrn.pi.projeto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ifrn.pi.projeto.service.ProjetoDetailsService;

@Configuration
@EnableWebSecurity
public class ProjetoSecurityConfig {
	
	@Autowired
	ProjetoDetailsService projetoDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("Executou");
		http
				.httpBasic()
				.and()
				.authorizeHttpRequests()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.and()
				.csrf().disable();
		
		return http.build();
	} 
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(projetoDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
