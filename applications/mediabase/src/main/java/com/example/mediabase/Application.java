package com.example.mediabase;

import com.example.mediabase.moviesui.ActionServlet;
import com.example.mediabase.moviesui.MovieClient;
import com.example.mediabase.podcastsui.PodcastClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class Application {

    private String moviesURL = "//movies-ms/movies";
    private String podcastsURL = "//podcasts-ms/podcasts";

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean registerActionServlet(ActionServlet actionServlet) {
        return new ServletRegistrationBean(actionServlet, "/moviefun/*");
    }

    @Bean
    public MovieClient movieClient(RestOperations restOperations) {
        return new MovieClient(moviesURL, restOperations);
    }

    @Bean
    public PodcastClient podcastClient(RestOperations restOperations) {
        return new PodcastClient(podcastsURL, restOperations);
    }
}