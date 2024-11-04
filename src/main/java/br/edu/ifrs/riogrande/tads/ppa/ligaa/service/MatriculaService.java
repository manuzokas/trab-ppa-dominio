package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.MatriculaRepository;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public void cadastrarMatricula(Matricula novaMatricula) {
        if (novaMatricula.getId() != null) {
            throw new IllegalStateException("Não deve haver ID para uma nova matrícula.");
        }
        matriculaRepository.save(novaMatricula);
    }

    public Optional<Matricula> buscarMatriculaPorId(int id) {
        return matriculaRepository.findById(id);
    }

    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    public Matricula atualizarMatricula(int id, Matricula novaMatricula) {
        matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Matrícula não encontrada: " + id));
        novaMatricula.setId(id);
        return matriculaRepository.save(novaMatricula);
    }

    public boolean deletarMatricula(int id) {
        matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Matrícula não encontrada: " + id));
        matriculaRepository.deleteById(id);
        return true;
    }
}
