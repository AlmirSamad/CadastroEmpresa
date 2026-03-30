package emp.cad.api.empresa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

public record EmpresaDTO(
        @NotBlank
        String nomeFantasia,
        @NotBlank
        String razaoSocial,
        @NotBlank
        @CNPJ(message = "Formato de número inválido")
        String cnpj,
        @NotBlank
        String diretor,
        @NotBlank @Email(message = "Email é obrigatório")
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Polo polo,
        @NotNull
        Subsetor subsetor,
        @NotNull @Valid
        EnderecoDTO endereco
) {
}
