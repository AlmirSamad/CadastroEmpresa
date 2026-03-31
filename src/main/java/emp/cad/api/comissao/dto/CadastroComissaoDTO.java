package emp.cad.api.comissao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroComissaoDTO(@NotBlank
                                  String nome,

                                  @NotNull
                                  TemaComissao tema,

                                  String descricao) {
}
