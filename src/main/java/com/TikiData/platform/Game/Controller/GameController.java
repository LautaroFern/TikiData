package com.TikiData.platform.Game.Controller;

import com.TikiData.platform.Game.DTO.GameEventRequestDTO;
import com.TikiData.platform.Game.DTO.GameEventResponseDTO;
import com.TikiData.platform.Game.DTO.GameRequestDTO;
import com.TikiData.platform.Game.DTO.GameResponseDTO;
import com.TikiData.platform.Game.Service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<GameResponseDTO>> getAllGames() {
        return ResponseEntity.ok(gameService.findAllGames());
    }

    @GetMapping("/championship/{championshipId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<GameResponseDTO>> getByChampionship(@PathVariable Long championshipId) {
        return ResponseEntity.ok(gameService.findByChampionship(championshipId));
    }

    @GetMapping("/team/{teamId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<GameResponseDTO>> getByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(gameService.findByTeam(teamId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GameResponseDTO> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findGameById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameResponseDTO> createGame(@Valid @RequestBody GameRequestDTO dto) {
        GameResponseDTO response = gameService.saveGame(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) Integer homeGoals,
            @RequestParam(required = false) Integer awayGoals) {

        GameResponseDTO response = gameService.updateGameStatus(id, status, homeGoals, awayGoals);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/events")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameEventResponseDTO> addEvent(
            @PathVariable Long gameId,
            @Valid @RequestBody GameEventRequestDTO dto) {

        GameEventResponseDTO response = gameService.addEventToGame(gameId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}