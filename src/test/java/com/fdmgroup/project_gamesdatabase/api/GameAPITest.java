package com.fdmgroup.project_gamesdatabase.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameAPITest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webContext;

    @Autowired
    ObjectMapper objectMapper;

    final static String GAME_API_ROOT = "/api/game/";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    void retrieveAllGames() throws Exception {
        String expectedResult = "[{\"gameId\":1,\"name\":\"Among Us\",\"developer\":{\"developerId\":1,\"name\":\"InnerSloth\",\"address\":\"Washington, USA\"}},{\"gameId\":2,\"name\":\"Minecraft\",\"developer\":{\"developerId\":2,\"name\":\"Mojang\",\"address\":\"Stockholm, Sweden\"}},{\"gameId\":3,\"name\":\"Witcher 3\",\"developer\":{\"developerId\":3,\"name\":\"CD Projekt Red\",\"address\":\"Warsaw, Poland\"}},{\"gameId\":4,\"name\":\"Undertale\",\"developer\":{\"developerId\":4,\"name\":\"Toby Fox\",\"address\":\"Massachusetts, USA\"}},{\"gameId\":5,\"name\":\"League of Legends\",\"developer\":{\"developerId\":5,\"name\":\"Riot Games\",\"address\":\"California, USA\"}}]";
        ResultActions mvcResult = mockMvc.perform(get(GAME_API_ROOT + "all"))
                .andExpect(status().isOk());

        String actualResult = mvcResult.andReturn().getResponse().getContentAsString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getGameIfExists() throws Exception {
        this.mockMvc
                .perform(get(GAME_API_ROOT + "get/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.gameId").value(1));
    }

    @Test
    void getGameIfDoesntExistGivesStatusNotFound() throws Exception {
        mockMvc.perform(get(GAME_API_ROOT + "get/{id}", 99999999))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void addNewGame() throws Exception {
        Developer developer = new Developer("Riot Games", "California, USA");
        Game game = new Game("League of Legends", developer);
        mockMvc.perform(post(GAME_API_ROOT + "create")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(game)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateGame() throws Exception {
        ResultActions mvcResult = mockMvc.perform(get(GAME_API_ROOT + "get/1"))
                .andExpect(status().isOk());

        String actualResult = mvcResult.andReturn().getResponse().getContentAsString();
        Game game = objectMapper.readValue(actualResult, Game.class);
        game.setName("Updated Name");

        mockMvc.perform(put(GAME_API_ROOT + "update/{id}", game.getGameId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(game)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteGame() throws Exception {
        mockMvc.perform(delete(GAME_API_ROOT + "delete/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void gameRatings() throws Exception {
        mockMvc.perform(get(GAME_API_ROOT + "gameRatings"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
