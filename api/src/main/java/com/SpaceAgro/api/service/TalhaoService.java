package com.SpaceAgro.api.service;

import com.SpaceAgro.api.model.Talhao;
import com.SpaceAgro.api.repository.TalhaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalhaoService {

    private final TalhaoRepository repository;

    public TalhaoService(TalhaoRepository repository) {
        this.repository = repository;
    }

    public List<Talhao> listarTodos() {
        return repository.findAll();
    }

    public Talhao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Talhao> buscarPorProdutor(Long idProdutor) {
        return repository.findByIdProdutor(idProdutor);
    }

    public Talhao salvar(Talhao talhao) {
        return repository.save(talhao);
    }

    public Talhao atualizar(Long id, Talhao dados) {
        Talhao talhao = repository.findById(id).orElse(null);

        if (talhao == null) {
            return null;
        }

        talhao.setNomeTalhao(dados.getNomeTalhao());
        talhao.setCultura(dados.getCultura());
        talhao.setAreaHectares(dados.getAreaHectares());
        talhao.setLatitude(dados.getLatitude());
        talhao.setLongitude(dados.getLongitude());
        talhao.setIdProdutor(dados.getIdProdutor());

        return repository.save(talhao);
    }

    public boolean excluir(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}