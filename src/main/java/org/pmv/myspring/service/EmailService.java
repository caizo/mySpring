package org.pmv.myspring.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.pmv.myspring.request.RegistroRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

/*    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }*/

    private void sendEmail(String para, String titulo, String cuerpo) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(para);
            helper.setSubject(titulo);
            helper.setText(cuerpo, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void enviarEmailConfirmacion(RegistroRequest registroRequest, String jwt) {
        String subject = "Confirmación de registro de usuario";
        String body = "<html><body>"
                + "<h1>Usuario registrado</h1>"
                + "<p>Usuario registrado: " + registroRequest.getUsername() + "</p>"
                + "<p>Token: " + jwt + "</p>"
                + "</body></html>";

        sendEmail(registroRequest.getEmail(), subject, body);
    }
}