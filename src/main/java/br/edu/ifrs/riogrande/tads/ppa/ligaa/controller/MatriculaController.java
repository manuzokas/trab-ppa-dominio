package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.TurmaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.MatriculaService;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.NovaMatricula;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    @Autowired
    public MatriculaController(MatriculaService matriculaService,
            AlunoRepository alunoRepository,
            TurmaRepository turmaRepository) {
        this.matriculaService = matriculaService;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Matricula> novaMatricula(@RequestBody NovaMatricula novaMatricula) {
        Matricula matricula = new Matricula();
        Aluno aluno = alunoRepository.findByCpf(novaMatricula.getCpfAluno())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Aluno não encontrado com CPF: " + novaMatricula.getCpfAluno()));
        matricula.setAluno(aluno);
        Turma turma = turmaRepository.findById(novaMatricula.getTurmaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Turma não encontrada com ID: " + novaMatricula.getTurmaId()));
        matricula.setTurma(turma);
        matricula.setDataMatricula(LocalDateTime.now());
        matriculaService.cadastrarMatricula(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Matricula> buscarMatriculaPorId(@PathVariable int id) {
        Matricula matricula = matriculaService.buscarMatriculaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada com ID: " + id));
        return ResponseEntity.ok(matricula);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Matricula> atualizarMatricula(@PathVariable int id, @RequestBody Matricula novaMatricula) {
        Matricula matriculaAtualizada = matriculaService.atualizarMatricula(id, novaMatricula);
        return ResponseEntity.ok(matriculaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable int id) {
        matriculaService.deletarMatricula(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> buscarTodasMatriculas() {
    return ResponseEntity.ok(matriculaService.findAll());
    }


}
