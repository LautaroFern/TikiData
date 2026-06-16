package com.TikiData.platform.Player.Controller;

import com.TikiData.platform.Player.DTO.PlayerRequestDTO;
import com.TikiData.platform.Player.DTO.PlayerResponseDTO;
import com.TikiData.platform.Player.Service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlayerResponseDTO> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(playerService.findByName(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlayerResponseDTO> savePlayer(@RequestBody @Valid PlayerRequestDTO playerRequestDTO) {
        return ResponseEntity.ok(playerService.savePlayer(playerRequestDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@RequestBody @Valid PlayerRequestDTO playerRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(playerService.updatePlayer(playerRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
