package com.dusktildwan.playlistgenerator;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class AuthController {

    private final SpotifyAuthService spotifyAuthService;
    private final SpotifyConfig spotifyConfig;

    public AuthController(SpotifyAuthService spotifyAuthService, SpotifyConfig spotifyConfig) {
        this.spotifyAuthService = spotifyAuthService;
        this.spotifyConfig = spotifyConfig;
    }
    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        String authorizationUrl = "https://accounts.spotify.com/authorize?" +
                "client_id=" + spotifyConfig.getId() + "&" +
                "response_type=code&" +
                "redirect_uri=" + URLEncoder.encode(spotifyConfig.getRedirectUrl(), StandardCharsets.UTF_8) + "&" +
                "scope=user-library-read user-library-modify playlist-modify-public playlist-modify-private";
        response.sendRedirect(authorizationUrl);
    }

    @GetMapping("/callback")
    public String handleRedirect(@RequestParam("code") String code) {
        System.out.println("Received authorization code: " + code);
        // Now exchange the authorization code for an access token
        try {
            spotifyAuthService.exchangeCodeForToken(code);
            return "Token received: " + spotifyAuthService.getAccessToken();  // Or redirect to another page or handle it as needed
        } catch (Exception e) {
            return "Error exchanging code for access token: " + e.getMessage();
        }
    }

}


