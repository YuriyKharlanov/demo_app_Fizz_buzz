package com.kharlanov.demo_for_solveva.Services;

import com.kharlanov.demo_for_solveva.Objects.ConfigRequestDTO;
import com.kharlanov.demo_for_solveva.Objects.ConfigResponseDTO;

import java.util.List;

public interface GameConfigService {

    ConfigResponseDTO ConfigRequestAgent(ConfigRequestDTO configRequestDTO);

    List<String> getListOfGames();

}
