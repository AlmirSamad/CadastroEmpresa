package emp.cad.api.reuniao.service.validacoes;

import emp.cad.api.reuniao.dto.DadosAgendamentoReuniao;
import emp.cad.api.reuniao.service.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioDeFuncionamento implements ValidadorReuniao {



    public void validar(DadosAgendamentoReuniao dados) throws ValidacaoException {
        var dataReuniao = dados.dataHora();

        // --- REGRA 1: Segunda a sexta, entre 9h e 18h ---
        var domingo = dataReuniao.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var sabado = dataReuniao.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var antesDaAbertura = dataReuniao.getHour() < 9;
        var depoisDoEncerramento = dataReuniao.getHour() >= 18; // Bloqueia se tentar marcar para as 18:00 em diante

        if (domingo || sabado ||antesDaAbertura || depoisDoEncerramento) {
            throw new ValidacaoException("Reuniões só podem ser agendadas de segunda a sábado, das 09:00 às 18:00.");
        }
    }
}