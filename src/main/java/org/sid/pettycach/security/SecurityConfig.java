package org.sid.pettycach.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 
	    
	    
	
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.authorizeRequests()
    	.antMatchers("/showNewUserForm").hasRole("ADMIN")
        .antMatchers("/css/**", "/app-assets/**","/src/**","/star-klit/**","/gulp-tasks/**","/js/**", "/expensehead","/registration","/dashboard","/narration","/new","/receivers","/expensehead","/accounts").permitAll()
        .anyRequest().authenticated()
        .and()
    .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
    .logout()
    	.invalidateHttpSession(true)
    	.clearAuthentication(true)
    	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    	.logoutSuccessUrl("/login?logout")
        .permitAll();
    }
    	
    	
       
    

@Bean
public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
}

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//encode password of user which was returned by userdetailsService
	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
}

}