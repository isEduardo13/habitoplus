package com.habitoplus.habitoplusback.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitoplus.habitoplusback.dto.GroupDTO;
import com.habitoplus.habitoplusback.enums.GroupType;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private GroupController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @DisplayName("Consult groups test")
    @Test
    public void consultGroupsTest() throws Exception {
        mvc.perform(get("/groups").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void consultGroupsPaginatedTest() throws Exception {
        mvc.perform(get("/groups/pagination/1/5").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void consultGroupTest() throws Exception {
        mvc.perform(get("/groups/2").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idGroup", is(2)));
    }

    @Test
    public void consultGroupNotFoundTest() throws Exception {
        mvc.perform(get("/groups/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested resource was not found")));
    }

    @Test
    public void createGroupTest() throws Exception {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName("Test Group");
        groupDTO.setDescription("Group to Test");
        groupDTO.setGroupType(GroupType.PRIVATE);

        mvc.perform(post("/groups/profiles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(groupDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Saved record")));
    }

    @Test
    public void updateGroupInformationTest() throws Exception {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName("Test Group");
        groupDTO.setDescription("Update Group to Test");
        groupDTO.setGroupType(GroupType.PRIVATE);

        mvc.perform(put("/groups/14/administrators/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(groupDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated record")));
    }

    @Test
    public void deleteGroupTest() throws Exception {
        mvc.perform(delete("/groups/14/administrators/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted record")));
    }

    @Test
    public void consultTheGroupUsersTest() throws Exception {
        mvc.perform(get("/groups/2/groupMembers").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void consultRequestsTest() throws Exception {
        mvc.perform(get("/groups/2/requests/administrators/2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void consultCommentsTest() throws Exception {
        mvc.perform(get("/groups/2/comments").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

}
