package com.TikiData.platform.MiniGame.DTO;

import lombok.Data;

@Data
public class MiniGameResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String externalUrl;
    private String imageUrl;
}