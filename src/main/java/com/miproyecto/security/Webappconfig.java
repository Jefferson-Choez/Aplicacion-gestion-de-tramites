package com.miproyecto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Webappconfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService userDetailsService;

        private PasswordEncoder passwordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeRequests(requests -> requests
                                                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(login -> login
                                                .loginPage("./Inicio-sesion")
                                                .defaultSuccessUrl("./Panel-usuario")
                                                .permitAll())
                                .logout(logout -> logout
                                                .permitAll());
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService)
                                .passwordEncoder(passwordEncoder);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
