package com.habitoplus.habitoplusback.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitoplus.habitoplusback.model.Comment;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CommentController controller;

    @Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

    @Test
    public void sendCommentTest() throws Exception {
        Comment comment = new Comment();
        comment.setIdProfile(2);
        comment.setIdGroup(2);
        comment.setContent("Holaaaa");


        mvc.perform(post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comment)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Saved record")));
    }

    @Test
    public void editCommentTest() throws Exception {
        Comment comment = new Comment();
        comment.setContent("Hola que tal ");

        mvc.perform(put("/comments/2/2/2024-10-23T04:46:50.15")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comment)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated record")));
    }

    @Test
    public void deleteCommentTest() throws Exception {
        mvc.perform(delete("/comments/2/2/2024-10-23T04:46:50.15").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted record")));
    }
}
