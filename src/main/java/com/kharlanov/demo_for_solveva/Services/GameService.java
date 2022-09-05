package com.kharlanov.demo_for_solveva.Services;

import com.kharlanov.demo_for_solveva.Objects.GameStepRequestDTO;
import com.kharlanov.demo_for_solveva.Objects.GameStepResponseDTO;

public interface GameService {

    GameStepResponseDTO stepAgent(GameStepRequestDTO request);
}
