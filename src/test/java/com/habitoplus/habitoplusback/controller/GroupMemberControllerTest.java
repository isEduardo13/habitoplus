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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitoplus.habitoplusback.dto.GroupDTO;
import com.habitoplus.habitoplusback.dto.GroupMemberDTO;
import com.habitoplus.habitoplusback.dto.ProfileDTO;
import com.habitoplus.habitoplusback.enums.Role;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupMemberControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private GroupMemberController controller;

    @Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

    @Test
    public void addUserToGroupTest() throws Exception {
        ProfileDTO profile = new ProfileDTO();
        GroupDTO group = new GroupDTO();
        GroupMemberDTO memberDTO = new GroupMemberDTO();
        group.setIdGroup(2);
        profile.setIdProfile(5);
        memberDTO.setGroup(group);
        memberDTO.setProfile(profile);
        profile.setIdProfile(5);
        memberDTO.setRole(Role.MEMBER);

        mvc.perform(post("/groupMembers/administrators/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(memberDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Saved record")));
    }

    @Test
    public void leaveGroupTest() throws Exception {
        mvc.perform(delete("/groupMembers/2/5").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted record")));
    }

    @Test
    public void deleteUserFromGroupTest() throws Exception {
        mvc.perform(delete("/groupMembers/2/5/administrators/2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted record")));
    }


}
