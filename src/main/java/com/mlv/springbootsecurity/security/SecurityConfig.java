package com.mlv.springbootsecurity.security;

import com.mlv.springbootsecurity.service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Authentication method
    @Bean
    public UserDetailsService userDetailsService(){

//        UserDetails Admin= User.withUsername("mano")
//                .password(encoder.encode("mano"))
//                .roles("ADMIN")
//                .build();
//        UserDetails Users= User.withUsername("rajini")
//                .password(encoder.encode("rajini"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(Admin,Users);
       return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return  http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/hello","/user/add")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/**")
                .authenticated()
                .and().formLogin().and().build();
    }

    //Password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
