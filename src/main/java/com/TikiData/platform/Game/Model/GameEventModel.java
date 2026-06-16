package com.TikiData.platform.Game.Model;

import com.TikiData.platform.Player.Model.PlayerModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "game_events")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameEventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "event_minute")
    private Integer minute;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameModel game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerModel player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secondary_player_id")
    private PlayerModel secondaryPlayer;

}
