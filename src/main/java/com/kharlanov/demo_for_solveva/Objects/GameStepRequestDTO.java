package com.kharlanov.demo_for_solveva.Objects;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * DTO для передачи запросов к программе с именем игры и ходами других игроков
 */
@Component
@Data
public class GameStepRequestDTO {

    private String gameName;
    private LinkedList<String> answers;

}
