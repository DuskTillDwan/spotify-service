package com.dusktildwan.spotifyservice;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {
    private final SpotifyApiService spotifyApiService;

    public SpotifyController(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    @PutMapping("/add-song")
    public ResponseEntity<String> addSongToPlaylist(@RequestParam String playlistId, @RequestParam String songId) {
        try {
            HttpStatusCode response = spotifyApiService.addSongToPlaylist(playlistId, songId);
            return new ResponseEntity<>("Song added successfully: " + response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add song: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


