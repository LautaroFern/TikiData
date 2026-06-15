package com.TikiData.platform.Team.Model;

import com.TikiData.platform.Championship.Model.ChampionshipModel;
import com.TikiData.platform.Player.Model.PlayerModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teams")
public class TeamModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate foundationDate;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String stadium;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String president;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private ChampionshipModel championship;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PlayerModel> players = new ArrayList<>();
}
