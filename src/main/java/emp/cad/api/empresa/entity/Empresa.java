package emp.cad.api.empresa.entity;

import emp.cad.api.empresa.dto.AtualizarEmpresaDTO;
import emp.cad.api.empresa.dto.EmpresaDTO;
import emp.cad.api.empresa.dto.Polo;
import emp.cad.api.empresa.dto.Subsetor;
import emp.cad.api.funcionario.entity.Funcionario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Table(name = "empresas")
@Entity(name = "Empresa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String diretor;
    private String email;
    private String telefone;
    private Boolean ativo;
//    private LocalDate efetivacao;

    @Enumerated(EnumType.STRING)
    private Polo polo;

    @Enumerated(EnumType.STRING)
    private Subsetor subsetor;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Empresa(EmpresaDTO dados) {
        this.nomeFantasia = dados.nomeFantasia();
        this.razaoSocial = dados.razaoSocial();
        this.cnpj = dados.cnpj();
        this.diretor = dados.diretor();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.ativo =  true;
        this.polo = dados.polo();
        this.subsetor = dados.subsetor();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarinformacoes(@Valid AtualizarEmpresaDTO dados) {
        if (dados != null){
            if (StringUtils.hasText(dados.diretor())){
                this.diretor = dados.diretor();
            }
            if (StringUtils.hasText(dados.email())){
                this.email = dados.email();
            }
            if (StringUtils.hasText(dados.telefone())){
                this.telefone = dados.telefone();
            }
            if (dados.enderecoDTO() != null){
                this.endereco.atualizarendereco(dados.enderecoDTO());
            }
        }

    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }
}
