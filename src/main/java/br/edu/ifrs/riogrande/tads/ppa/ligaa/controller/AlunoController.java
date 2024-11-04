package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.AlunoService;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.NovoAluno;


// Rotear tudo "que tem a ver" com Aluno

@RestController
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
   
    // rotear
    @PostMapping(path = "/api/v1/alunos", 
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void novoAluno(@RequestBody NovoAluno aluno) {

        System.out.println(aluno);
        alunoService.cadastrarAluno(aluno); // não deve ser IDEMPOTENTE
    }
    
    @GetMapping(path = "/api/v1/alunos/{cpf}") // identificador
    public ResponseEntity<Aluno> buscaCpf(@PathVariable("cpf") String cpf) {
        Optional<Aluno> optionalAluno = alunoService.buscarAlunoPorCpf(cpf);

        if (optionalAluno.isPresent()) {
            return ResponseEntity.ok(optionalAluno.get()); // 200 retorna o aluno encontrado
        } else {
            return ResponseEntity.notFound().build(); // 404 aluno não encontrado
        }
    }

    @GetMapping(path = "/api/v1/alunos")
    public ResponseEntity<List<Aluno>> buscaTodos() {
        return ResponseEntity.ok(alunoService.findAll()); // outras opções: 404 ou 204
    }
}
