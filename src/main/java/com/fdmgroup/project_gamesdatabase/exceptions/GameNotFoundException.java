package com.fdmgroup.project_gamesdatabase.exceptions;

public class GameNotFoundException extends RuntimeException {

    GameNotFoundException(Long id) {
        super("Could not find game " + id);
    }
}
