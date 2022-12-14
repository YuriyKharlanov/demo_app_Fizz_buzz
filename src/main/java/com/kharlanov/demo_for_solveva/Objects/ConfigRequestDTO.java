package com.kharlanov.demo_for_solveva.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Для запросов для создания или прекращения игры
 */
@Data
public class ConfigRequestDTO {

    StagesEnum gameState;

    String wishFullName;

    Integer numberOfGamers;
}
