package com.example.userservice.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    private static final String[] WHITE_LIST = {
            "/users/**"
    };

    //권한 관련
    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable(); //h2-console 화면 정상 작동
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/user-service/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
        );
        return http.build();
    }
}
