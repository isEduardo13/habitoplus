package com.habitoplus.habitoplusback.controller;
import org.springframework.security.core.Authentication;
import static org.mockito.Mockito.when;
import java.util.Collections;
import com.habitoplus.habitoplusback.model.Habit;
import com.habitoplus.habitoplusback.model.Note;
import com.habitoplus.habitoplusback.model.Notification;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.web.servlet.MockMvc;

import com.habitoplus.habitoplusback.component.AuthenticationTokenFilter;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.service.HabitService;
import com.habitoplus.habitoplusback.service.JsonWebTokenService;
import com.habitoplus.habitoplusback.service.NoteService;
import com.habitoplus.habitoplusback.service.NotificationService;
import com.habitoplus.habitoplusback.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;

import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
@AutoConfigureMockMvc
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @MockBean
    private HabitService habitService;

    @MockBean
    private NoteService noteService;

    @MockBean
    private NotificationService notificationService;

    // Agregamos los beans necesarios para la seguridad
    @MockBean
    private JsonWebTokenService jsonWebTokenService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AuthenticationManager authenticationManager;


    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_TOKEN = "Bearer fake-jwt-token";

    @TestConfiguration
    static class SecurityTestConfig {
        @Bean
        public AuthenticationTokenFilter authenticationTokenFilter(
                JsonWebTokenService tokenService,
                UserDetailsService userDetailsService) {
            return new AuthenticationTokenFilter(tokenService, userDetailsService);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/profiles/**").permitAll()  // Permitir todas las rutas bajo /profiles
                    .anyRequest().authenticated()
                )
                .addFilterBefore(
                    authenticationTokenFilter(null, null),
                    UsernamePasswordAuthenticationFilter.class
                );
            return http.build();
        }
    }

    @BeforeEach
    void setUp() {
        // Configurar un usuario de prueba con autoridades específicas
        List<SimpleGrantedAuthority> authorities = Arrays.asList(
            new SimpleGrantedAuthority("ROLE_USER")
        );
        
        UserDetails userDetails = new User(
            "testUser",
            "password",
            authorities
        );
        
        // Configurar el comportamiento del token
        when(jsonWebTokenService.isTokenValid(anyString(), any(UserDetails.class))).thenReturn(true);
        when(jsonWebTokenService.getUserEmailFromToken(anyString())).thenReturn("testUser");
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        
        // Configurar la autenticación
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
    @AfterEach
    void tearDown() {
        // Limpiar el contexto de seguridad después de cada prueba
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetAllProfiles() throws Exception {
        Profile profile1 = new Profile();
        profile1.setIdProfile(1);
        profile1.setName("Test");

        List<Profile> profiles = Arrays.asList(profile1);
        when(profileService.getAllProfiles()).thenReturn(profiles);

        mockMvc.perform(get("/profiles")
                .header("Authorization", "Bearer fake-jwt-token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idProfile").value(1))
                .andExpect(jsonPath("$[0].name").value("Test"));
    }



    @Test
    void testGetHabitsByProfileId() throws Exception {
        Habit habit1 = new Habit();
        habit1.setIdHabit(1);
        habit1.setDescription("5km run every morning");

        Habit habit2 = new Habit();
        habit2.setIdHabit(2);
        habit2.setDescription("Read 30 minutes daily");

        List<Habit> habits = Arrays.asList(habit1, habit2);
        when(habitService.getHabitsByProfile(1)).thenReturn(habits);

        mockMvc.perform(get("/profiles/1/habits")
                .header(AUTH_HEADER, BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idHabit").value(1))
                .andExpect(jsonPath("$[1].idHabit").value(2))
                .andExpect(jsonPath("$[0].description").value("5km run every morning"))
                .andExpect(jsonPath("$[1].description").value("Read 30 minutes daily"));

    }

    @Test
    void testGetNotesByProfileId() throws Exception {
        Note note1 = new Note();
        note1.setIdNote(1);
        note1.setContent("Important meeting tomorrow");
        note1.setTitle("Meeting Note");

        Note note2 = new Note();
        note2.setIdNote(2);
        note2.setContent("Buy groceries");
        note2.setTitle("Shopping List");

        List<Note> notes = Arrays.asList(note1, note2);
        when(noteService.getNotesByProfileId(1)).thenReturn(notes);

        mockMvc.perform(get("/profiles/1/notes")
                .header(AUTH_HEADER, BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idNote").value(1))
                .andExpect(jsonPath("$[0].title").value("Meeting Note"))
                .andExpect(jsonPath("$[1].idNote").value(2))
                .andExpect(jsonPath("$[1].title").value("Shopping List"));
    }

    @Test
    void testGetNotificationsByProfileId() throws Exception {
        Notification notif1 = new Notification();
        notif1.setId(1);
        notif1.setMessage("New habit streak achieved!");
        notif1.setType("ACHIEVEMENT");

        Notification notif2 = new Notification();
        notif2.setId(2);
        notif2.setMessage("Don't forget your evening meditation");
        notif2.setType("REMINDER");

        List<Notification> notifications = Arrays.asList(notif1, notif2);
        when(notificationService.getNotificationsByProfileId(1)).thenReturn(notifications);

        mockMvc.perform(get("/profiles/1/notifications")
                .header(AUTH_HEADER, BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].message").value("New habit streak achieved!"))
                .andExpect(jsonPath("$[0].type").value("ACHIEVEMENT"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].type").value("REMINDER"));
    }


    @Test
    void testGetHabitsByProfileIdEmptyList() throws Exception {
        when(habitService.getHabitsByProfile(1)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/profiles/1/habits")
                .header(AUTH_HEADER, BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }




}