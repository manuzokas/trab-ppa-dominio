package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void cadastrarAluno(NovoAluno novoAluno) {
        if (alunoRepository.cpfExists(novoAluno.getCpf())) {
            throw new IllegalStateException("CPF já existe: " + novoAluno.getCpf());
        }

        Aluno aluno = new Aluno();

        aluno.setCpf(novoAluno.getCpf());
        aluno.setNome(novoAluno.getNome());
        aluno.setLogin(novoAluno.getLogin());
        aluno.setEnderecoEletronico(novoAluno.getEnderecoEletronico());

        alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAluno(@NonNull Integer id) {
        return alunoRepository.findAll().stream()
                .filter(aluno -> aluno.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Aluno não encontrado com ID: " + id));
    }

    public Optional<Aluno> buscarAlunoPorCpf(String cpf) {
        return alunoRepository.findByCpf(cpf);
    }

}
