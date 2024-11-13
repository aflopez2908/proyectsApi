package gestorfreelance.gestorfreelancev5.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void testSendEmailwithAttachment_Success() throws MessagingException {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        doNothing().when(javaMailSender).send(mimeMessage);

        // Act
        String result = emailService.sendEmailwithAttachment(to, subject, body);

        // Assert
        assertEquals("Correo enviado exitosamente con archivo adjunto", result);
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    void testSendEmailwithAttachment_Failure() throws MessagingException {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Simula una excepción MailException en el método 'send' en lugar de 'createMimeMessage'
        doThrow(new MailException("Failed to send MimeMessage") {}).when(javaMailSender).send(mimeMessage);

        //
        String result = emailService.sendEmailwithAttachment(to, subject, body);

        //respuesta
        assertEquals("Error al enviar el correo con archivo adjunto", result);
    }

}