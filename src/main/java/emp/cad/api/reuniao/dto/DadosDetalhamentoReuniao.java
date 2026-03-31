package emp.cad.api.reuniao.dto;

import emp.cad.api.reuniao.entity.Reuniao;
import java.time.LocalDateTime;

public record DadosDetalhamentoReuniao(Long id, Long idComissao, LocalDateTime dataHora, String pauta) {

    // Construtor que facilita a conversão da Entidade para DTO
    public DadosDetalhamentoReuniao(Reuniao reuniao) {
        this(reuniao.getId(), reuniao.getComissao().getId(), reuniao.getDataHora(), reuniao.getPauta());
    }
}