package com.construmoveis.backend.controller;

import com.construmoveis.backend.dto.PecaRequestDTO;
import com.construmoveis.backend.dto.PecaResponseDTO;
import com.construmoveis.backend.entity.Peca;
import com.construmoveis.backend.mapper.PecaMapper;
import com.construmoveis.backend.repository.PecaRepository;
import com.construmoveis.backend.service.PecaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pecas")
public class PecaController {


    private final PecaService pecaService;

    private final PecaRepository pecaRepository;

    public PecaController(PecaService pecaService, PecaRepository pecaRepository) {
        this.pecaService = pecaService;

        this.pecaRepository = pecaRepository;
    }

    @GetMapping
    ResponseEntity<List<PecaResponseDTO>> listar(){
        List<Peca> pecas = pecaService.buscar();
        return ResponseEntity.ok(PecaMapper.toResponseDto(pecas));
    }


    public ResponseEntity<PecaResponseDTO> cadastrar(
            @Valid @RequestBody PecaRequestDTO dto
    ){
        Peca peca = PecaMapper.toEntity(dto);
        Peca pecaSalva = pecaService.cadastrarPeca(peca);
        return ResponseEntity.status(201).body(PecaMapper.toResponse(pecaSalva));
    }

}
