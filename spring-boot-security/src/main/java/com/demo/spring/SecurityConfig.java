package com.demo.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/status").access("hasRole('ROLE_ADMIN')")                
                .antMatchers(HttpMethod.POST, "/login", "/logout").permitAll()                 
                // NOTE if did not match any other rule, then nothing is done (grant access)
                // if want deny by default do
                // .anyRequest().denyAll()
                .antMatchers("/welcome", "/getaccount").permitAll()
                .antMatchers("/static/**").anonymous()
                .antMatchers("/actuator/**").authenticated()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("admin")
                .and()
                .formLogin()
                
                // .and().httpBasic()
                .and().formLogin().and().csrf().disable();
    }

    @Autowired
    // Added @Autowired to use customized AuthenticationManagerBuilder which created via @Beans
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("user").password(encoder().encode("welcome1")).roles("USER");

        auth.inMemoryAuthentication().withUser("peter").password("welcome1").roles("USER");

        auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("welcome1")).roles("ADMIN");

        auth.inMemoryAuthentication().withUser("john").password(encoder().encode("welcome1")).roles("USER")
                .disabled(true); // Disable true reject the user to login

    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
