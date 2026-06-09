package com.SpaceAgro.api.service;

import com.SpaceAgro.api.model.Produtor;
import com.SpaceAgro.api.repository.ProdutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutorService {

    private final ProdutorRepository repository;

    public ProdutorService(ProdutorRepository repository) {
        this.repository = repository;
    }

    public List<Produtor> listarTodos() {
        return repository.findAll();
    }

    public Produtor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Produtor salvar(Produtor produtor) {
        return repository.save(produtor);
    }

    public Produtor login(String email, String senha) {
        return repository
                .findByEmailAndSenha(email, senha)
                .orElse(null);
    }
}