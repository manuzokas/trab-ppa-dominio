package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Disciplina;

@Repository
public class DisciplinaRepository implements IRepository<Disciplina> {

    private Map<Integer, Disciplina> mapa = new HashMap<>();
    private int currentId = 1;

    @Override
    public Disciplina save(Disciplina d) {
        LocalDateTime agora = LocalDateTime.now();

        if (d.getId() == null) {
            d.setId(currentId++);
            d.setDataHoraCriacao(agora); 
        }

        d.setDataHoraAlteracao(agora);
        mapa.put(d.getId(), d); 

        return d;
    }

    @Override
    public boolean delete(Disciplina d) {
        d.setDesativado(true); 
        d.setDataHoraAlteracao(LocalDateTime.now()); 
        return true; 
    }

    @Override
    public List<Disciplina> findAll() {
        return new ArrayList<>(mapa.values());
    }

    public Optional<Disciplina> findById(int id) {
        Disciplina disciplina = mapa.get(id);
        return disciplina != null ? Optional.of(disciplina) : Optional.empty();
    }
}
