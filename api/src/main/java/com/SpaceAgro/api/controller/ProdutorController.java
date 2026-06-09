package com.SpaceAgro.api.controller;

import com.SpaceAgro.api.dto.LoginRequest;
import com.SpaceAgro.api.model.Produtor;
import com.SpaceAgro.api.service.ProdutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ProdutorController {

    private final ProdutorService service;

    public ProdutorController(ProdutorService service) {
        this.service = service;
    }

    @GetMapping("/produtores")
    public List<Produtor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/produtores/{id}")
    public Produtor buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/salvarProdutor")
    public Produtor salvar(@RequestBody Produtor produtor) {
        return service.salvar(produtor);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        var produtor = service.login(
                request.getEmail(),
                request.getSenha()
        );

        if (produtor == null) {
            return ResponseEntity
                    .status(401)
                    .body("Email ou senha inválidos");
        }

        return ResponseEntity.ok(produtor);
    }
}