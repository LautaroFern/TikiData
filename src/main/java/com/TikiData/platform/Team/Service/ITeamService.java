package com.TikiData.platform.Team.Service;

import com.TikiData.platform.Team.DTO.TeamResponseDTO;

public interface ITeamService {
    TeamResponseDTO findTeamByName(String name);

}
