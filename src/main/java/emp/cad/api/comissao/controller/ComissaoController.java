package emp.cad.api.comissao.controller;


import emp.cad.api.comissao.dto.CadastroComissaoDTO;
import emp.cad.api.comissao.dto.InscricaoComissaoDTO;
import emp.cad.api.comissao.service.ComissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comissao")
public class ComissaoController {


    @Autowired
    private ComissaoService comissaoService;

    @PostMapping
    @Transactional
    public ResponseEntity criarComissao(@RequestBody @Valid CadastroComissaoDTO dados) {
        var comissao = comissaoService.criar(dados);
        return ResponseEntity.ok().build(); // Depois podemos melhorar para retornar o DTO Detalhamento e a URI 201 Created
    }

    @PostMapping("/{id}/inscricoes")
    @Transactional
    public ResponseEntity inscreverFuncionario(@PathVariable Long id, @RequestBody @Valid InscricaoComissaoDTO dados) {
        comissaoService.inscreverFuncionario(id, dados);
        return ResponseEntity.ok("Funcionário inscrito com sucesso na comissão!");
    }
}
