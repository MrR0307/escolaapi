package mz.co.escola.escolaapi.service;

import mz.co.escola.escolaapi.model.Estudante;
import mz.co.escola.escolaapi.repository.EstudanteRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class EstudanteService {

    private final EstudanteRepository repository;

    public EstudanteService(EstudanteRepository repository) {
        this.repository = repository;
    }

    public Estudante salvar(Estudante estudante) {
        return repository.save(estudante);
    }

    public List<Estudante> listarTodos() {
        return repository.findAll();
    }

    public Estudante buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estudante não encontrado"
                ));
    }

    public Estudante atualizar(Long id, Estudante estudante) {

        Estudante existente = buscarPorId(id);

        existente.setNome(estudante.getNome());
        existente.setApelido(estudante.getApelido());
        existente.setCurso(estudante.getCurso());
        existente.setIdade(estudante.getIdade());

        return repository.save(existente);
    }

    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Estudante não encontrado"
            );
        }

        repository.deleteById(id);
    }
}