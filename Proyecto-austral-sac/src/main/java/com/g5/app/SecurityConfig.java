package com.g5.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.g5.app.models.service.LoginServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new LoginServiceImpl();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.
		authorizeHttpRequests()
		.antMatchers("/assets/**","/login/**").permitAll()
		.antMatchers("/").hasAnyAuthority("ADMIN","USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.usernameParameter("email")
		.passwordParameter("contraseña")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.permitAll();
		
		http.authenticationProvider(authenticationProvider());
        return http.build();
	}
	
	

}
