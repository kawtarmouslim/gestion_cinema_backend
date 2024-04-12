package org.example.gestion_cinema.config;

import org.example.gestion_cinema.security.jwt.AuthEntryPointJwt;
import org.example.gestion_cinema.security.jwt.AuthTokenFilter;
import org.example.gestion_cinema.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public Filter authenticationJwtTokenFilter() {
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
       http.cors().and();
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint((AuthenticationEntryPoint) unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->{
                    auth.antMatchers("/api/auth/**").permitAll();
                            auth.antMatchers("/api/auth/signup").permitAll();
                            auth.antMatchers("/api/auth/signin").permitAll();


                    auth.antMatchers(HttpMethod.GET, "/api/cinemas/**").hasAnyRole("RESPONSABLE", "TECHNICIEN");
                            auth.antMatchers(HttpMethod.POST, "/api/cinemas").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.PUT, "/api/cinemas**").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.DELETE, "/api/cinemas**").hasAnyRole("RESPONSABLE");

                            auth.antMatchers(HttpMethod.GET, "/api/clients/**").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.POST, "/api/clients").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.PUT, "/api/clients/**").hasAnyRole("TECHNICIEN");
                            auth.antMatchers(HttpMethod.DELETE, "/api/clients/**").hasAnyRole("TECHNICIEN");

                            auth.antMatchers(HttpMethod.GET, "/api/films/**").hasAnyRole("RESPONSABLE", "TECHNICIEN");
                            auth.antMatchers(HttpMethod.POST, "/api/films").hasAnyRole("TECHNICIEN");
                            auth.antMatchers(HttpMethod.PUT, "/api/films/**").hasAnyRole("TECHNICIEN");
                            auth.antMatchers(HttpMethod.DELETE, "/api/films/**").hasAnyRole("TECHNICIEN");

                            auth.antMatchers(HttpMethod.GET, "/api/places/**").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.POST, "/api/places").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.PUT, "/api/places/**").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.DELETE, "/api/places/**").hasAnyRole("RESPONSABLE");

                            auth.antMatchers(HttpMethod.GET, "/api/utilisateur/**").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.POST, "/api/utilisateur").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.PUT, "/api/utilisateur/**").hasAnyRole("RESPONSABLE");
                            auth.antMatchers(HttpMethod.DELETE, "/api/utilisateur/**").hasAnyRole("RESPONSABLE");

                    auth.antMatchers(HttpMethod.GET, "/api/projections/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.POST, "/api/projections").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.PUT, "/api/projections/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.DELETE, "/api/projections/**").hasAnyRole("RESPONSABLE");

                    auth.antMatchers(HttpMethod.GET, "/api/tickets/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.POST, "/api/tickets").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.PUT, "/api/tickets/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.DELETE, "/api/tickets/**").hasAnyRole("RESPONSABLE");

                    auth.antMatchers(HttpMethod.GET, "/api/villes/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.POST, "/api/villes").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.PUT, "/api/villes/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.DELETE, "/api/villes/**").hasAnyRole("RESPONSABLE");

                    auth.antMatchers(HttpMethod.GET, "/api/seances/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.POST, "/api/seances").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.PUT, "/api/seances/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.DELETE, "/api/seances/**").hasAnyRole("RESPONSABLE");

                    auth.antMatchers(HttpMethod.GET, "/api/salles/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.POST, "/api/salles").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.PUT, "/api/salles/**").hasAnyRole("RESPONSABLE");
                    auth.antMatchers(HttpMethod.DELETE, "/api/salles/**").hasAnyRole("RESPONSABLE");



                            auth.anyRequest().authenticated();
                        }
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter();
//    }
}
