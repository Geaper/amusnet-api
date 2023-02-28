package com.amusnet.controller;

import com.amusnet.dto.PlayerActivityResponse;
import com.amusnet.service.PlayerActivityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PlayerActivityController.class)
public class PlayerActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlayerActivityService playerActivityService;

    @InjectMocks
    private PlayerActivityController playerActivityController;

    private Page<PlayerActivityResponse> playerActivityResponsePage;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerActivityController).build();

        List<PlayerActivityResponse> playerActivityResponseList = new ArrayList<>();
        playerActivityResponseList.add(new PlayerActivityResponse());
        playerActivityResponsePage = new PageImpl<>(playerActivityResponseList);
    }

    @Test
    public void testGetPlayerActivity() throws Exception {
        String playerId = "1";
        int offset = 0;
        int limit = 10;

        Pageable pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending());

        given(playerActivityService.getPlayerActivity(playerId, pageable)).willReturn(playerActivityResponsePage);

        mockMvc.perform(get("/activity/player/{playerId}?offset={offset}&limit={limit}", playerId, offset, limit))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }
}
