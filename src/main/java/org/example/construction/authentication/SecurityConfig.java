package org.example.construction.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/auth/**", "/api/v1/files/**", "/api/v1/values/**").permitAll()
                        .requestMatchers("/api/v1/auth/reset/email", "/api/v1/auth/reset","/api/v1/auth/register").authenticated()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/v1/contact/get/","/api/v1/contact/getAll", "api/v1/auth/sendVerificationCode","/api/v1/contact/apply").permitAll()
                        .requestMatchers("/api/v1/files/**","/api/v1/foreign/getAll", "/api/v1/foreign/get/**","/api/v1/foreign/getBySlug/**").permitAll()
                        .requestMatchers("/api/v1/home/all","/api/v1/ksm/getAll", "/api/v1/ksm/get/**","/api/v1/ksm/getBySlug/**").permitAll()
                        .requestMatchers("/api/v1/news/getAll", "/api/v1/news/get10News","/api/v1/news/get/**", "/api/v1/news/getBySlug/**").permitAll()
                        .requestMatchers(
                                "/api/v1/service/card/getBySlug/**",
                                "/api/v1/service/card/get/**",
                                "/api/v1/service/card/getAll",
                                "/api/v1/service/card/getByHeadCategory/**",
                                "/api/v1/service/card/getBySubCategory/**",

                                "/api/v1/service/head-category/get/**",
                                "/api/v1/service/head-category/getAll",
                                "/api/v1/service/head-category/getBySlug/**",

                                "/api/v1/service/sub-category/get/**",
                                "/api/v1/service/sub-category/getAll",
                                "/api/v1/service/sub-category/getByHeadSlug/**",
                                "/api/v1/service/sub-category/getBySlug/**"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/projects/get/**",
                                "/api/v1/projects/getBySlug/**",
                                "/api/v1/projects/getAll"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/setem/getAll",
                                "/api/v1/setem/get/**"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/social/getAll",
                                "/api/v1/social/get/**"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/vacancy/get/**",
                                "/api/v1/vacancy/getBySlug/**",
                                "/api/v1/vacancy/getAll",
                                "/api/v1/vacancy/applicant/getAll",
                                "/api/v1/vacancy/applicant/get/**",
                                "/api/v1/vacancy/applicant/apply"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/specials/getAll",
                                "/api/v1/specials/getBySlug/**",
                                "/api/v1/specials/get/**"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/home/about/get",
                                "/api/v1/home/about/get/**"
                        ).permitAll()
                        .requestMatchers("/api/v1/carousel/getAll").permitAll()
                        .requestMatchers("/api/v1/whyChooseUs/getAll").permitAll()
                        .requestMatchers("/api/v1/about/get").permitAll()
                        .requestMatchers(
                                "/api/v1/certificates/getAll",
                                "/api/v1/certificates/get/**"
                        ).permitAll()
                        .requestMatchers("/api/v1/history/get").permitAll()

                        .requestMatchers("/api/v1/management-structure/get").permitAll()
                        .requestMatchers(
                                "/api/v1/team/getAll",
                                "/api/v1/team/get/**"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/missions/getAll",
                                "/api/v1/missions/get/**"
                        ).permitAll()
                        .requestMatchers(
                                "/api/v1/values/getAll",
                                "/api/v1/values/get/**"
                        ).permitAll()
                        .requestMatchers("/api/v1/vision/get").permitAll()
                        .requestMatchers(
                                "/api/v1/auth/register",
                                "/api/v1/auth/login",
                                "/api/v1/auth/forgetPassword",
                                "/api/v1/auth/verify"
                        ).permitAll().anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .cors(c -> c.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "XSRF-TOKEN"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
