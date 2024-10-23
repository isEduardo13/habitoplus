package com.habitoplus.habitoplusback.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.habitoplus.habitoplusback.exception.InvalidPixelaDataException;
import com.habitoplus.habitoplusback.exception.PixelaAccountCreationException;

@Service
public class PixelaService {

    private final String pixelaUrl = "https://pixe.la/v1/users";

    public boolean createPixelaAccount(String email, String token, boolean isNotMinor, String thanksCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = pixelaUrl;
        String username = generateValidUsername(email);

        // Validar el token y el username
        if (!isValidToken(token) || !isValidUsername(username)) {
            throw new InvalidPixelaDataException("Invalid token or username format.");
        }

        String requestJson = createRequestJson(token, username, isNotMinor, thanksCode);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                System.out.println("El usuario ya existe en Pixela, continuando con el proceso.");
                return true; 
            } else {
                throw new PixelaAccountCreationException("Failed to create Pixela account: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new PixelaAccountCreationException(
                    "Unexpected error while creating Pixela account: " + e.getMessage(), e);
        }
    }

    private String createRequestJson(String token, String username, boolean isNotMinor, String thanksCode) {
        return String.format(
                "{\"token\":\"%s\", \"username\":\"%s\", \"agreeTermsOfService\":\"yes\", \"notMinor\":\"%s\"%s}",
                escapeJson(token), escapeJson(username), isNotMinor ? "yes" : "no",
                (thanksCode != null && !thanksCode.isEmpty())
                        ? String.format(", \"thanksCode\":\"%s\"", escapeJson(thanksCode))
                        : "");
    }

    private boolean isValidToken(String token) {
        return token != null && token.length() >= 8 && token.length() <= 128 && token.matches("[ -~]+");
    }

    private boolean isValidUsername(String username) {
        return username != null && username.matches("[a-z][a-z0-9-]{1,32}");
    }

    private String generateValidUsername(String email) {
        String username = email.split("@")[0].toLowerCase();
        return username.replaceAll("[^a-z0-9-]", "-");
    }

    private String escapeJson(String input) {
        return input.replace("\"", "\\\"").replace("\n", "\\n");
    }
}
