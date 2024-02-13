package by.itacademy.user.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(AbstractHttpConfigurer::disable)

                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(httpSSMConfigurer -> httpSSMConfigurer.sessionCreationPolicy(STATELESS))

                .exceptionHandling(httpSEHConf
                        -> httpSEHConf.authenticationEntryPoint((request, response, authException)
                        -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))
                .exceptionHandling(httpSEHConf
                        -> httpSEHConf.accessDeniedHandler((request, response, accessDeniedException)
                        -> response.setStatus(HttpServletResponse.SC_FORBIDDEN)))

                .authorizeHttpRequests(authManagerReqMatcherReg -> authManagerReqMatcherReg

                        .requestMatchers(POST, "/api/v1/users/registration").permitAll()
                        .requestMatchers(POST, "/api/v1/users/login").permitAll()
                        .requestMatchers(GET, "/api/v1/users/verification").permitAll()
                        .requestMatchers(GET, "/api/v1/users/me").authenticated()
                        .requestMatchers(POST, "/api/v1/users/send-password-restore-link").permitAll()
                        .requestMatchers(POST, "/api/v1/users/update-password").permitAll()
                        .requestMatchers(GET, "/api/v1/users").hasRole("ADMIN")
                        .requestMatchers(POST, "/api/v1/users").hasRole("ADMIN")
                        .requestMatchers(PUT, "/api/v1/users/{id}/dt_update").hasRole("ADMIN")
                        .anyRequest().authenticated())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
