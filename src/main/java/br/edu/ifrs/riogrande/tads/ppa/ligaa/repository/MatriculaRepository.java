package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Matricula;


@Repository
public class MatriculaRepository implements IRepository<Matricula> {

    private Map<Integer, Matricula> mapa = new HashMap<>();
    private int currentId = 1;

    @Override
    public Matricula save(Matricula matricula) {
        LocalDateTime agora = LocalDateTime.now();

        if (matricula.getId() == null) {
            matricula.setId(currentId++);
            matricula.setDataHoraCriacao(agora);
            matricula.setDesativado(false);
        }

        if (matricula.isDesativado()
                || (mapa.containsKey(matricula.getId()) && mapa.get(matricula.getId()).isDesativado())) {
            throw new EntidadeInativaException();
        }

        matricula.setDataHoraAlteracao(agora);
        mapa.put(matricula.getId(), matricula);
        return matricula;
    }

    public Optional<Matricula> findById(int id) {
        Matricula matricula = mapa.get(id);
        return matricula != null && !matricula.isDesativado() ? Optional.of(matricula) : Optional.empty();
    }

    @Override
    public boolean delete(Matricula matricula) {
        if (mapa.containsKey(matricula.getId()) && !matricula.isDesativado()) {
            matricula.setDesativado(true);
            matricula.setDataHoraAlteracao(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public void deleteById(int id) {
        Matricula matricula = mapa.get(id);
        if (matricula != null && !matricula.isDesativado()) {
            matricula.setDesativado(true);
            matricula.setDataHoraAlteracao(LocalDateTime.now());
        } else {
            throw new IllegalStateException("Matrícula não encontrada ou já desativada: " + id);
        }
    }

    @Override
    public List<Matricula> findAll() {
        List<Matricula> listaAtivas = new ArrayList<>();
        for (Matricula matricula : mapa.values()) {
            if (!matricula.isDesativado()) {
                listaAtivas.add(matricula);
            }
        }
        return listaAtivas;
    }
}
