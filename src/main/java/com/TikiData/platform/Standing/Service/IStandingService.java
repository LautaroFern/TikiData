package com.TikiData.platform.Standing.Service;

import com.TikiData.platform.Standing.DTO.StandingDTO;

import java.util.List;

public interface IStandingService {
    List<StandingDTO> getStandingsByChampionship(Long championshipId);
}
