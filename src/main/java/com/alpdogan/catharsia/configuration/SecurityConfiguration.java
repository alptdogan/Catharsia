package com.alpdogan.catharsia.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails therapist = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(therapist, admin);

    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    try {
                        auth.requestMatchers("/home").permitAll()

                                .requestMatchers("/swagger-ui/**").hasAnyRole("ADMIN", "USER")

                                .requestMatchers("/categories/**").hasRole("ADMIN")

                                .requestMatchers("/comments/").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/comments/newComment").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/comments/addComment").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/comments/details").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/comments/updateComment").hasAnyRole("ADMIN", "USER")
                                // do we let users to delete comments?
                                .requestMatchers("/comments/deleteComment").hasRole("ADMIN")

                                .requestMatchers("/likes/").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/likes/newLike").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/likes/addLike").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/likes/updateLike").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/likes/deleteLike").hasRole("ADMIN")

                                .requestMatchers("/topics/").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/topics/newTopic").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/topics/addTopic").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/topics/details").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/topics/updateTopic").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/topics/deleteTopic").hasRole("ADMIN")

                                .requestMatchers("/users/").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/users/newUser").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/therapists/addUser").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/therapists/details").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/therapists/updateUser").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/therapists/deleteUser").hasRole("ADMIN")

                                .requestMatchers("/dashboard").authenticated()
                                .and().formLogin().loginPage("/login")
                                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(withDefaults())
                .build();

    }

}
