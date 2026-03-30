package emp.cad.api.funcionario.entity;


import emp.cad.api.empresa.entity.Empresa;
import emp.cad.api.funcionario.dto.AtualizarFuncionarioDTO;
import emp.cad.api.funcionario.dto.FuncionarioDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Table(name = "funcionarios")
@Entity(name = "Funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Funcionario(FuncionarioDTO dados, Empresa empresaEncontrada) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.empresa = empresaEncontrada;
        this.ativo = true;
    }

    public void atualizarFuncionario(@Valid AtualizarFuncionarioDTO dados) {

        if (dados != null){
            if (StringUtils.hasText(dados.email())){
                this.email = dados.email();
            }
        }
        if (StringUtils.hasText(dados.telefone())){
            this.telefone = dados.telefone();
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
