package hh5.twogaether.config;

import hh5.twogaether.security.exception.CustomAuthenticationEntryPoint;
import hh5.twogaether.security.jwt.JwtAuthFilter;
import hh5.twogaether.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String[] test_url = {"/**","/admin/signup", "/admin/main"};

    private static final String[] permitUrl = {"/users/**", "/favicon.ico","/"}; // cors test 용 "/cors/**"

    private static final List<String> permitOrigin =
            List.of("https://....vercel.app");
//    private static final List<String> permitOrigin =
//            List.of("http://localhost:3000","https://....vercel.app",
//                    "http://....s3-website.ap-northeast-2.amazonaws.com","http://localhost:8080");

    private static final List<String> permitHeader = List.of("Authorization","Content-Type");

//    private static final List<String> permitMethod = List.of("GET","POST","OPTIONS");

    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // h2-console 사용 및 resources 접근 허용 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        http.cors().configurationSource(configurationSource());

        http.authorizeRequests()
                .antMatchers(permitUrl).permitAll()
                .antMatchers(test_url).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(permitOrigin);
        config.addAllowedOrigin("https://....vercel.app");
        config.addAllowedMethod("*");
//        config.setAllowedHeaders(permitHeader);

        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");

        config.addExposedHeader("Authorization");
        config.addExposedHeader("Content-Type");
        config.setAllowCredentials(true);

        corsConfigurationSource.registerCorsConfiguration("/**", config);

        return corsConfigurationSource;
    }
}
