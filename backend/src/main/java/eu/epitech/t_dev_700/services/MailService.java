package eu.epitech.t_dev_700.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Password Reset Request";
        String message = """
                You requested a password reset.
                Use this token to reset your password (valid for 15 minutes):
                
                %s
                
                If you didn’t request this, please ignore this email.
                """.formatted(token);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        mailSender.send(mail);
    }
}
