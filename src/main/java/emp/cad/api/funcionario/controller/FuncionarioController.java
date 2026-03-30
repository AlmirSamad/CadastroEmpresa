package emp.cad.api.funcionario.controller;

import emp.cad.api.empresa.entity.Empresa;
import emp.cad.api.empresa.repository.EmpresaRepository;
import emp.cad.api.funcionario.dto.AtualizarFuncionarioDTO;
import emp.cad.api.funcionario.dto.DetalhamentoFuncionarioDTO;
import emp.cad.api.funcionario.dto.FuncionarioDTO;
import emp.cad.api.funcionario.dto.ListagemFuncionarioDTO;
import emp.cad.api.funcionario.entity.Funcionario;
import emp.cad.api.funcionario.repository.FuncionarioRepository;
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
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarFuncionario(@RequestBody FuncionarioDTO dados, UriComponentsBuilder uriComponentsBuilder){
        Empresa empresaEncontrada = empresaRepository.findByCnpj(dados.cnpjEmpresa())
                .orElseThrow(() -> new IllegalArgumentException("Erro: Nenhuma empresa cadastrada com o CNPJ " + dados.cnpjEmpresa()));

        Funcionario novoFuncionario = new Funcionario(dados, empresaEncontrada);

        funcionarioRepository.save(novoFuncionario);

        var uri = uriComponentsBuilder.path("/funcionario/{id}")
                .buildAndExpand(novoFuncionario.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DetalhamentoFuncionarioDTO(novoFuncionario));

    }

    @GetMapping
    public ResponseEntity< Page<ListagemFuncionarioDTO>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var page = funcionarioRepository.findAllByAtivoTrue(pageable).map(ListagemFuncionarioDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarFuncionario(@RequestBody @Valid AtualizarFuncionarioDTO dados){
        var funcionario = funcionarioRepository.getReferenceById(dados.id());

        funcionario.atualizarFuncionario(dados);

        return ResponseEntity.ok(new DetalhamentoFuncionarioDTO(funcionario));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirFuncionario(@PathVariable Long id){
        var funcionario = funcionarioRepository.getReferenceById(id);

        funcionario.inativar();
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity detalharFuncionario(@PathVariable Long id){
        var funcionario = funcionarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoFuncionarioDTO(funcionario));
    }

}
