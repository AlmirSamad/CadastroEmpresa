package emp.cad.api.reuniao.service.validacoes;

import emp.cad.api.reuniao.dto.DadosAgendamentoReuniao;
import emp.cad.api.reuniao.service.ValidacaoException;

public interface ValidadorReuniao {

    void validar(DadosAgendamentoReuniao dados) throws ValidacaoException;
}
