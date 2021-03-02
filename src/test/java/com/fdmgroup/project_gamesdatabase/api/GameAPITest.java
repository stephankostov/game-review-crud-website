package com.fdmgroup.project_gamesdatabase.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class GameAPITest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webContext;

    final static String GAME_API_ROOT = "/api/game/";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }


}
