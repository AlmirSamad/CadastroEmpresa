package emp.cad.api.reuniao.service.validacoes;

import emp.cad.api.reuniao.dto.DadosAgendamentoReuniao;
import emp.cad.api.reuniao.repository.ReuniaoRepository;
import emp.cad.api.reuniao.service.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConflitoDeHorario implements ValidadorReuniao {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    public void validar(DadosAgendamentoReuniao dados) throws ValidacaoException {

        var dataReuniao = dados.dataHora();

        var conflitoHorario = reuniaoRepository.existsByComissaoIdAndDataHora(dados.idComissao(), dataReuniao);

        if (conflitoHorario) {
            throw new ValidacaoException("Já existe uma reunião agendada para esta comissão neste exato horário.");
        }
    }
}