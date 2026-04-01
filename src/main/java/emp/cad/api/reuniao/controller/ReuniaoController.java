package emp.cad.api.reuniao.controller;

import emp.cad.api.reuniao.dto.DadosAgendamentoReuniao;
import emp.cad.api.reuniao.dto.DadosAtualizarReuniao;
import emp.cad.api.reuniao.dto.DadosConfrimacaoPresenca;
import emp.cad.api.reuniao.dto.DadosDetalhamentoReuniao;
import emp.cad.api.reuniao.repository.ReuniaoRepository;
import emp.cad.api.reuniao.service.ReuniaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reunioes")
@SecurityRequirement(name = "bearer-key")
public class ReuniaoController {

    @Autowired
    private ReuniaoService reuniaoService;

    @Autowired
    private ReuniaoRepository reuniaoRepository;



    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoReuniao dados)  {


        var reuniao = reuniaoService.agendar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoReuniao(reuniao));
    }

    @PostMapping("/{idReuniao}/participantes/{idFuncionario}")
    @Transactional
    public ResponseEntity confirmarPresenca(@PathVariable Long idReuniao, @PathVariable Long idFuncionario) {

        var dados = new DadosConfrimacaoPresenca(idReuniao, idFuncionario);


        reuniaoService.confirmarPresenca(dados);


        return ResponseEntity.ok("Presença confirmada com sucesso!");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarReuniao(@PathVariable Long idReuniao, DadosAtualizarReuniao dados){
        var reuniao = reuniaoService.atualizar(idReuniao, dados);

        return ResponseEntity.ok(new DadosDetalhamentoReuniao(reuniao));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoReuniao> detalhar(@PathVariable Long id) {

        var detalhamento = reuniaoService.detalhar(id);

        return ResponseEntity.ok(detalhamento);
    }


}