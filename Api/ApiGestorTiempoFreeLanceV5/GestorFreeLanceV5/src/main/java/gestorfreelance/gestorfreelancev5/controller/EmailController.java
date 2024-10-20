package gestorfreelance.gestorfreelancev5.controller;
import gestorfreelance.gestorfreelancev5.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text) {

        try {
            emailService.sendSimpleMessage(to, subject, text);
            return "Correo enviado exitosamente a " + to;
        } catch (Exception e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }

}
