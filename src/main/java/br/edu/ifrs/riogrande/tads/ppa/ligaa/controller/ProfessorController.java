package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Professor;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
        Professor professorCriado = professorService.cadastrarProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorCriado);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> buscarProfessorPorId(@PathVariable Integer id) {
        Professor professor = professorService.buscarProfessorPorId(id);
        return ResponseEntity.ok(professor);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Professor>> listarProfessores() {
        List<Professor> professores = professorService.listarProfessores();
        return ResponseEntity.ok(professores);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Integer id, @RequestBody Professor professor) {
        Professor professorAtualizado = professorService.atualizarProfessor(id, professor);
        return ResponseEntity.ok(professorAtualizado); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Integer id) {
        professorService.deletarProfessor(id);
        return ResponseEntity.noContent().build(); 
    }
}
