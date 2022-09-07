package com.kharlanov.demo_for_solveva.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

/**
 * Для ответов о созданной игре
 */
@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigResponseDTO {
    private String nameOfGame;
    private UUID gameUUID;
    private StagesEnum gameStage;
}

