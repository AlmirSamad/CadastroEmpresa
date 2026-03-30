package emp.cad.api.funcionario.dto;

import emp.cad.api.empresa.entity.Empresa;
import emp.cad.api.funcionario.entity.Funcionario;

public record ListagemFuncionarioDTO(Long id, String nome, String email, String nomeFantasia) {

    public ListagemFuncionarioDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getEmail(), funcionario.getEmpresa().getNomeFantasia());
    }
}