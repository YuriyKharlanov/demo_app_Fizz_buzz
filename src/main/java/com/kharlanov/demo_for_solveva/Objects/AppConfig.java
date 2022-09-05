package com.kharlanov.demo_for_solveva.Objects;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Тут храним список всех игр
 */
@Data
@Component
public class AppConfig {

    Map<String, GameContext> games = new HashMap<>();

}
