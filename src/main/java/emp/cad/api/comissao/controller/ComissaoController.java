package emp.cad.api.comissao.controller;


import emp.cad.api.comissao.dto.CadastroComissaoDTO;
import emp.cad.api.comissao.dto.InscricaoComissaoDTO;
import emp.cad.api.comissao.dto.ListagemComissaoDTO;
import emp.cad.api.comissao.service.ComissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<ListagemComissaoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = comissaoService.listarAtivas(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var detalhamento = comissaoService.detalhar(id);
        return ResponseEntity.ok(detalhamento);
    }

    // Rota para remover um funcionário da comissão
    @DeleteMapping("/{idComissao}/inscricoes/{idFuncionario}")
    @Transactional
    public ResponseEntity desinscrever(@PathVariable Long idComissao, @PathVariable Long idFuncionario) {
        comissaoService.desinscrever(idComissao, idFuncionario);
        return ResponseEntity.noContent().build();
    }

    // Rota para inativar a comissão (Exclusão lógica)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        comissaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
