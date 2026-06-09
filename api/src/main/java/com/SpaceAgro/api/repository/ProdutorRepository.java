package com.SpaceAgro.api.repository;

import com.SpaceAgro.api.model.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

    Optional<Produtor> findByEmailAndSenha(
            String email,
            String senha
    );
}