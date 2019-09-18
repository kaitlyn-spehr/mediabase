package com.example.mediabase;

import com.example.mediabase.moviesui.MovieClient;
import com.example.mediabase.moviesui.MoviesInitialList;
import com.example.mediabase.podcastsui.PodcastClient;
import com.example.mediabase.podcastsui.PodcastsInitialList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RootController {
    private MovieClient movieClient;
    private PodcastClient podcastClient;

    public RootController(MovieClient movieClient, PodcastClient podcastClient) {
        this.movieClient = movieClient;
        this.podcastClient = podcastClient;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model, MoviesInitialList moviesInitialList, PodcastsInitialList podcastsInitialList) {
        moviesInitialList.asList().forEach(movieClient::create);
        podcastsInitialList.asList().forEach(podcastClient::create);
        model.put("movies", movieClient.getAll());
        model.put("podcasts", podcastClient.getAll());

        return "setup";
    }

}
