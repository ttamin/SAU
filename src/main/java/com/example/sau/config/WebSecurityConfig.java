package com.example.sau.config;

import com.example.sau.config.service.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;

import static com.example.sau.constanta.UserRolesConstants.ADMIN;
import static com.example.sau.constanta.UserRolesConstants.USER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    private final List<String> RESOURCES_WHITE_LIST = List.of("/resources/**",
            "/static/**", "/js/**, /css/**","/");

    private final List<String> HOME_WHITE_LIST = List.of("/home/**");
    private final List<String> ADMIN_WHITE_LIST = List.of("/admin/**");
    private final List<String> CART_WHITE_LIST = List.of("/cart/**");

    private final List<String> USER_WHITE_LIST = List.of("/login", "/users/register", "/users/forgot-pass");


    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                             CustomUserDetailsService customUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public HttpFirewall firewall(){
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        firewall.setAllowBackSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    }
    //localhost:8000/search/привет;%20weorewo%20 - принимает все это изза того что сверху написано

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors().disable()
//                .csrf().disable()
        http
                .authorizeHttpRequests((request)-> request
                                .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(HOME_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(USER_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(ADMIN_WHITE_LIST.toArray(String[]::new)).hasRole(ADMIN)
                                .requestMatchers(CART_WHITE_LIST.toArray(String[]::new)).hasRole(USER)
                                .anyRequest().authenticated()
                        )
                .formLogin((form)->form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout((logout)->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );

        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
