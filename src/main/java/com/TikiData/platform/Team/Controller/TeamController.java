package com.TikiData.platform.Team.Controller;

import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import com.TikiData.platform.Team.DTO.TeamResponseDTO;

import com.TikiData.platform.Team.Service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

        @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TeamResponseDTO> getTeamByName(@PathVariable String name) {
        return ResponseEntity.ok(teamService.findTeamByName(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody TeamRequestDTO dto) {
        TeamResponseDTO response = teamService.saveTeam(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamRequestDTO dto) {
        TeamResponseDTO response = teamService.updateTeam(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TeamResponseDTO>> filterTeams(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country) {

        List<TeamResponseDTO> response = teamService.filterTeams(name, country);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
