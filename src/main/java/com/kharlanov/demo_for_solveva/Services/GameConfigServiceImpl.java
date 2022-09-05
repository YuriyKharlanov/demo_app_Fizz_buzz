package com.kharlanov.demo_for_solveva.Services;

import com.kharlanov.demo_for_solveva.Objects.*;
import com.kharlanov.demo_for_solveva.Objects.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Общий сервис для создания и остановки игры
 * Хранит созданные или помеченные остановленными игры в AppConfig
 */
@Service
class GameConfigServiceImpl implements GameConfigService {

    @Autowired
    AppConfig appConfig;

    /**
     * Принимает решение о создании новой игры или прекращении существующей
     * @param configRequestDTO конфигурационный запрос
     * @return ответ клиенту
     */
    @Override
    public ConfigResponseDTO ConfigRequestAgent(ConfigRequestDTO configRequestDTO) {
        switch (configRequestDTO.getGameState()) {
            case NEW_GAME:
                return newGameEngine(configRequestDTO);
            case STOP_GAME:
                return stopGame(configRequestDTO);
            default:
                throw new IllegalStateException("Unexpected value: " + configRequestDTO.getGameState());
        }
    }

    /**
     * @return Отдает список имеющихся имен игр
     */
    @Override
    public List<String> getListOfGames() {
        return new ArrayList<>(appConfig.getGames().keySet());
    }

    /**
     * @param configRequestDTO Если в состоянии игры стоит STOP_GAME, тогда помечаем игру из контекста этим статусом
     *                      и более мы не сможем в нее играть
     * @return ответ о статусе игры с указанным именем
     */
    private ConfigResponseDTO stopGame(ConfigRequestDTO configRequestDTO) {
        if (!appConfig.getGames().containsKey(configRequestDTO.getWishFullName())) {
            throw new IllegalStateException("Нет игры с указанным именем");
        }
        GameContext gameContext = appConfig.getGames().get(configRequestDTO.getWishFullName());
        gameContext.setStage(StagesEnum.STOP_GAME);
        return ConfigResponseDTO.builder()
                .nameOfGame(configRequestDTO.getWishFullName())
                .gameUUID(gameContext.getUuid())
                .gameStage(gameContext.getStage())
                .build();
    }

    /**
     * @param configRequestDTO Создает новую игру в случае если указан NEW_GAME в запросе
     * @return ответ с параметрами созданной игры
     */
    private ConfigResponseDTO newGameEngine(ConfigRequestDTO configRequestDTO) {
        String newName = configRequestDTO.getWishFullName();
        int count = 0;
        while (appConfig.getGames().containsKey(newName) && count<100) {
            newName = configRequestDTO.getWishFullName().concat("-")
                    .concat(String.valueOf(ThreadLocalRandom.current().nextInt(1, 100 + 1)));
            count++;
        }
        if (count >= 100) {
            throw new IllegalStateException("Невозможно создать игру с таким именем");
        }
        if (configRequestDTO.getNumberOfGamers() == null || configRequestDTO.getNumberOfGamers() < 1) {
            throw new IllegalStateException("Число участников должно быть указано не менее 1");
        }
        GameContext newGame = GameContext.builder()
                .gameName(newName)
                .stage(StagesEnum.NEW_GAME)
                .currentNumber(0)
                .uuid(UUID.randomUUID())
                .numberOfGamers(configRequestDTO.getNumberOfGamers())
                .comments("")
                .build();
        appConfig.getGames().put(newName, newGame);
        return ConfigResponseDTO.builder()
                .nameOfGame(newGame.getGameName())
                .gameUUID(newGame.getUuid())
                .gameStage(newGame.getStage())
                .build();
    }
}
