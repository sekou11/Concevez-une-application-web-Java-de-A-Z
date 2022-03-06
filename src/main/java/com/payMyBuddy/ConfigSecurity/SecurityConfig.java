package com.payMyBuddy.ConfigSecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource)
				.usersByUsernameQuery("select email AS username, password, 1 from user where email=?")
				.authoritiesByUsernameQuery("select email, role from user where email=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/signup", "/css/**", "/webjars/**", "/js/**").permitAll()
		                        .anyRequest().authenticated()
				                .and()
				                .formLogin().loginPage("/login")
				                 .usernameParameter("email").permitAll()
				                 .and()
				                 .rememberMe()
                                  .and()
                                  .logout().permitAll();

		                          http.formLogin().defaultSuccessUrl("/home", true);

		                           http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}
}
