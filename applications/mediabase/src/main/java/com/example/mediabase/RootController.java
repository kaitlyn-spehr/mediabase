package com.example.mediabase;

import com.example.mediabase.movies.MoviesBean;
import com.example.mediabase.movies.MoviesInitialList;
import com.example.mediabase.podcasts.PodcastRepository;
import com.example.mediabase.podcasts.PodcastsInitialList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RootController {
    private MoviesBean moviesBean;
    private PodcastRepository podcastRepository;

    public RootController(MoviesBean moviesBean, PodcastRepository podcastRepository) {
        this.moviesBean = moviesBean;
        this.podcastRepository = podcastRepository;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model, MoviesInitialList moviesInitialList, PodcastsInitialList podcastsInitialList) {
        moviesInitialList.asList().forEach(moviesBean::addMovie);
        podcastsInitialList.asList().forEach(podcastRepository::save);
        model.put("movies", moviesBean.getMovies());
        model.put("podcasts", podcastRepository.findAll());

        return "setup";
    }

}
