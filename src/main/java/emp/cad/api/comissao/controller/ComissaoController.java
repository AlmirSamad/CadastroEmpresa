package emp.cad.api.comissao.controller;

import emp.cad.api.comissao.dto.CadastroComissaoDTO;
import emp.cad.api.comissao.dto.InscricaoComissaoDTO;
import emp.cad.api.comissao.dto.ListagemComissaoDTO;

import emp.cad.api.comissao.dto.DetalhamentoComissaoDTO;
import emp.cad.api.comissao.service.ComissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comissoes") // Ajustado para o plural e com barra
public class ComissaoController {

    @Autowired
    private ComissaoService comissaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoComissaoDTO> criarComissao(@RequestBody @Valid CadastroComissaoDTO dados, UriComponentsBuilder uriBuilder) {

        var comissao = comissaoService.criar(dados);

        // Constrói a URI dinâmica do novo recurso (ex: http://localhost:8080/comissoes/1)
        var uri = uriBuilder.path("/comissoes/{id}").buildAndExpand(comissao.getId()).toUri();

        // Convertendo a entidade para DTO (Supondo que você crie esse DetalhamentoComissaoDTO)
        var dto = new DetalhamentoComissaoDTO(comissao);

        // Retorna 201 Created com a URI no header e o corpo da nova comissão
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/{id}/inscricoes")
    @Transactional
    public ResponseEntity<Void> inscreverFuncionario(@PathVariable Long id, @RequestBody @Valid InscricaoComissaoDTO dados) {

        comissaoService.inscreverFuncionario(id, dados);

        // Retorna 204 No Content (Padrão excelente para ações de vincular/desvincular)
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ListagemComissaoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = comissaoService.listarAtivas(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoComissaoDTO> detalhar(@PathVariable Long id) {
        // Supondo que o detalhar lá no service já retorna um DTO!
        var detalhamento = comissaoService.detalhar(id);
        return ResponseEntity.ok(detalhamento);
    }

    @DeleteMapping("/{idComissao}/inscricoes/{idFuncionario}")
    @Transactional
    public ResponseEntity<Void> desinscrever(@PathVariable Long idComissao, @PathVariable Long idFuncionario) {
        comissaoService.desinscrever(idComissao, idFuncionario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        comissaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}