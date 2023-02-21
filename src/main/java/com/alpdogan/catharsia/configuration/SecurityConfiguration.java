package com.alpdogan.catharsia.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
     */

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    try {
                        auth.antMatchers("/home").permitAll()

                                .antMatchers("/register").permitAll()

                                .antMatchers("/swagger-ui/**").hasRole("ADMIN")

                                .antMatchers("/categories/**").hasRole("ADMIN")

                                .antMatchers("/comments/").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/comments/newComment").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/comments/addComment").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/comments/details").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/comments/updateComment").hasAnyRole("ADMIN", "USER")
                                // do we let users to delete comments?
                                .antMatchers("/comments/deleteComment").hasRole("ADMIN")

                                .antMatchers("/likes/").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/likes/newLike").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/likes/addLike").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/likes/updateLike").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/likes/deleteLike").hasRole("ADMIN")

                                .antMatchers("/topics/").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/topics/newTopic").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/topics/addTopic").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/topics/details").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/topics/updateTopic").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/topics/deleteTopic").hasRole("ADMIN")

                                .antMatchers("/users/").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/users/newUser").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/therapists/addUser").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/therapists/details").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/therapists/updateUser").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/therapists/deleteUser").hasRole("ADMIN")

                                .antMatchers("/dashboard").authenticated()
                                .and().formLogin().loginPage("/login")
                                            .usernameParameter("email")
                                            .defaultSuccessUrl("/dashboard")
                                            .failureUrl("/login?error=true").permitAll()
                                .and()
                                .logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(withDefaults())
                .build();

    }

}
