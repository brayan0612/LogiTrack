package org.example.infrastructure.controller;

import org.example.application.usecase.RastreoUseCase;
import org.example.application.usecase.ReporteNovedadUseCase;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bot")
public class WebhookController {

    private final RastreoUseCase rastreoUseCase;
    private final ReporteNovedadUseCase reporteNovedadUseCase;

    // Inyectamos ambos casos de uso
    public WebhookController(RastreoUseCase rastreoUseCase, ReporteNovedadUseCase reporteNovedadUseCase) {
        this.rastreoUseCase = rastreoUseCase;
        this.reporteNovedadUseCase = reporteNovedadUseCase;
    }

    // Endpoint existente para leer
    @GetMapping("/rastreo")
    public String rastrearPaquete(@RequestParam String guia) {
        return rastreoUseCase.consultarEstado(guia);
    }

    // NUEVO Endpoint para escribir en la base de datos
    @PostMapping("/novedad")
    public String registrarNovedad(@RequestParam String guia,
                                   @RequestParam String ubicacion,
                                   @RequestParam String descripcion) {
        return reporteNovedadUseCase.reportarProblema(guia, ubicacion, descripcion);
    }

    // NUEVO: Endpoint para la verificación inicial de WhatsApp (Meta)
    @GetMapping("/whatsapp")
    public String verificarWebhookWhatsApp(
            @RequestParam(name = "hub.mode", required = false) String mode,
            @RequestParam(name = "hub.verify_token", required = false) String token,
            @RequestParam(name = "hub.challenge", required = false) String challenge) {

        // Esta es la contraseña secreta que configuraremos en la página de Meta
        String tokenSecreto = "logitrack_token_123";

        if ("subscribe".equals(mode) && tokenSecreto.equals(token)) {
            System.out.println("✅ Webhook verificado correctamente por Meta!");
            // Meta exige que devolvamos exactamente el número del challenge para aprobar la conexión
            return challenge;
        }

        return "❌ Error de validación de token";
    }

    @PostMapping("/whatsapp")
    public void recibirMensajeWhatsApp(@RequestBody Map<String, Object> payload) {
        System.out.println("📩 ¡Nuevo evento recibido de WhatsApp!");
        // Por ahora, solo imprimimos el JSON completo en la consola de IntelliJ
        System.out.println(payload);

        // El siguiente paso será extraer el número de teléfono y el texto para conectarlo con tus Casos de Uso
    }
}