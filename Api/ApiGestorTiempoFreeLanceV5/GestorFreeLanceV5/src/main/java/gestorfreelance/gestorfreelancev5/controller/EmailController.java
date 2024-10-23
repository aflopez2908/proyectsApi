package gestorfreelance.gestorfreelancev5.controller;
import gestorfreelance.gestorfreelancev5.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendEmailwithAttachment")
    public ResponseEntity<String> sendEmailwithAttachment(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body
    ) {
        try {
            String response = emailService.sendEmailwithAttachment(to, subject, body);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace(); // Log the exception to console for now
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud");
        }
    }
}