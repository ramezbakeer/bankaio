package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authz) -> authz
                        // User Profile Access
                        .requestMatchers(HttpMethod.GET, "/users/{userId}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/users/{userId}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/{userId}").hasAuthority("ADMIN")

                        // Accounts Access
                        .requestMatchers(HttpMethod.GET, "/users/{userId}/accounts/{accountId}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/{userId}/accounts").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/users/{userId}/accounts/{accountId}").hasAuthority("ADMIN")

                        // Bills Access
                        .requestMatchers(HttpMethod.GET, "/users/{userId}/bills/{billId}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/{userId}/bills").hasAuthority("USER")

                        // Loans Access
                        .requestMatchers(HttpMethod.GET, "/users/{userId}/loans/{loanId}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/{userId}/loans").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PATCH, "/users/{userId}/loans/{loanId}").hasAuthority("ADMIN")

                        // Notifications Access
                        .requestMatchers(HttpMethod.GET, "/users/{userId}/notifications").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PATCH, "/users/{userId}/notifications/{notificationId}").hasAuthority("USER")

                        // Transactions Access
                        .requestMatchers(HttpMethod.GET, "/users/{userId}/accounts/{accountId}/transactions/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/{userId}/accounts/{accountId}/transactions/**").hasAuthority("USER")
                        // Deny all other requests
                        .anyRequest().denyAll())
                .httpBasic(withDefaults())
                .csrf(CsrfConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
