package com.zeynep.casestudy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity//Add this annotation to an @Configuration class to have the Spring Security configuration defined in any WebSecurityConfigurer or more likely by exposing a SecurityFilterChain bean:
@EnableMethodSecurity
public class SecurityConfig {

    /* Configuring HttpSecurity
    we are providing The Authorization

    Authorization gives those users(principal, teachers) permission to access a resource(principal room, staff room).

    For Authorization, we need to get hold of HttpSecurity
    we need to configure SecurityFilterChain thanks to a method which takes HttpSecurity as a parameter

    We need to map a path to a role, for that, we have used requestMatchers() method. We will go from most restrictive to least restrictive.
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())//Here we're using the httpBasic() element to define Basic Authentication inside the SecurityFilterChain bean.
                .build();

    }

    /*
     ** we are providing The Authentication **
     *    Authentication confirms that users(principal, teachers, and students) are who they say they are.    *
     The InMemoryUserDetailsManager provides management of UserDetails by implementing the UserDetailsManager interface.
     UserDetails-based authentication is used by Spring Security when it is configured to accept a username and password for authentication.

     we have created two users and stored them in the InMemoryUserDetailsManager class object.

      we have used Spring Security’s InMemoryUserDetailsManager class which implements UserDetailsService to provide support for username/password-based authentication that is stored in memory.
    */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails zeynep = User.builder().username("zeynep").password(passwordEncoder().encode("zeynep@123")).roles("USER")
                .build();

        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(zeynep,admin);
        /*

        Non-persistent implementation of UserDetailsManager which is backed by an in-memory map.
        Mainly intended for testing and demonstration purposes, where a full blown persistent system isn't required.
        An extension of the UserDetailsService which provides the ability to create new users and update existing ones.

        public interface UserDetailsManager extends UserDetailsService
         --> An extension of the UserDetailsService which provides the ability to create new users and update existing ones.
         create update delete user .. methods

         public interface UserDetailsService
         --> Core interface which loads user-specific data.
         It is used throughout the framework as a user DAO and is the strategy used by the DaoAuthenticationProvider.
         It has only one method -> UserDetails loadUserByUsername(String username)
         */

    }

    /*
    Password Encoding
    we are using PasswordEncoder to encode the password.
    Spring Security’s PasswordEncoder interface is used to perform a one-way transformation of a password to let the password be stored securely.
    We are using BCryptPasswordEncoder class which implements the PasswordEncoder interface.
    The BCryptPasswordEncoder class implementation uses the widely supported bcrypt algorithm to hash the passwords.
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
