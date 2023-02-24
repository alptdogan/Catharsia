package com.alpdogan.catharsia.configuration.security;

import com.alpdogan.catharsia.configuration.security.jwt.AuthEntryPointJwt;
import com.alpdogan.catharsia.configuration.security.jwt.AuthTokenFilter;
import com.alpdogan.catharsia.configuration.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /*
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()

                //.antMatchers("/swagger-ui/**").permitAll()

                //.antMatchers("/topics/**").permitAll()

                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
         */

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    try {
                        auth.antMatchers("/home").permitAll()

                                .antMatchers("/api/auth/**").permitAll()

                                .antMatchers("/register/**").permitAll()

                                .antMatchers("/swagger-ui/**").permitAll()

                                .antMatchers("/categories/**").permitAll()

                                .antMatchers("/comments/").permitAll()

                                .antMatchers("/topics/**").permitAll()

                                .antMatchers("/users/**").permitAll()

                                /*

                                .antMatchers("/comments/").hasAnyRole("ADMIN", "USER")

                                .antMatchers("/topics/").hasAnyRole("ADMIN", "USER")

                                .antMatchers("/users/").hasAnyRole("ADMIN", "USER")

                                 */

                                /*
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
                                .antMatchers("/users/displayUsers").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/users/newUser").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/users/addUser").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/users/details").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/users/updateUser").hasAnyRole("ADMIN", "USER")
                                .antMatchers("/users/deleteUser").hasRole("ADMIN")
                                */

                                .antMatchers("/dashboard").authenticated()
                                .and().formLogin().loginPage("/login")
                                .usernameParameter("username")
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
