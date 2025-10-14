package eu.epitech.t_dev_700.mappers;

import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordMapper {
    private final PasswordEncoder encoder;
    public PasswordMapper(PasswordEncoder encoder) { this.encoder = encoder; }

    @Named("encodePassword")
    public String encodePassword(String raw) {
        if (raw == null || raw.isBlank()) return raw; // let Bean Validation catch blanks if required
        return encoder.encode(raw);
    }
}
