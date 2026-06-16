package com.TikiData.platform.Standing.Service;

import com.TikiData.platform.Standing.DTO.TieDTO;

import java.util.List;

public interface IKnockoutService {
    List<TieDTO> getBracket(Long championshipId);
}
