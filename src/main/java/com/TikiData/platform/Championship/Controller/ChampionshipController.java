package com.TikiData.platform.Championship.Controller;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Mapper.ChampionshipMapper;
import com.TikiData.platform.Championship.Model.Championship;
import com.TikiData.platform.Championship.Service.ChampionshipService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/championship")
@RequiredArgsConstructor
public class ChampionshipController {

    private final ChampionshipService championshipService;

    @GetMapping
    public ResponseEntity<List<ChampionshipResponseDTO>> getAllChampionships() {
        return ResponseEntity.ok(championshipService.findAllChampionships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipResponseDTO> getChampionshipById(@PathVariable String nombre) {
        return ResponseEntity.ok(championshipService.findByName(nombre));
    }

    @PostMapping
    public ResponseEntity<ChampionshipResponseDTO> saveChampionship(@RequestBody @Valid ChampionshipRequestDTO championshipRequestDTO) {
        return ResponseEntity.ok(championshipService.saveChampionship(championshipRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampionshipResponseDTO> updateChampionship(@PathVariable Long id, @RequestBody @Valid ChampionshipRequestDTO championshipRequestDTO) {
        return ResponseEntity.ok(championshipService.updateChampionship(championshipRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChampionship(@PathVariable Long id) {
        championshipService.deleteChampionshipById(id);
        return ResponseEntity.noContent().build();
    }


}
