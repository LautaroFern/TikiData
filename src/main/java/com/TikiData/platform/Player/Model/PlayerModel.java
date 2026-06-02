package com.TikiData.platform.Player.Model;

import com.TikiData.platform.Team.Model.TeamModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "players")
public class PlayerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDate bornDate;

    @Column(nullable = false)
    private String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamModel team;

}
