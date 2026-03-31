package emp.cad.api.comissao.service;

import emp.cad.api.comissao.dto.CadastroComissaoDTO;
import emp.cad.api.comissao.dto.InscricaoComissaoDTO;
import emp.cad.api.comissao.entity.Comissao;
import emp.cad.api.comissao.repository.ComissaoRepository;
import emp.cad.api.funcionario.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComissaoService {

    @Autowired
    private ComissaoRepository comissaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Comissao criar(CadastroComissaoDTO dados) {
        var comissao = new Comissao(null, dados.nome(), dados.tema(), dados.descricao(), true, new java.util.ArrayList<>());
        return comissaoRepository.save(comissao);
    }

    public void inscreverFuncionario(Long idComissao, InscricaoComissaoDTO dados) {

        var comissao = comissaoRepository.findById(idComissao)
                .orElseThrow(() -> new IllegalArgumentException("Comissão não encontrada"));

        var funcionario = funcionarioRepository.findById(dados.idFuncionario())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        if (!comissao.getAtivo()) {
            throw new IllegalArgumentException("Não é possível se inscrever em uma comissão inativa.");
        }

        if (!funcionario.getAtivo()) {
            throw new IllegalArgumentException("Apenas funcionários ativos podem participar de comissões.");
        }

        if (comissao.getMembros().contains(funcionario)) {
            throw new IllegalArgumentException("O funcionário já faz parte desta comissão.");
        }

        comissao.adicionarMembro(funcionario);

    }
}
