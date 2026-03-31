package emp.cad.api.reuniao.dto;

import jakarta.validation.constraints.NotNull;

public record DadosConfrimacaoPresenca(

        @NotNull
        Long idReuniao,
        @NotNull
        Long idFuncionario

) {
}
