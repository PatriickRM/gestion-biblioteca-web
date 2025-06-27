package com.webapp.biblioteca.springboot_webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.webapp.biblioteca.springboot_webapp.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    // Constructor para inyectar dependencias
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    // Configuración del DaoAuthenticationProvider
      @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // Configuración de la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form
                .loginPage("/login")                         
                .loginProcessingUrl("/api/auth/login")       
                .defaultSuccessUrl("/index", true)  
                .failureUrl("/login?error=true")  
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/css/**", "/js/**", "/images/**", "/webjars/**", "/login","/usuarios/registro").permitAll()
                .requestMatchers("/usuarios/**","/libros/**").hasRole("ADMIN")
                .requestMatchers("/lector/**").hasRole("LECTOR")
                .anyRequest().authenticated()
            );

        return http.build();
    }
}