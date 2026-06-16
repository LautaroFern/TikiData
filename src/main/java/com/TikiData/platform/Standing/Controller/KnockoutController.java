package com.TikiData.platform.Standing.Controller;

import com.TikiData.platform.Standing.DTO.TieDTO;
import com.TikiData.platform.Standing.Service.KnockoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/knockout")
@RequiredArgsConstructor
public class KnockoutController {

    private final KnockoutService knockoutService;

    @GetMapping("/championship/{championshipId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TieDTO>> getBracket(@PathVariable Long championshipId) {
        return ResponseEntity.ok(knockoutService.getBracket(championshipId));
    }
}