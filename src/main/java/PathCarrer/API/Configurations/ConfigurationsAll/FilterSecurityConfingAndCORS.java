package PathCarrer.API.Configurations.ConfigurationsAll;

import com.auth0.jwt.JWT;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@SecurityScheme(name = FilterSecurityConfingAndCORS.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT",scheme = "bearer")
public class FilterSecurityConfingAndCORS {
    @Autowired
    private SecurityFilter securityFilter;
    @Autowired
    private AuthenticationTratamentCustom authenticationTratamentCustom;
    @Value("${cors.client}")
    private String CorsClient;
    public static final String SECURITY = "bearerAuth";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            Exception {
        return
                http    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                        .csrf(csrf -> csrf.disable())
                        .sessionManagement(sm ->
                                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers(HttpMethod.POST,"/Login","/Login/NewUser").permitAll();
                            req.requestMatchers(HttpMethod.GET,"/CRUD").permitAll();
                            req.requestMatchers("/v3/api-docs/**", "swagger-ui/**","swagger-ui.html").permitAll();
                            req.anyRequest().authenticated();
                        })
                        .exceptionHandling(ex -> ex
                                .authenticationEntryPoint(authenticationTratamentCustom))
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws
            Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(CorsClient));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
