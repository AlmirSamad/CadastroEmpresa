package emp.cad.api.empresa.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarEmpresaDTO(
        @NotNull
        Long id,
        String diretor,
        String email,
        String telefone,
        EnderecoDTO enderecoDTO) {
}
