package com.habitoplus.habitoplusback.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitoplus.habitoplusback.dto.GroupDTO;
import com.habitoplus.habitoplusback.dto.ProfileDTO;
import com.habitoplus.habitoplusback.dto.RequestDTO;
import com.habitoplus.habitoplusback.enums.Status;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private RequestController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void requestToJoinAGroupTest() throws Exception {
        ProfileDTO profile = new ProfileDTO();
        GroupDTO group = new GroupDTO();
        group.setIdGroup(2);
        profile.setIdProfile(5);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setGroup(group);
        requestDTO.setProfile(profile);
        requestDTO.setStatus(Status.SENT);

        mvc.perform(post("/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Saved record")));
    }

    @Test
    public void acceptUserTest() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setStatus(Status.ADMITTED);

        mvc.perform(put("/requests/2/5/acceptUser/administrators/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated record")));
    }
}
