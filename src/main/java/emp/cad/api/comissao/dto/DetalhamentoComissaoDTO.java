package emp.cad.api.comissao.dto;

import emp.cad.api.comissao.entity.Comissao;
import java.util.List;

public record DetalhamentoComissaoDTO(
        Long id,
        String nome,
        emp.cad.api.comissao.dto.TemaComissao tema,
        String descricao,
        List<String> membrosNames // Apenas os nomes para simplificar agora
) {
    public DetalhamentoComissaoDTO(Comissao comissao) {
        this(comissao.getId(), comissao.getNome(), comissao.getTema(), comissao.getDescricao(),
                comissao.getMembros().stream().map(m -> m.getNome()).toList());
    }
}