package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.AlunoService;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.NovoAluno;

@RestController
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Cadastrar novo aluno
    @PostMapping(path = "/api/v1/alunos", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void novoAluno(@RequestBody NovoAluno aluno) {
        alunoService.cadastrarAluno(aluno);
    }

    // Buscar aluno por CPF
    @GetMapping(path = "/api/v1/alunos/{cpf}")
    public ResponseEntity<Aluno> buscaCpf(@PathVariable("cpf") String cpf) {
        Optional<Aluno> optionalAluno = alunoService.buscarAlunoPorCpf(cpf);
        return optionalAluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar todos os alunos
    @GetMapping(path = "/api/v1/alunos")
    public ResponseEntity<List<Aluno>> buscaTodos() {
        return ResponseEntity.ok(alunoService.findAll());
    }

    // Atualizar aluno por CPF
    @PutMapping(path = "/api/v1/alunos/{cpf}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> atualizarAluno(@PathVariable("cpf") String cpf, @RequestBody NovoAluno novoAluno) {
        boolean atualizado = alunoService.atualizarAluno(cpf, novoAluno);
        return atualizado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Excluir aluno por CPF
    @DeleteMapping(path = "/api/v1/alunos/{cpf}")
    public ResponseEntity<Void> excluirAluno(@PathVariable("cpf") String cpf) {
        boolean excluido = alunoService.excluirAluno(cpf);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
