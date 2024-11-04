package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Disciplina;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.entity.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.DisciplinaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.MatriculaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.TurmaRepository;

@Service // qualificando o objeto
public class TurmaService {
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final AlunoService alunoService;
    private final MatriculaRepository matriculaRepository;
    private final DisciplinaRepository disciplinaRepository;

    private static final Logger logger = LoggerFactory.getLogger(TurmaService.class);

    public TurmaService(TurmaRepository turmaRepository, AlunoRepository alunoRepository,
            AlunoService alunoService, MatriculaRepository matriculaRepository,
            DisciplinaRepository disciplinaRepository) {
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
        this.alunoService = alunoService;
        this.matriculaRepository = matriculaRepository;
        this.disciplinaRepository = disciplinaRepository; 
    }

    public Turma cadastrarTurma(Turma novaTurma) {
        Turma turma = new Turma();
        turma.setDisciplinas(novaTurma.getDisciplinas());
        turma.setSemestre(novaTurma.getSemestre());
        turma.setSala(novaTurma.getSala());
        turma.setVagas(novaTurma.getVagas());
        turma.setPeriodo(novaTurma.getPeriodo());
        turma.setProfessor(novaTurma.getProfessor());
        
        return turmaRepository.save(turma);
    }

    public void associarDisciplina(Integer turmaId, Integer disciplinaId) {

        Optional<Turma> turmaOptional = turmaRepository.findById(turmaId);
        if (!turmaOptional.isPresent()) {
            logger.error("Turma não encontrada: " + turmaId);
            throw new IllegalStateException("Turma não encontrada: " + turmaId);
        }

        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(disciplinaId);
        if (!disciplinaOptional.isPresent()) {
            logger.error("Disciplina não encontrada: " + disciplinaId);
            throw new IllegalStateException("Disciplina não encontrada: " + disciplinaId);
        }

        Turma turma = turmaOptional.get();
        Disciplina disciplina = disciplinaOptional.get();

        turma.adicionarDisciplina(disciplina); 
        turmaRepository.save(turma);
        logger.info("Disciplina associada à turma com sucesso: " + turmaId);
    }

    public void matriculaAluno(NovaMatricula novaMatricula) {

        if (!alunoRepository.cpfExists(novaMatricula.getCpfAluno())) {
            logger.error("Aluno não existe: " + novaMatricula.getCpfAluno());
            throw new IllegalStateException("Aluno não existe: " + novaMatricula.getCpfAluno());
        }

        Optional<Turma> turmaOptional = turmaRepository.findById(novaMatricula.getTurmaId());
        if (!turmaOptional.isPresent()) {
            logger.error("Turma não existe: " + novaMatricula.getTurmaId());
            throw new IllegalStateException("Turma não existe: " + novaMatricula.getTurmaId());
        }

        Optional<Aluno> alunoOptional = alunoService.buscarAlunoPorCpf(novaMatricula.getCpfAluno());
        Aluno alunoMatricula = alunoOptional.orElseThrow(
                () -> new IllegalStateException("Aluno não encontrado com CPF: " + novaMatricula.getCpfAluno()));

        Matricula novaMatricula2 = new Matricula();

        novaMatricula2.setAluno(alunoMatricula);
        novaMatricula2.setTurma(turmaOptional.get());
        novaMatricula2.setDataMatricula(LocalDateTime.now());

        logger.info("Criando matrícula: " + novaMatricula2);

        if (novaMatricula2.getId() == null) {
            logger.debug("ID da nova matrícula é nulo, um ID será atribuído.");
        }

        matriculaRepository.save(novaMatricula2);
        logger.info("Matrícula salva com sucesso: " + novaMatricula2);
    }

    public Turma buscarTurma(int id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Turma não encontrada: " + id));
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma atualizarTurma(int id, Turma novaTurma) {
        turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Turma não encontrada: " + id));
        novaTurma.setId(id);
        return turmaRepository.save(novaTurma);
    }

    public boolean deletarTurma(int id) {
        turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Turma não encontrada: " + id));
        turmaRepository.deleteById(id);
        return true;
    }
}
