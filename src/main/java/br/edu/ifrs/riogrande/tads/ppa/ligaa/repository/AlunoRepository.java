package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Aluno;

@Repository
public class AlunoRepository implements IRepository<Aluno> {

    private Map<Integer, Aluno> mapa = new HashMap<>();
    private int currentId = 1;

    @Override
    public Aluno save(Aluno a) {
        LocalDateTime agora = LocalDateTime.now();

        if (a.getId() == null) {
            a.setDataHoraCriacao(agora);
            a.setId(currentId++);
            a.setDesativado(false);
        }

        if (a.isDesativado() || (mapa.containsKey(a.getId()) && mapa.get(a.getId()).isDesativado())) {
            throw new EntidadeInativaException();
        }

        a.setDataHoraAlteracao(agora);
        mapa.put(a.getId(), a);

        return a;
    }

    public boolean cpfExists(String cpf) {
        return mapa.values().stream()
                .anyMatch(aluno -> cpf.equals(aluno.getCpf()));
    }

    public Optional<Aluno> findByCpf(String cpf) {
        return mapa.values().stream()
                .filter(aluno -> aluno.getCpf().equals(cpf))
                .findFirst();
    }

    public Optional<Aluno> findById(int id) {
        Aluno aluno = mapa.get(id);
        return aluno != null ? Optional.of(aluno) : Optional.empty();
    }

    @Override
    public boolean delete(Aluno e) {
        e.setDesativado(true);
        e.setDataHoraAlteracao(LocalDateTime.now());
        return true;
    }

    @Override
    public List<Aluno> findAll() {
        return new ArrayList<>(mapa.values());
    }
}
