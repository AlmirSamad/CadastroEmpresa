package emp.cad.api.empresa.dto;

import emp.cad.api.empresa.entity.Empresa;
import emp.cad.api.empresa.entity.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record DetalhamentoEmpresaDTO(
        Long id,

        String nomeFantasia,
        String razaoSocial,
        String cnpj,
        String diretor,
        String email,
        String telefone,
        Polo polo,
        Subsetor subsetor,
        Endereco endereco) {

    public DetalhamentoEmpresaDTO(Empresa empresa){
        this(empresa.getId(), empresa.getNomeFantasia(), empresa.getRazaoSocial(),
                empresa.getCnpj(), empresa.getDiretor(), empresa.getEmail(), empresa.getTelefone(),
                empresa.getPolo(), empresa.getSubsetor(), empresa.getEndereco());
    }
}
