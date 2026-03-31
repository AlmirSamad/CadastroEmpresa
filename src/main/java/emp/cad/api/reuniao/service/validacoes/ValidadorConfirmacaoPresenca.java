package emp.cad.api.reuniao.service.validacoes;

import emp.cad.api.funcionario.repository.FuncionarioRepository;
import emp.cad.api.reuniao.dto.DadosConfrimacaoPresenca;
import emp.cad.api.reuniao.repository.ReuniaoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConfirmacaoPresenca implements ValidadorPresenca {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void presenca(DadosConfrimacaoPresenca dados){
        var reuniao = reuniaoRepository.findById(dados.idReuniao())
                .orElseThrow(() -> new ValidationException("Reunião não encontrada."));

        var funcionario = funcionarioRepository.findById(dados.idFuncionario())
                .orElseThrow(() -> new ValidationException("Funcionário não encontrado."));

        // Validação principal pedida: Funcionário Inativo
        if (!funcionario.getAtivo()) {
            throw new ValidationException("Ação não permitida. Funcionários inativos não podem confirmar presença.");
        }

        // Validação extra de coerência (Opcional, mas muito recomendada)
        if (!reuniao.getComissao().getMembros().contains(funcionario)) {
            throw new ValidationException("O funcionário não está inscrito na comissão dona desta reunião.");
        }
    }
}
