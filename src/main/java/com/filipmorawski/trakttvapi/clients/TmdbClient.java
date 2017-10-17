package com.filipmorawski.trakttvapi.clients;

import com.filipmorawski.trakttvapi.domain.TmdbParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TmdbClient {
    private RestTemplate restTemplate;
    private final String apiKey = "1c369d32002391b37504a9f0dc0f23e6";
    private final String queryUrlPart1 = "https://api.themoviedb.org/3/movie/";
    private final String queryUrlPart2 = "/images?api_key=";
    private final String imageUrl = "https://image.tmdb.org/t/p/w185";
    @Autowired
    private TmdbParse tmdbParse;

    public String getPosterUrl(String tmdbId) {
        restTemplate = new RestTemplate();
        String query = queryUrlPart1 + tmdbId + queryUrlPart2 + apiKey;
        String receivedData = restTemplate.getForObject(query, String.class);
        String jpgUrl = tmdbParse.getPoster(receivedData);
        String posterUrl = imageUrl + jpgUrl;
        return posterUrl;
    }
}
