package io.pt.springboot.bookmark.security;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.nativejdbc.Jdbc4NativeJdbcExtractor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	     http
	     .csrf().disable()
	     .headers().disable()
         .authorizeRequests()
         	 .antMatchers("/console/**").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin().and()
         .httpBasic();
	}

	@Autowired
	public void configureGlobal(DataSource ds, AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userName -> new UserDetails() {
			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}
			
			@Override
			public String getUsername() {
				return "admin";
			}
			
			@Override
			public String getPassword() {
				return "pass";
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return AuthorityUtils.createAuthorityList("ROLE_USER");
			}
		});
	}
}
