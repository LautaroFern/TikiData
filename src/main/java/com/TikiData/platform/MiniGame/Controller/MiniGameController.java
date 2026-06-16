package com.TikiData.platform.MiniGame.Controller;

import com.TikiData.platform.MiniGame.DTO.MiniGameRequestDTO;
import com.TikiData.platform.MiniGame.DTO.MiniGameResponseDTO;
import com.TikiData.platform.MiniGame.Service.MiniGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/minigames")
@RequiredArgsConstructor
public class MiniGameController {

    private final MiniGameService miniGameService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<MiniGameResponseDTO>> getAllMiniGames() {
        return ResponseEntity.ok(miniGameService.findAllMiniGames());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MiniGameResponseDTO> getMiniGameById(@PathVariable Long id) {
        return ResponseEntity.ok(miniGameService.findMiniGameById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MiniGameResponseDTO> createMiniGame(@RequestBody @Valid MiniGameRequestDTO dto) {
        MiniGameResponseDTO response = miniGameService.saveMiniGame(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MiniGameResponseDTO> updateMiniGame(
            @PathVariable Long id,
            @RequestBody @Valid MiniGameRequestDTO dto) {
        return ResponseEntity.ok(miniGameService.updateMiniGame(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMiniGame(@PathVariable Long id) {
        miniGameService.deleteMiniGame(id);
        return ResponseEntity.noContent().build();
    }
}