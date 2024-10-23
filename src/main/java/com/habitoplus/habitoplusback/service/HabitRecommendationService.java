package com.habitoplus.habitoplusback.service;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        // Generar el prompt
        String prompt = "Generate a list of 3 specific habits that you would recommend, expressed in concise phrases like 'Daily exercise routine', considering the following preferences and profile description:\n"
                +
                "Preferences: " + profile.getPreferences() + ".\n" +
                "Description: " + profile.getDescription() + ".\n" +
                "Make sure each habit is clear and easy to understand.";

        // Realizar llamada a la API
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(keyAPI);
        // Crear el cuerpo de la solicitud
        JSONObject body = new JSONObject();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", new JSONArray().put(new JSONObject()
                .put("role", "user")
                .put("content", prompt)));

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            // Procesar la respuesta
            JSONObject responseBody = new JSONObject(response.getBody());
            String recommendations = responseBody.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            // Retornar la respuesta
            return recommendations;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al obtener recomendaciones: " + e.getMessage();
        }
    }

}
