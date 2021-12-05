package com.example.ohjelmistoprojekti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ohjelmistoprojekti.security.services.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailServiceImpl userDetailsService;

//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/apiadminusers").authenticated().antMatchers("/**").permitAll();
//
//	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/apiadmin/**").hasAuthority("ADMIN").and()
				.formLogin().defaultSuccessUrl("/apisurveys", true).permitAll().and().logout().permitAll();
	}

//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/apiadmin/**").hasAuthority("ADMIN");
//	}

	// so if we were to change the urls so that all the adminpages start with admin
	// they're like admin/survey/1 etc
	// then we can just set this as admin/** and it should block all of those
	// endpoints if you're
	// not logged in as an admin user

	// okay how do we then add that those with admin role are allowed
	// cos that seems to mess this up every time

	// so issue is you can't call antMatchers after anyrequest
	// but if I put /** permit all first it permits everyone to all pages first
	// ie everyone to adminpages as well thus rendering the admin limitation useless

//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/apiusers/**").permitAll().antMatchers("/apiadminusers/**")
//				.hasRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login")
//				.defaultSuccessUrl("/apisurveys", true).permitAll().and().logout().permitAll()
//				.invalidateHttpSession(true); // Invalidate session;
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
