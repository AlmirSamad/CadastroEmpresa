package emp.cad.api.comissao.dto;

import jakarta.validation.constraints.NotNull;

public record InscricaoComissaoDTO(@NotNull
                                Long idFuncionario) {
}
