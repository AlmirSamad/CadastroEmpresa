package emp.cad.api.funcionario.dto;

import emp.cad.api.empresa.entity.Empresa;
import emp.cad.api.funcionario.entity.Funcionario;

public record DetalhamentoFuncionarioDTO(
         Long id,
         String nome,
         String email,
         String telefone,
         String cpf,
         Boolean ativo,
         String nomeFantasia
) {

    public DetalhamentoFuncionarioDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getEmail(), funcionario.getTelefone(),
                funcionario.getCpf(), funcionario.getAtivo(), funcionario.getEmpresa().getNomeFantasia());
    }
}
