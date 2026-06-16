package com.TikiData.platform.Standing.Controller;

import com.TikiData.platform.Standing.DTO.StandingDTO;
import com.TikiData.platform.Standing.Service.StandingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/standings")
@RequiredArgsConstructor
public class StandingController {

    private final StandingService standingService;

    @GetMapping("/championship/{championshipId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<StandingDTO>> getStandings(@PathVariable Long championshipId) {
        return ResponseEntity.ok(standingService.getStandingsByChampionship(championshipId));
    }
}