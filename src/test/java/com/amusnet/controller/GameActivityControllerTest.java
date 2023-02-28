package com.amusnet.controller;

import com.amusnet.dto.GameActivityRequest;
import com.amusnet.dto.GameActivityResponse;
import com.amusnet.model.CustomException;
import com.amusnet.model.GameOutcome;
import com.amusnet.service.GameActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(GameActivityController.class)
public class GameActivityControllerTest {

    @Mock
    private GameActivityService gameActivityService;

    @InjectMocks
    private GameActivityController gameActivityController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameActivityController)
                .setControllerAdvice(new ControllerAdvisor())
                .build();

    }

    @Test
    public void testCreateGameActivityReturnsOkResponse() throws Exception {
        // Given
        GameActivityRequest request = new GameActivityRequest();
        request.setGameActivityId("123");
        request.setGameId("game1");
        request.setPlayerId("player1");
        request.setBetAmount(10.0);
        request.setCurrency(Currency.getInstance("EUR"));

        GameActivityResponse response = new GameActivityResponse();
        response.setGameActivityId("123");
        response.setWinAmount(0.0);
        response.setOutcome(GameOutcome.LOSS);
        response.setPlayerBalanceAfter(100.0);
        response.setCurrency(Currency.getInstance("EUR"));

        given(gameActivityService.createGameActivity(request)).willReturn(response);

        // When
        MvcResult result = mockMvc.perform(post("/activity/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        // Then
        String expectedResponseBody = new ObjectMapper().writeValueAsString(response);
        assertEquals(expectedResponseBody, result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateGameActivityThrowsCustomException() throws Exception {
        // Given
        GameActivityRequest request = new GameActivityRequest();
        request.setGameId("game1");
        request.setPlayerId("player1");
        request.setBetAmount(10.0);
        request.setCurrency(Currency.getInstance("EUR"));

        CustomException exception = new CustomException("Test exception");

        when(gameActivityService.createGameActivity(request)).thenThrow(exception);

        // When
        MvcResult result = mockMvc.perform(post("/activity/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        Map<String, String> expectedResponseBody = new HashMap<>();
        expectedResponseBody.put("message", exception.getMessage());

        // convert the expected response body map to JSON
        String expectedResponseBodyJson = new ObjectMapper().writeValueAsString(expectedResponseBody);

        // assert the response body is equal to the expected response body JSON
        assertEquals(expectedResponseBodyJson, result.getResponse().getContentAsString());
    }
}

