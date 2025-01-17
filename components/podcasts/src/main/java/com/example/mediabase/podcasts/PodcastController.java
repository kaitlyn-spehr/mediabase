package com.example.mediabase.podcasts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {

    private PodcastRepository podcastRepository;

    public PodcastController(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @PostMapping
    public ResponseEntity<Podcast> create(@RequestBody Podcast podcast) {
        podcastRepository.save(podcast);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public List<Podcast> read(){
        List<Podcast> ret = new ArrayList<>();
        podcastRepository.findAll().forEach(ret::add);
        return ret;
    }
}
