package com.TikiData.platform.Championship.Controller;

import com.TikiData.platform.Championship.DTO.ChampionshipRequestDTO;
import com.TikiData.platform.Championship.DTO.ChampionshipResponseDTO;
import com.TikiData.platform.Championship.Service.ChampionshipService;
import com.TikiData.platform.Team.DTO.TeamRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/findChampionship/{name}")
    public ResponseEntity<ChampionshipResponseDTO> getChampionshipByName(@PathVariable String name) {
        return ResponseEntity.ok(championshipService.findByName(name));
    }

    @PostMapping("/createChampionship")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChampionshipResponseDTO> saveChampionship(@RequestBody @Valid ChampionshipRequestDTO championshipRequestDTO) {
        return ResponseEntity.ok(championshipService.saveChampionship(championshipRequestDTO));
    }

    @PutMapping("/updateChampionship/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChampionshipResponseDTO> updateChampionship(@PathVariable Long id, @RequestBody @Valid ChampionshipRequestDTO championshipRequestDTO) {
        return ResponseEntity.ok(championshipService.updateChampionship(championshipRequestDTO, id));
    }

    @DeleteMapping("/deleteChampionship/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteChampionship(@PathVariable Long id) {
        championshipService.deleteChampionshipById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ascenso/{championshipID}/team/{teamID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChampionshipResponseDTO> addTeamToChampionship(@PathVariable("championshipID") Long championshipId, @PathVariable("teamID") Long teamId){
        return ResponseEntity.ok(championshipService.addTeamToChampionship(championshipId, teamId));
    }

    @PutMapping("/descenso/{championshipID}/team/{teamID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeTeamFromChampionship(@PathVariable("teamID") Long idTeam, @PathVariable("championshipID") Long idChampionchip){
        championshipService.removeTeamFromChampionship(idTeam, idChampionchip);
        return ResponseEntity.noContent().build();
    }
}
