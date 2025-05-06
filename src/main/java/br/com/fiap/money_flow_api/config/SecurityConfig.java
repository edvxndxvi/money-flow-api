package br.com.fiap.money_flow_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/transactions/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                    .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // Desabilitando filtro contra CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Com isso, o usuário não fica logado
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // @Bean // Fornecedor de Objetos para Spring
    // UserDetailsService userDetailsService() {
    //     return new InMemoryUserDetailsManager(List.of(
    //         User
    //             .withUsername("ed")
    //             .password("$2a$12$EOog9UAWg1JPorkwGFIlEuWQcWL9huCM8u0Crm9fG9dXhV64JunWS")
    //             .roles("ADMIN")
    //             .build(), 
    //         User
    //             .withUsername("maria")
    //             .password("$2a$12$wIkv1JZfPulhdJdiW7suiuNMjrJ13hs2T2lUHiJuJtidev1IA44MG")
    //             .roles("USER")
    //             .build()
    //     ));
    // }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
