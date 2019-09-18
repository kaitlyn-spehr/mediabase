package com.example.mediabase.podcastsui;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

public class PodcastClient {
    private static ParameterizedTypeReference<List<PodcastUI>> podcastListType = new ParameterizedTypeReference<List<PodcastUI>>() {
    };
    private RestOperations restOperations;
    private String podcastsURL;


    public PodcastClient(String podcastsURL, RestOperations restOperations) {
        this.restOperations = restOperations;
        this.podcastsURL = podcastsURL;
    }

    public void create(PodcastUI podcast) {
        restOperations.postForEntity(podcastsURL, podcast, PodcastUI.class);
    }

    public List<PodcastUI> getAll() {
        return restOperations.exchange(podcastsURL, HttpMethod.GET, null, podcastListType).getBody();
    }
}

