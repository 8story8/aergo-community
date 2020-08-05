package io.blocko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.blocko.auth.UsernamePasswordValidationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UsernamePasswordValidationFilter usernamePasswordValidationFilter() throws Exception {
		final UsernamePasswordValidationFilter filter = new UsernamePasswordValidationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		return filter;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/template/**", "/js/**", "/upload/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/user/register", "/board/register").permitAll().anyRequest()
				.authenticated();
		http.addFilterBefore(usernamePasswordValidationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.formLogin().loginPage("/").loginProcessingUrl("/user/login").defaultSuccessUrl("/main")
				.usernameParameter("email").passwordParameter("password").and().httpBasic();

	}
}
