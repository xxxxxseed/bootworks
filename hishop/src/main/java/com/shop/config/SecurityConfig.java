package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//인증 처리(로그인과 로그아웃)
				http.formLogin()
					.loginPage("/members/login")
					.defaultSuccessUrl("/")
					.usernameParameter("email")
					.failureUrl("/members/login/error")
					.and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
					.logoutSuccessUrl("/");
				
				//권한 설정
				http.authorizeRequests()
					.mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
					.mvcMatchers("/", "/members/**", "/item/**", "/images/**", "/board/**").permitAll()
					.mvcMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated();
				
				//ajax 핸들링
				http.exceptionHandling()
					.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		return http.build();
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { //인증
	 * 처리(로그인과 로그아웃) http.formLogin() .loginPage("/members/login")
	 * .defaultSuccessUrl("/") .usernameParameter("email")
	 * .failureUrl("/members/login/error") .and() .logout()
	 * .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
	 * .logoutSuccessUrl("/");
	 * 
	 * //권한 설정 http.authorizeRequests() .antMatchers("/", "/members/**", "/item/**",
	 * "/images/**").permitAll() //인증되지 않은 모든 사용자 접근
	 * .antMatchers("/admin/**").hasRole("ADMIN");
	 * 
	 * //ajax 핸들링 http.exceptionHandling() .authenticationEntryPoint(new
	 * CustomAuthenticationEntryPoint()); }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
