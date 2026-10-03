package com.construmoveis.backend.service;

import com.construmoveis.backend.entity.Peca;
import com.construmoveis.backend.repository.PecaRepository;

import java.util.List;

public class PecaService {

    private final PecaRepository pecaRepository;

    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public List<Peca> buscar(){
        return pecaRepository.findAll();
    }

    public Peca cadastrarPeca(Peca pecaCadastrada){
        return pecaRepository.save(pecaCadastrada);
    }
}
