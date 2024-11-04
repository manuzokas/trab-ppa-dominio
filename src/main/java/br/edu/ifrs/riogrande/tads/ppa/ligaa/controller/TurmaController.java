package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.TurmaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Turma> cadastrarTurma(@RequestBody Turma novaTurma) {
        Turma turmaCriada = turmaService.cadastrarTurma(novaTurma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaCriada);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Turma> buscarTurma(@PathVariable Integer id) {
        Turma turma = turmaService.buscarTurma(id);
        return ResponseEntity.ok(turma);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Turma>> listarTurmas() {
        List<Turma> turmas = turmaService.findAll();
        return ResponseEntity.ok(turmas); 
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Integer id, @RequestBody Turma novaTurma) {
        Turma turmaAtualizada = turmaService.atualizarTurma(id, novaTurma);
        return ResponseEntity.ok(turmaAtualizada); 
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarTurma(@PathVariable Integer id) {
        turmaService.deletarTurma(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{turmaId}/ofertaDisciplina/{disciplinaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> ofertaDisciplina(@PathVariable Integer turmaId, @PathVariable Integer disciplinaId) {
        turmaService.associarDisciplina(turmaId, disciplinaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
