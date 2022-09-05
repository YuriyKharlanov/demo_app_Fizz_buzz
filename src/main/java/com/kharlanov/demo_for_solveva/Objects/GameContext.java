package com.kharlanov.demo_for_solveva.Objects;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Умный "Контекст" - содержит методы для проверки корректности ответов и информацию для ведения игры с определенным именем
 */
@Builder
@Data
public class GameContext {

    private UUID uuid;
    private Integer numberOfGamers;
    private String gameName;
    private StagesEnum stage;
    private Integer currentNumber;
    private String comments;

    /**
     * @return Отдает ответ компьютера
     */
    public String getAnswerAsText() {
        return getNumberAsText(++currentNumber);
    }

    /**
     * Принимает число и при необходимости отдает как текст
     * @param answ принимаемое число
     * @return текст
     */
    private String getNumberAsText(Integer answ) {
        if ((answ % 3 == 0) && ((answ % 5 == 0))) {
            return "fizz buzz";
        }
        if (answ % 3 == 0) {
            return "fizz";
        }
        if (answ % 5 == 0) {
            return "buzz";
        }
        return String.valueOf(answ);
    }

    /**
     * Берем последний ответ из контекста, получаем его в виде текста и сравниваем с пришедшим от участника,
     * если совпало, значит участник ответил правильно и инкрементим currentNumber.
     * Если не совпало, значит участник ошибся и выбывает и инкрементим currentNumber, так же
     * (вычитаем одного участника и пишем коммент) и если необходимо, меняем статус, что игра окончена, если других участников не осталось.
     * @param answAsText - проверяемый ответ участника
     * @return был ли ответ корректен
     */
    public Boolean checkNumber(String answAsText) {
        if (stage == StagesEnum.STOP_GAME) {
            return false;
        }
        if ((getNumberAsText(currentNumber+1)).equals(answAsText)) {
            currentNumber++;
            return true;
        }
        currentNumber++;
        if (numberOfGamers > 1) {
            numberOfGamers--;
            comments = comments + "участник выбыл, потому что ошибся с ответом назвав число " + answAsText + ", а следовало сказать "
                    + getNumberAsText(currentNumber)  + " (" + currentNumber + "); ";
        } else {
            numberOfGamers--;
            comments = comments + "Игра окончена и программа победила, так как последний участник выбыл, потому что ошибся с ответом назвав число "
                    + answAsText + ", а следовало сказать " + getNumberAsText(currentNumber) + " (" + currentNumber + ")";
            stage = StagesEnum.STOP_GAME;
        }
        return false;
    }

}
