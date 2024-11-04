package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Disciplina;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE 
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Disciplina> criarDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina novaDisciplina = disciplinaService.criarDisciplina(disciplina);
        return ResponseEntity.ok(novaDisciplina);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Disciplina> buscarDisciplinaPorId(@PathVariable Integer id) {
        Disciplina disciplina = disciplinaService.buscarDisciplinaPorId(id);
        return ResponseEntity.ok(disciplina);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Disciplina>> listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable Integer id,
            @RequestBody Disciplina disciplina) {
        Disciplina disciplinaAtualizada = disciplinaService.atualizarDisciplina(id, disciplina);
        return ResponseEntity.ok(disciplinaAtualizada);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Integer id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
