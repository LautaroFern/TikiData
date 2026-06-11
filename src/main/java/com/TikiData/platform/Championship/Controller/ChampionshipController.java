package com.TikiData.platform.Championship.Controller;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Service.ChampionshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/championship")
@RequiredArgsConstructor
public class ChampionshipController {

    @Autowired
    private final ChampionshipService championshipService;

    @GetMapping
    public ResponseEntity<List<ChampionshipResponseDTO>> getAllChampionships() {
        return ResponseEntity.ok(championshipService.findAllChampionships());
    }

    @GetMapping("/name")
    public ResponseEntity<ChampionshipResponseDTO> getChampionshipByName(@RequestParam String nombre) {
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
