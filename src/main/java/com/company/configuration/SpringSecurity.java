//package dev.egov.apimonitoring.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableMethodSecurity
//public class SpringSecurity {
//
//    //private final JwtAuthenticationFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//        requestCache.setMatchingRequestParameterName(null);
////        http
////                .csrf().disable()
////                .authorizeHttpRequests()
////                .anyRequest().permitAll();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        corsConfiguration.setAllowedOrigins(List.of("*"));
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//
//        http.exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
//        http.cors(config -> config.configurationSource(request -> corsConfiguration));
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(request -> request.requestMatchers(
//                        "/api/v1/auth/**",
//                        "/v2/api-docs",
//                        "/v3/api-docs",
//                        "/v3/api-docs/**",
//                        "/swagger-resources",
//                        "/swagger-resources/**",
//                        "/configuration/ui",
//                        "/configuration/security",
//                        "/swagger-ui/**",
//                        "/webjars/**",
//                        "/api-docs/**",
//                        "/swagger-ui.html"
//                )
//                .permitAll());
//        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/api/v1/user", "/api/v1/user/signup", "/api/v1/api").hasAnyRole("MANAGER", "ADMIN"));
//        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/api/v1/user/password", "/api/v1/result").hasAnyRole("USER", "MANAGER", "ADMIN"));
//        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/api/v1/user/all", "/api/v1/user/history/**").hasAnyRole("MANAGER", "ADMIN"));
//        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.PUT, "/api/v1/user/**", "/api/v1/api/**").hasAnyRole("MANAGER", "ADMIN"));
//        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE, "/api/v1/api/**").hasAnyRole("ADMIN"));
//        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE, "/api/v1/user/session/**").hasAnyRole("USER", "MANAGER", "ADMIN"));
//        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/api/v1/user", "/api/v1/user/**", "/api/v1/api", "/api/v1/api/**").hasAnyRole("USER", "MANAGER", "ADMIN"));
//        http.authorizeHttpRequests(request -> request.requestMatchers("/api/v1/user/sign-in").permitAll());
//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        http.logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
//                .addLogoutHandler(logoutHandler)
//                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
//
//        return http.build();
//    }
//
//}
