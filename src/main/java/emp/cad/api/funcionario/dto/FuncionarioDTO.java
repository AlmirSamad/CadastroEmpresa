package emp.cad.api.funcionario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record FuncionarioDTO(

        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^\\\\d{10,11}$\", message = \"O telefone deve conter 10 ou 11 dígitos numéricos com DDD")
        String telefone,
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        @CNPJ
        String cnpjEmpresa

) {
}
