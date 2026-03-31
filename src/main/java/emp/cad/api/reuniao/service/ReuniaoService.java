package emp.cad.api.reuniao.service;

import emp.cad.api.comissao.repository.ComissaoRepository;
import emp.cad.api.funcionario.repository.FuncionarioRepository;
import emp.cad.api.reuniao.dto.DadosAgendamentoReuniao;
import emp.cad.api.reuniao.dto.DadosConfrimacaoPresenca;
import emp.cad.api.reuniao.entity.Reuniao;
import emp.cad.api.reuniao.repository.ReuniaoRepository;
import emp.cad.api.reuniao.service.validacoes.ValidadorPresenca;
import emp.cad.api.reuniao.service.validacoes.ValidadorReuniao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReuniaoService {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private ComissaoRepository comissaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private List<ValidadorReuniao> validadorReuniaos;

    @Autowired
    private List<ValidadorPresenca> validadorPresencas;

    public Reuniao agendar(DadosAgendamentoReuniao dados) throws ValidacaoException {

        var dataReuniao = dados.dataHora();

        validadorReuniaos.forEach(v -> v.validar(dados));


        var comissao = comissaoRepository.findById(dados.idComissao())
                .orElseThrow(() -> new IllegalArgumentException("Comissão não encontrada no sistema."));

        var reuniao = new Reuniao(comissao, dataReuniao, dados.pauta());
        return reuniaoRepository.save(reuniao);
    }


    public void confirmarPresenca(DadosConfrimacaoPresenca dados){

        validadorPresencas.forEach(v -> v.presenca(dados));

        var reuniao = reuniaoRepository.findById(dados.idReuniao()).get();
        var funcionario = funcionarioRepository.findById(dados.idFuncionario()).get();


        reuniao.confirmarParticipante(funcionario);
    }
}