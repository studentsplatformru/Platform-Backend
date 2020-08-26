package ru.studentsplatform.backend.entities.model.user;

import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Класс для реализации подписки через Telegram.
 */
@Entity
@Table(name = "follower")
public class TelegramFollower {

    @Id
    @Column(name = "telegram_id")
    private Long telegramId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private SpbuTeam team;

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public SpbuTeam getTeam() {
        return team;
    }

    public void setTeam(SpbuTeam team) {
        this.team = team;
    }
}
