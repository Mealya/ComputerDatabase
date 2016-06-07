package com.excilys.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.excilys.model.User;
import com.excilys.service.UserService;

@Configuration 
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userServ;
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        // TODO for update : add roles in DB?
        for (User u : userServ.getAllUsers()) {
            if (u.getName().equals("root")) {
                auth.inMemoryAuthentication().withUser(u.getName()).password(u.getPassword())
                .roles("ADMIN");
            } else {
                auth.inMemoryAuthentication().withUser(u.getName()).password(u.getPassword())
                .roles("USER");
            }
        }   
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //.httpBasic() => Basic for dialogue box login
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
                        /*.antMatchers("/rest/") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/rest/dashboard") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/rest/editComputer") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/rest/editComputerForm") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/rest/addComputer") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/rest/addComputerForm") .access("hasRole('ADMIN') or hasRole('USER')")
                        .antMatchers("/rest/deleteComputer") .access("hasRole('ADMIN') or hasRole('USER')")*/
                .and()
                    .formLogin().loginPage("/login").permitAll()
                .and()
                    .csrf().disable();
    }

}