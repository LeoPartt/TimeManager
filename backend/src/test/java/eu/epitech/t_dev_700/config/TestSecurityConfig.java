package eu.epitech.t_dev_700.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de sécurité pour les tests.
 * Désactive l'authentification JWT pour permettre aux tests de s'exécuter sans token.
 * NOTE: Cette configuration n'utilise PAS @WithMockUser car cela nécessiterait
 * de mocker tout le système UserDetailsService. Pour les tests d'intégration,
 * on préfère simplement permettre toutes les requêtes.
 */
@Configuration
@EnableWebSecurity
@Profile("test")
@Order(1) // Higher priority than SecurityConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()    // Allow all requests in test environment
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder testPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
