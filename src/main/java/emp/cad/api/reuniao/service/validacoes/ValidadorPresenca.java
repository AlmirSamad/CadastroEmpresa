package emp.cad.api.reuniao.service.validacoes;

import emp.cad.api.reuniao.dto.DadosConfrimacaoPresenca;
import emp.cad.api.reuniao.service.ValidacaoException;

public interface ValidadorPresenca {

    void presenca(DadosConfrimacaoPresenca dados) throws ValidacaoException;
}
