package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.service.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AllGamesController {

    @Autowired
    private GameService gameService;

    private static final Logger LOGGER = LogManager.getLogger(AllGamesController.class);

    /*
    @GetMapping("/AllGames")
    public ModelAndView allGames() {
        return new ModelAndView("WEB-INF/allGames.jsp", )
    } */
}
