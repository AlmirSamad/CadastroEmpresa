package emp.cad.api.reuniao.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamentoReuniao(
        @NotNull
        Long idComissao,

        @NotNull
        @Future // O Spring já barra datas no passado nativamente com isso!
        LocalDateTime dataHora,

        @NotBlank
        String pauta
) {
}