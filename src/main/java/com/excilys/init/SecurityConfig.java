package com.excilys.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("123456")
                .roles("USER");
        auth.inMemoryAuthentication().withUser("root").password("123456")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //.httpBasic() => Basir for dialogue box login
                //.and()
                    .authorizeRequests()
                        .antMatchers("/admin").access("hasRole('ADMIN')")
                        .antMatchers("/") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/dashboard") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/editComputer") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/editComputerForm") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/addComputer") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/addComputerForm") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/deleteComputer") .access("hasRole('ADMIN') or hasRole('USER')")
                .and()
                    .formLogin().loginPage("/login").permitAll()
                .and()
                    .csrf().disable();
    }
}