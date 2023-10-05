package com.company.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration {

    private final JwtTokenFilter filter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        http.cors(config -> config.configurationSource(request -> corsConfiguration));
        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**",
                "/api/v1/sms/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/api-docs/**",
                "/swagger-ui.html").permitAll());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/api/v1/brigade/**",
                        "/api/v1/car/**",
                        "api/v1/doctor/**",
                        "/api/v1/region/**",
                        "api/v1/user/",
                        "/delete_user/{id}",
                        "/get_users"
                ).hasAuthority("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers("/api/v1/application/create",
                "/api/v1/application/change_status",
                "/list/get_all_applications",
                "/list/getByStatus/{status}",
                "/get_patient_applications").hasAuthority("DISPATCHER"));
        http.authorizeHttpRequests(request -> request.requestMatchers("/api/v1/application/patient_application").hasAuthority("USER"));
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
