package com.dusktildwan.playlistgenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PlaylistGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistGeneratorApplication.class, args);
	}
}
