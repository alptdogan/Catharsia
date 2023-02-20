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
                        auth.antMatchers("/home").permitAll()

                                .antMatchers("/swagger-ui/**").hasAnyRole("ADMIN", "USER")

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
