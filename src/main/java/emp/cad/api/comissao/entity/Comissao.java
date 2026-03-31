package emp.cad.api.comissao.entity;


import emp.cad.api.comissao.dto.TemaComissao;
import emp.cad.api.funcionario.entity.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "comissoes")
@Entity(name = "Comissao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comissao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TemaComissao tema;

    private String descricao;

    private Boolean ativo;

    @ManyToMany
    @JoinTable(
            name = "comissoes_funcionarios",
            joinColumns = @JoinColumn(name = "comissao_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<Funcionario> membros = new ArrayList<>();

    public void adicionarMembro(Funcionario funcionario) {
        this.membros.add(funcionario);
    }

    public void removerMembro(Funcionario funcionario) {
        this.membros.remove(funcionario);
    }

    public void inativar() {
        this.ativo = false;
    }
}
