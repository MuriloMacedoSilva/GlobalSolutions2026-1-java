package com.SpaceAgro.api.controller;

import com.SpaceAgro.api.model.Talhao;
import com.SpaceAgro.api.service.TalhaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talhoes")
public class TalhaoController {

    private final TalhaoService service;

    public TalhaoController(TalhaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Talhao> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Talhao buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/produtor/{idProdutor}")
    public List<Talhao> buscarPorProdutor(
            @PathVariable Long idProdutor) {
        return service.buscarPorProdutor(idProdutor);
    }

    @PostMapping
    public Talhao salvar(@RequestBody Talhao talhao) {
        return service.salvar(talhao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Talhao dados
    ) {
        Talhao talhaoAtualizado = service.atualizar(id, dados);

        if (talhaoAtualizado == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(talhaoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        boolean excluiu = service.excluir(id);

        if (!excluiu) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }
}