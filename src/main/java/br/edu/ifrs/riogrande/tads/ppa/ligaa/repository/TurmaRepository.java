package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Turma;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TurmaRepository implements IRepository<Turma> {

    private Map<Integer, Turma> mapa = new HashMap<>();
    private int currentId = 1;

    @Override
    public Turma save(Turma turma) {
        LocalDateTime agora = LocalDateTime.now();

        if (turma.getId() == null) {
            turma.setDataHoraCriacao(agora);
            turma.setId(currentId++);
            turma.setDesativado(false);
        }

        if (turma.isDesativado() || (mapa.containsKey(turma.getId()) && mapa.get(turma.getId()).isDesativado())) {
            throw new EntidadeInativaException();
        }

        turma.setDataHoraAlteracao(agora);
        mapa.put(turma.getId(), turma);

        return turma;
    }

    @Override
    public boolean delete(Turma turma) {
        if (mapa.containsKey(turma.getId())) {
            turma.setDesativado(true);
            turma.setDataHoraAlteracao(LocalDateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public List<Turma> findAll() {
        return new ArrayList<>(mapa.values());
    }

    public Optional<Turma> findById(int id) {
        Turma turma = mapa.get(id);
        return turma != null ? Optional.of(turma) : Optional.empty();
    }

    public List<Turma> findBySemestre(String semestre) {
        List<Turma> turmas = new ArrayList<>();
        for (Turma turma : mapa.values()) {
            if (semestre.equals(turma.getSemestre())) {
                turmas.add(turma);
            }
        }
        return turmas;
    }

    public List<Turma> findBySala(String sala) {
        List<Turma> turmas = new ArrayList<>();
        for (Turma turma : mapa.values()) {
            if (sala.equals(turma.getSala())) {
                turmas.add(turma);
            }
        }
        return turmas;
    }

    public List<Turma> findByProfessorId(int professorId) {
        List<Turma> turmas = new ArrayList<>();
        for (Turma turma : mapa.values()) {
            if (turma.getProfessor() != null && turma.getProfessor().getId() == professorId) {
                turmas.add(turma);
            }
        }
        return turmas;
    }

    public List<Turma> findByDisciplinaId(int disciplinaId) {
        List<Turma> turmas = new ArrayList<>();
        for (Turma turma : mapa.values()) {
            if (turma.getDisciplinas() != null
                    && turma.getDisciplinas().stream().anyMatch(d -> d.getId() == disciplinaId)) {
                turmas.add(turma);
            }
        }
        return turmas;
    }

    public void deleteById(int id) {
        if (mapa.containsKey(id)) {
            Turma turma = mapa.get(id);
            turma.setDesativado(true);
            turma.setDataHoraAlteracao(LocalDateTime.now());
        } else {
            throw new IllegalStateException("Turma não encontrada: " + id);
        }
    }

}
