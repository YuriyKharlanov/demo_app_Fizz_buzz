package com.kharlanov.demo_for_solveva.Objects;

import lombok.Getter;

/**
 * Возможные статусы игры
 * NEW_GAME означает что игра создана и в нее можно играть
 * STOP_GAME изначает что игра окончена и играть в нее больше нельзя
 */
public enum StagesEnum {

    NEW_GAME("NEW_GAME"), STOP_GAME("STOP_GAME");

    @Getter private String text;

    StagesEnum(String text) {
        this.text = text;
    }

}
