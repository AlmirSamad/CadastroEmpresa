package emp.cad.api.reuniao.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAtualizarReuniao(
        LocalDateTime dataHora,
        String pauta) {
}
