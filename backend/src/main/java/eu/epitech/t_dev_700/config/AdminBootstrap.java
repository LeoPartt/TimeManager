package eu.epitech.t_dev_700.config;

import eu.epitech.t_dev_700.config.props.AdminProps;
import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties(AdminProps.class)
public class AdminBootstrap {

    @Bean
    CommandLineRunner createDefaultAdmin(
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder,
            AdminProps props
    ) {
        return args -> {
            if (!props.enabled()) return;

            accountRepository.findByUsername("admin").ifPresent(accountRepository::delete);
            AccountEntity admin = new AccountEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(props.password()));
            admin.setFlags((byte) 255);
            accountRepository.save(admin);
        };
    }
}
