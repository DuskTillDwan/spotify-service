package com.dusktildwan.playlistgenerator;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class SpotifyApiService {

    private final SpotifyAuthService spotifyAuthService;
    private final RestTemplate restTemplate = new RestTemplate();

    public SpotifyApiService(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
    }

    public HttpStatusCode addSongToPlaylist(String playlistId, String songUri) throws RuntimeException {
        String url = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
        String accessToken = spotifyAuthService.getAccessToken();

        if (accessToken == null) {
            throw new RuntimeException("Access token is missing.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = "{ \"uris\": [\"" + songUri + "\"] }";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        return response.getStatusCode();
    }
}

