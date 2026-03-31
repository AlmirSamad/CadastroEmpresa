package emp.cad.api.reuniao.controller;

import emp.cad.api.reuniao.dto.DadosAgendamentoReuniao;
import emp.cad.api.reuniao.dto.DadosConfrimacaoPresenca;
import emp.cad.api.reuniao.dto.DadosDetalhamentoReuniao;
import emp.cad.api.reuniao.service.ReuniaoService;
import emp.cad.api.reuniao.service.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reunioes") // Ajustado para o plural
public class ReuniaoController {

    @Autowired
    private ReuniaoService reuniaoService;

    // 1. Rota para Agendar a Reunião
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoReuniao dados) throws ValidacaoException {

        // O service salva no banco e devolve a entidade preenchida com o ID
        var reuniao = reuniaoService.agendar(dados);

        // Convertendo a entidade salva para o DTO de saída
        return ResponseEntity.ok(new DadosDetalhamentoReuniao(reuniao));
    }

    // 2. Rota para o Funcionário confirmar presença (RSVP)
    @PostMapping("/{idReuniao}/participantes/{idFuncionario}")
    @Transactional
    public ResponseEntity confirmarPresenca(@PathVariable Long idReuniao, @PathVariable Long idFuncionario) throws ValidacaoException {

        var dados = new DadosConfrimacaoPresenca(idReuniao, idFuncionario);

        // 2. Agora sim passamos o DTO corretamente!
        reuniaoService.confirmarPresenca(dados);

        // Como é apenas uma ação de confirmar, não precisamos devolver um corpo.
        // O HTTP 204 (No Content) ou um simples OK com mensagem é o ideal.
        return ResponseEntity.ok("Presença confirmada com sucesso!");
    }
}