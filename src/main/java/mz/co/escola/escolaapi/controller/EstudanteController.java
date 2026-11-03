package mz.co.escola.escolaapi.controller;

import mz.co.escola.escolaapi.model.Estudante;
import mz.co.escola.escolaapi.service.EstudanteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    private final EstudanteService service;

    public EstudanteController(EstudanteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Estudante>> listarTodos() {

        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Estudante> salvar(
            @Valid @RequestBody Estudante estudante) {

        Estudante novo = service.salvar(estudante);

        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Estudante estudante) {

        return ResponseEntity.ok(service.atualizar(id, estudante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}