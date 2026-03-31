package emp.cad.api.comissao.dto;

import emp.cad.api.comissao.entity.Comissao;


public record ListagemComissaoDTO(Long id, String nome, emp.cad.api.comissao.dto.TemaComissao tema) {
    public ListagemComissaoDTO(Comissao comissao) {
        this(comissao.getId(), comissao.getNome(), comissao.getTema());
    }
}