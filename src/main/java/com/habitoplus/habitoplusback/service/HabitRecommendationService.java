package com.habitoplus.habitoplusback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.habitoplus.habitoplusback.model.Profile;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HabitRecommendationService {
    @Value("${api.key.OpenIA}")
    private String keyAPI;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private RestTemplate restTemplate;

    public String getRecommendations(Integer idProfile) {
        Profile profile = profileService.getProfileById(idProfile);
        if (profile == null) {
            return "Perfil no encontrado.";
        }

        String prompt = "Generate a list of 3 specific habits that you would recommend, expressed in concise phrases like 'Daily exercise routine', considering the following preferences and profile description:\n"
                + "Preferences: " + profile.getPreferences() + ".\n"
                + "Description: " + profile.getDescription() + ".\n"
                + "Make sure each habit is clear and easy to understand.";

        JsonObject body = new JsonObject();
        body.addProperty("model", "gpt-3.5-turbo");
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", prompt);
        JsonArray messagesArray = new JsonArray();
        messagesArray.add(message);
        body.add("messages", messagesArray);
        String jsonString = body.toString();

        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(keyAPI);

        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        JsonObject responseBody = new Gson().fromJson(response.getBody(), JsonObject.class);
        String recommendations = responseBody.getAsJsonArray("choices")
                .get(0).getAsJsonObject()
                .getAsJsonObject("message")
                .get("content").getAsString();

        return recommendations;
    }

}
