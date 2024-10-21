package com.habitoplus.habitoplusback.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PixelaService {

    private final String pixelaUrl = "https://pixe.la/v1/users";

    public boolean createPixelaAccount(String email, String token, boolean isNotMinor, String thanksCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = pixelaUrl;

        // Generar un nombre de usuario válido a partir del email
        String username = generateValidUsername(email);

        // Valida el token y el nombre de usuario
        if (!isValidToken(token) || !isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid token or username format.");
        }

        // Crear el JSON para la solicitud, siguiendo el formato exacto esperado por la API de Pixela
        String requestJson = createRequestJson(token, username, isNotMinor, thanksCode);

        // Crear encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Crear la solicitud HTTP
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        // Enviar la solicitud
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // Verificar si la respuesta fue exitosa
        return response.getStatusCode().is2xxSuccessful();
    }

    // Método separado para crear el JSON de la solicitud en formato correcto
    private String createRequestJson(String token, String username, boolean isNotMinor, String thanksCode) {
        // Crear el JSON con los valores correctos según la documentación de Pixela
        return String.format(
            "{\"token\":\"%s\", \"username\":\"%s\", \"agreeTermsOfService\":\"yes\", \"notMinor\":\"%s\"%s}",
            escapeJson(token), escapeJson(username), isNotMinor ? "yes" : "no",
            (thanksCode != null && !thanksCode.isEmpty()) ? String.format(", \"thanksCode\":\"%s\"", escapeJson(thanksCode)) : ""
        );
    }

    // Valida el token
    private boolean isValidToken(String token) {
        return token != null && token.length() >= 8 && token.length() <= 128 && token.matches("[ -~]+");
    }

    // Valida el nombre de usuario
    private boolean isValidUsername(String username) {
        return username != null && username.matches("[a-z][a-z0-9-]{1,32}");
    }

    // Genera un nombre de usuario válido a partir del email
    private String generateValidUsername(String email) {
        String username = email.split("@")[0].toLowerCase();
        return username.replaceAll("[^a-z0-9-]", "-");
    }

    // Método para escapar caracteres especiales en JSON
    private String escapeJson(String input) {
        return input.replace("\"", "\\\"").replace("\n", "\\n");
    }
}


