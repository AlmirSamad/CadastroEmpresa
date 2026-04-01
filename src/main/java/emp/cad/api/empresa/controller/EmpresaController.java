package emp.cad.api.empresa.controller;


import emp.cad.api.empresa.dto.AtualizarEmpresaDTO;
import emp.cad.api.empresa.dto.DetalhamentoEmpresaDTO;
import emp.cad.api.empresa.dto.EmpresaDTO;
import emp.cad.api.empresa.dto.ListagemEmpresasDTO;
import emp.cad.api.empresa.entity.Empresa;
import emp.cad.api.empresa.repository.EmpresaRepository;
import emp.cad.api.funcionario.repository.FuncionarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("empresa")
@SecurityRequirement(name = "bearer-key")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid EmpresaDTO dados, UriComponentsBuilder uriComponentsBuilder){

         var empresa =  new Empresa(dados);

        empresaRepository.save(empresa);
        var uri = uriComponentsBuilder.path("/empresa/{id}")
                .buildAndExpand(empresa.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DetalhamentoEmpresaDTO(empresa));

    }

    @GetMapping
    public ResponseEntity<Page<ListagemEmpresasDTO>>  list(@PageableDefault(size = 10, sort = {"nomeFantasia"}) Pageable pageable){
        var page = empresaRepository.findAllByAtivoTrue(pageable).map(ListagemEmpresasDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarEmpresaDTO dados){

        var empresa = empresaRepository.getReferenceById(dados.id());

        empresa.atualizarinformacoes(dados);

        return ResponseEntity.ok(new DetalhamentoEmpresaDTO(empresa));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativarEmpresa(@PathVariable Long id){
        var empresa = empresaRepository.getReferenceById(id);

        funcionarioRepository.inativarTodosPorEmpresaId(id);

        empresa.inativar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativar(@PathVariable Long id){
        var empresa = empresaRepository.getReferenceById(id);
        funcionarioRepository.ativarTodosPorEmpresaId(id);

        empresa.ativar();

        return ResponseEntity.ok(new DetalhamentoEmpresaDTO(empresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharEmpresa(@PathVariable Long id){
        var empresa = empresaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoEmpresaDTO(empresa));
    }
}
