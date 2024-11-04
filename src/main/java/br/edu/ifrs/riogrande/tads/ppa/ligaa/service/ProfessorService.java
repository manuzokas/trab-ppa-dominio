package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Professor;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.ProfessorRepository;

import java.util.List;

@Service // Indica que esta classe é um serviço
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor cadastrarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor buscarProfessorPorId(Integer id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + id));
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor atualizarProfessor(Integer id, Professor professor) {
        Professor professorExistente = buscarProfessorPorId(id);
        professorExistente.setSiape(professor.getSiape());
        professorExistente.setFormacao(professor.getFormacao());
        return professorRepository.save(professorExistente);
    }

    public void deletarProfessor(Integer id) {
        Professor professor = buscarProfessorPorId(id);
        professorRepository.delete(professor);
    }
}
