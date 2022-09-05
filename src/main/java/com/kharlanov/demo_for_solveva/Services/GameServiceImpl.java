package com.kharlanov.demo_for_solveva.Services;

import com.kharlanov.demo_for_solveva.Objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Для упрощения сейчас допустим что программа всегда ходит последней.
 * Т.е. если других игроков например три, то программа всегда ходит последней предполагая,
 * что другие участники уже сходили и ожидает три хода других участников.
 * Сделавший ошибку игрок исключается из игры.
 *
 * Проверяем всевозможные варианты некорректных запросов и перебираем ответы от участников на корректность и формируем ответ.
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    AppConfig appConfig;

    @Override
    public GameStepResponseDTO stepAgent(GameStepRequestDTO request) {
        if (request.getGameName() == null) {
            throw new IllegalStateException("Нет указано имя игры");
        }
        if (!appConfig.getGames().containsKey(request.getGameName())) {
            throw new IllegalStateException("Нет игры с указанным именем");
        }
        if (appConfig.getGames().get(request.getGameName()).getStage() == StagesEnum.STOP_GAME) {
            throw new IllegalStateException("Игра с указанным именем уже завершена");
        }
        GameContext currentGameContext = appConfig.getGames().get(request.getGameName());
        if (request.getAnswers() == null
                && request.getAnswers().size() != currentGameContext.getNumberOfGamers()) {
            throw new IllegalStateException("нет ответов игроков или количество не совпадает с количеством участников");
        }
        checkAnswers(request.getAnswers(), currentGameContext);
        return GameStepResponseDTO.builder()
                .answer(currentGameContext.getAnswerAsText())
                .nameOfGame(currentGameContext.getGameName())
                .uuid(currentGameContext.getUuid())
                .comment(currentGameContext.getComments())
                .stagesEnum(currentGameContext.getStage())
                .build();
    }

    /**
     * Здесь мы перебираем ответы участников и отправляем их в контекст игры и контекст меняется
     * После перебора получаем контекст и отдаем в виде ответа
     * @param answers список ответов участников
     * @param currentGameContext контекст текущей игры
     */
    private void checkAnswers(List<String> answers, GameContext currentGameContext) {
        for (String answ : answers) {
            currentGameContext.checkNumber(answ);
        }
    }
}
