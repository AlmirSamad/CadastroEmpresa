package emp.cad.api.reuniao.dto;

import emp.cad.api.reuniao.entity.Reuniao;
import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoReuniao(Long id, Long idComissao, LocalDateTime dataHora, String pauta, List<String> participantesNomes) {


    public DadosDetalhamentoReuniao(Reuniao reuniao) {
        this(reuniao.getId(), reuniao.getComissao().getId(), reuniao.getDataHora(), reuniao.getPauta(), reuniao.getParticipantes().stream()
                .map(m -> m.getNome()).toList());
    }
}