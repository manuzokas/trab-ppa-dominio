package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Professor;

@Repository
public class ProfessorRepository implements IRepository<Professor> {

    private Map<Integer, Professor> mapa = new HashMap<>();
    private int currentId = 1;

    @Override
    public Professor save(Professor p) {
        LocalDateTime agora = LocalDateTime.now();

        if (p.getId() == null) {
            p.setId(currentId++);
        }

        if (p.isDesativado() || (mapa.containsKey(p.getId()) && mapa.get(p.getId()).isDesativado())) {
            throw new EntidadeInativaException();
        }

        p.setDataHoraAlteracao(agora);
        mapa.put(p.getId(), p);

        return p;
    }

    public Optional<Professor> findById(int id) {
        Professor professor = mapa.get(id);
        return professor != null ? Optional.of(professor) : Optional.empty();
    }

    @Override
    public boolean delete(Professor p) {
        p.setDesativado(true);
        p.setDataHoraAlteracao(LocalDateTime.now());
        return true;
    }

    @Override
    public List<Professor> findAll() {
        return new ArrayList<>(mapa.values());
    }
}
