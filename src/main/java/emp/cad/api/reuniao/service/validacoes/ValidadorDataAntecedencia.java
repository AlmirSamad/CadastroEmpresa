package emp.cad.api.reuniao.service.validacoes;

import emp.cad.api.reuniao.dto.DadosAgendamentoReuniao;
import emp.cad.api.reuniao.service.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorDataAntecedencia implements ValidadorReuniao {

    public void validar(DadosAgendamentoReuniao dados) throws ValidacaoException {
        var dataReuniao = dados.dataHora();
        // --- REGRA 2: Antecedência mínima de 1 dia (24 horas) ---
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, dataReuniao).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("A reunião deve ser agendada com antecedência mínima de 1 dia (24 horas).");
        }
    }
}
