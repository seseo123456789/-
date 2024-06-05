package com.green.Team3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private LoginFailHandler loginFailHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    c -> {
                        c.requestMatchers(
                            new AntPathRequestMatcher("/"),
                            new AntPathRequestMatcher("/member/joinForm"),
                            new AntPathRequestMatcher("/member/join"),
                            new AntPathRequestMatcher("/member/findPasswordForm"),
                            new AntPathRequestMatcher("/member/findPwFetch"),
                            new AntPathRequestMatcher("/member/findIdForm"),
                            new AntPathRequestMatcher("/member/findIdFetch"),
                            new AntPathRequestMatcher("/member/idCheckFetch")
                            ).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin")).hasRole("3")
                                .requestMatchers(new AntPathRequestMatcher("/teacher")).hasRole("2")
                                .requestMatchers(new AntPathRequestMatcher("/board")).hasAnyRole("1","2","3")
                                .requestMatchers(new AntPathRequestMatcher("/reply")).hasAnyRole("1","2","3")
                                .requestMatchers(new AntPathRequestMatcher("/class")).hasRole("2")
                                .anyRequest().authenticated();
                    }

                ).formLogin(
                        formLogin -> {
                            formLogin.loginPage("/")
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    .loginProcessingUrl("/member/login")
//                                    .defaultSuccessUrl("/")
                                    .failureUrl("/")
                                    .successHandler(loginSuccessHandler)
                                    .failureHandler(loginFailHandler);
                        }
                ).logout(
                        logout -> {
                            logout.logoutUrl("/member/logout")
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true);
                        }
                ).exceptionHandling(
                        ex ->{
                            ex.accessDeniedPage("/deny");
                        }
                );
        return security.build();
    }
    @Bean
    public WebSecurityCustomizer customizer(){
        return web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/images/**"),
                new AntPathRequestMatcher("/favicon.ico"),
                new AntPathRequestMatcher("/error")
        );
    }
}
