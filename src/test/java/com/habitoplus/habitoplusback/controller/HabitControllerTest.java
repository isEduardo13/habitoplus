package com.habitoplus.habitoplusback.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.Assertions;

import com.habitoplus.habitoplusback.dto.HabitDTO;
import com.habitoplus.habitoplusback.enums.Priority;
import com.habitoplus.habitoplusback.service.HabitService;


import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    
    @Mock
    private HabitService habitService;

    @Autowired
    
    private HabitController habitController;



    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(this.habitController).isNotNull();
    }

    @Test
    public void getAllHabitsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/habits")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))));
    }

    @Test
    public void registerHabitTest() throws Exception {
        HabitDTO habitDTO = new HabitDTO();
        habitDTO.setHabitName("New Habit");
        habitDTO.setDescription("Description for the new habit");
        habitDTO.setPriority(Priority.LOW);
        habitDTO.setCategoryId(1);
        habitDTO.setStreakId(1);
        habitDTO.setProfileId(1);

        mockMvc.perform(post("/habits/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(habitDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Saved Successfully"));
    }

    @Test
    public void getByHabitIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/habits/11")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idHabit", CoreMatchers.is(11)));
    }

    @Test
    public void getByHabitIdNotFoundTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/habits/999")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("The requested resource was not found")));
    }

    @Test
    public void updateHabitTest() throws Exception {
        HabitDTO habitDTO = new HabitDTO();
        habitDTO.setHabitName("Updated Habit");
        habitDTO.setDescription("Updated description for the habit");
        habitDTO.setPriority(Priority.MEDIUM);
        habitDTO.setCategoryId(1); 
        habitDTO.setStreakId(1); 
        habitDTO.setProfileId(1);
 
        mockMvc.perform(MockMvcRequestBuilders.put("/habits/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(habitDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Habit updated successfully"));
    }
    
    @Test
    public void deleteHabitTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/habits/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted record"));
    }

    @Test
    public void updateHabitStatusTest() throws Exception {
        Boolean newStatus = true;

        mockMvc.perform(MockMvcRequestBuilders.put("/habits/4/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newStatus)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Updated status"));
    }

    @Test
    public void getHabitsByCategoryTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/habits/1/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))));
    }

    @Test
    public void getHabitsByPriorityTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/habits/1/priority/MEDIUM")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))));
    }

}
