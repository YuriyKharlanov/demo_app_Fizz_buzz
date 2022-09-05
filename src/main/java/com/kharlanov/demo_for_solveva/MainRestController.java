package com.kharlanov.demo_for_solveva;

import com.kharlanov.demo_for_solveva.Objects.*;
import com.kharlanov.demo_for_solveva.Services.GameConfigService;
import com.kharlanov.demo_for_solveva.Services.GameService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/demo/v1")
@OpenAPIDefinition(
        info = @Info (
            title = "Демо игра Fizz buzz",
            version = "0.1",
            description = "REST API игры"
            )
        )
public class MainRestController {

    @Autowired
    private GameConfigService configService;

    @Autowired
    private GameService gameService;

    @Operation(
            tags = "Управление",
            summary = "Позволяет создать новую игру",
            description = "Возвращает параметры созданной игры")
    @PostMapping("/config")
    public ConfigResponseDTO config(@RequestBody ConfigRequestDTO configRequestDTO) {
        return configService.ConfigRequestAgent(configRequestDTO);
    }

    @Operation(
            tags = "Управление",
            summary = "Отдает список имен имеющихся игр")
    @GetMapping("/getgameslist")
    public List<String> getCurrentGamesList() {
        return configService.getListOfGames();
    }

    @Operation(
            tags = "Игра",
            summary = "Позволяет осуществлять ходы ",
            description = "Возвращает параметры созданной игры и позволяет смотреть ответы как программы, так и корректность ответов других игроков")
    @PostMapping("/newstep")
    public GameStepResponseDTO newStep(@RequestBody GameStepRequestDTO gameStepRequestDTO) {
        return gameService.stepAgent(gameStepRequestDTO);
    }

}
