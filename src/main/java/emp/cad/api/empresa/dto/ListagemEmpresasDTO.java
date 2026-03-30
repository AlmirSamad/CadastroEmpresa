package emp.cad.api.empresa.dto;

import emp.cad.api.empresa.entity.Empresa;

public record ListagemEmpresasDTO(
        Long id,
        String nomeFantasia,
        String diretor,
        String cnpj,
        Polo polo,
        Subsetor subsetor) {

    public ListagemEmpresasDTO(Empresa empresa){
        this(empresa.getId(), empresa.getNomeFantasia(), empresa.getDiretor(),empresa.getCnpj(), empresa.getPolo(), empresa.getSubsetor());
    }
}
