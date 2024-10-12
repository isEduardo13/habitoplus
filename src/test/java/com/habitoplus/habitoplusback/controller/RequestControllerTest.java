package com.habitoplus.habitoplusback.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.habitoplus.habitoplusback.Controller.RequestController;

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
    public void getAllTest() throws Exception {
        mvc.perform(get("/requests").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    // @Test
    // public void getByIdTest() throws Exception {
    //     mvc.perform(get("/requests/2").accept(MediaType.APPLICATION_JSON)).andDo(print())
    //             .andExpect(status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("$.idTejido", is(2)));
    // }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        mvc.perform(get("/requests/0/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }
}
