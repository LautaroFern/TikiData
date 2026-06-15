package com.TikiData.platform.Championship.Model;

import com.TikiData.platform.Team.Model.TeamModel;
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
@Table(name = "championships")
public class ChampionshipModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Integer numberOfTeams;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<TeamModel> teams = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private Integer season;
}
