package com.nicolas.belt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(Customizer.withDefaults())
				.authorizeHttpRequests((request) -> {
					request
							.requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
							.requestMatchers(new AntPathRequestMatcher("/dashboard/**")).authenticated()
							.anyRequest()
							.permitAll();
				})
				.formLogin((login -> {
					login
							.loginPage("/login")
							.usernameParameter("email")
							.defaultSuccessUrl("/dashboard")
							.failureUrl("/login?error")
							.permitAll();
				}))
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
						.clearAuthentication(true).deleteCookies("JSESSIONID"))
						;

		return http.build();
	}
}
