package com.construmoveis.backend.controller;

import com.construmoveis.backend.dto.PecaRequestDTO;
import com.construmoveis.backend.dto.PecaResponseDTO;
import com.construmoveis.backend.entity.Peca;
import com.construmoveis.backend.mapper.PecaMapper;
import com.construmoveis.backend.repository.PecaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    private final PecaRepository pecaRepository;

    public PecaController(PecaRepository pecaRepository) {

        this.pecaRepository = pecaRepository;
    }

    @GetMapping
    ResponseEntity<List<PecaResponseDTO>> listar(){

        List<Peca> pecas = pecaRepository.findAll();

        List<PecaResponseDTO> pecasMapeadas = PecaMapper.toResponseDto(pecas);
        return ResponseEntity.ok(pecasMapeadas);
    }

    @PostMapping
    public ResponseEntity<PecaResponseDTO> cadastrar(
            @Valid  @RequestBody PecaRequestDTO pecaRequest
    ) {

        Peca peca = PecaMapper.toEntity(pecaRequest);
        Peca pecaSalva = pecaRepository.save(peca);

        PecaResponseDTO resposta = PecaMapper.toResponse(pecaSalva);

        return ResponseEntity.status(201).body(resposta);
    }

}
