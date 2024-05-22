package com.app.vp.wookiebooks.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration for api context enrolling and declaring a new data based on security for user
 * (by authentication and authorization)
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //we need UserDetailsService for creating authentication provider
    @Autowired
    private UserDetailsService userDetailsService; //can take info about user from db through DataSource

    @Autowired
    private JwtFilter jwtFilter;

    //list of endpoints permitted
    private static final String[] WHITELIST = {
            "/api/user/**",
            "/api/token/**",
            "/api/authors/**"
    };


    @Bean //configuring chain for http request as security among app context
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()

                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/wookie_books/**")
                .permitAll()
                .requestMatchers(WHITELIST) //open for this urls
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider()) //from @Bean authenticationProvider()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //creating bean for authentication provider (responsible to take all inform for providing authentication)
    //so basically for we check matching username and password  we are using that authenticating provider

    /**
     * AuthenticationProvider responsible for check matching username and password that we paste by Postman
     * in request; and this data compare with db data for existing user
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //for authProvider we need to specify two properties:
        authProvider.setUserDetailsService(userDetailsService);//1)logic based on how we take info for user (in this class)
        authProvider.setPasswordEncoder(passwordEncoder()); //2)logic of how to encode password
        return authProvider;
    }

    /**
     * @Bean for encrypting password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}
