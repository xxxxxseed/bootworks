package com.boot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//직접 정의한 시큐리티 서비스
		//http.userDetailsService(userDetailsService);
		
		//인증 처리(로그인과 로그아웃)
		http.formLogin().loginPage("/member/login")
			.defaultSuccessUrl("/");
		
		http.logout().logoutUrl("/member/logout")
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.invalidateHttpSession(true)
			.logoutSuccessUrl("/");
		
		//권한 설정
		http.authorizeRequests()
			.antMatchers("/", "/member/**").permitAll()		//인증되지 않은 모든 사용자 접근
			.antMatchers("/admin/**").hasRole("ADMIN");
	}
	
	//비밀번호 암호화 객체 반환해줌
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	
}
