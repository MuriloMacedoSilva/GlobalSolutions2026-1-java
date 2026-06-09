package com.SpaceAgro.api.repository;

import com.SpaceAgro.api.model.Talhao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalhaoRepository extends JpaRepository<Talhao, Long> {

    List<Talhao> findByIdProdutor(Long idProdutor);
}