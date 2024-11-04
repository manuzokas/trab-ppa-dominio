package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Disciplina;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public Disciplina criarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina buscarDisciplinaPorId(Integer id) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
        if (disciplinaOptional.isPresent()) {
            return disciplinaOptional.get();
        } else {
            throw new IllegalStateException("Disciplina não encontrada: " + id);
        }
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina atualizarDisciplina(Integer id, Disciplina disciplinaAtualizada) {
        Disciplina disciplinaExistente = buscarDisciplinaPorId(id);
        disciplinaExistente.setCargaHoraria(disciplinaAtualizada.getCargaHoraria());
        disciplinaExistente.setAulasSemana(disciplinaAtualizada.getAulasSemana());
        disciplinaExistente.setPreRequisitos(disciplinaAtualizada.getPreRequisitos());
        return disciplinaRepository.save(disciplinaExistente);
    }

    public void deletarDisciplina(Integer id) {
        Disciplina disciplinaExistente = buscarDisciplinaPorId(id);
        disciplinaRepository.delete(disciplinaExistente);
    }
}
