package com.kharlanov.demo_for_solveva.Objects;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * DTO для ответов на шаг игроков с очередным шагом программы и информационными сообщениями
 * о проверке корректности ответов другими игроками
 */
@Data
@Builder
public class GameStepResponseDTO {
    private String answer;
    private String nameOfGame;
    private UUID uuid;
    private String comment;
    private StagesEnum stagesEnum;
}
