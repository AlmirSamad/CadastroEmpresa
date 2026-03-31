package emp.cad.api.reuniao.entity;

import emp.cad.api.comissao.entity.Comissao;
import emp.cad.api.funcionario.entity.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "reunioes")
@Entity(name = "Reuniao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reuniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento Muitos-para-Um (Muitas reuniões pertencem a Uma comissão)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comissao_id")
    private Comissao comissao;

    private LocalDateTime dataHora;

    private String pauta;

    private Boolean ativo;

    // Relacionamento Muitos-para-Muitos (Lista de presenças confirmadas)
    @ManyToMany
    @JoinTable(
            name = "reunioes_participantes",
            joinColumns = @JoinColumn(name = "reuniao_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<Funcionario> participantes = new ArrayList<>();

    // Construtor customizado para facilitar a criação pelo Service
    public Reuniao(Comissao comissao, LocalDateTime dataHora, String pauta) {
        this.comissao = comissao;
        this.dataHora = dataHora;
        this.pauta = pauta;
        this.ativo = true;
    }

    public void confirmarParticipante(Funcionario funcionario) {
        this.participantes.add(funcionario);
    }
}