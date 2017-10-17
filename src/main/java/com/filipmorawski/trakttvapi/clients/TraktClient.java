package com.filipmorawski.trakttvapi.clients;

import com.filipmorawski.trakttvapi.domain.JsonParse;
import com.filipmorawski.trakttvapi.domain.Movie;
import com.filipmorawski.trakttvapi.repos.MovieCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class TraktClient {

    private String id;
    private RestTemplate restTemplate;
    private String action;
    private ArrayList<String> movieCommand;
    private final String apiKey = "4c8ade9fec630294cc0b09916ef5fbb81a25b9f1015f79d65f43608071824510";
    private final String contentType = "application/json";
    private final String traktApiVersion = "2";
    private final String apiUrl = "https://api.trakt.tv/";
    private final String mediaType = "/movies";
    private HttpHeaders headers;
    private HttpEntity<String> entity;
    @Autowired
    private JsonParse jsonParse;


    public TraktClient() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", contentType);
        headers.add("trakt-api-version", traktApiVersion);
        headers.add("trakt-api-key", apiKey);
        this.movieCommand = new MovieCommands()
                .getMovieCommands();
        entity = new HttpEntity<>(this.headers);
    }

    public ArrayList<Movie> getUserData(String user, String command) {
        id = user;
        action = "/" + command;
        String query;
        if (movieCommand.contains(command)) {
            query = apiUrl + "users/" + id + action + mediaType;
        } else {
            query = apiUrl + "users/" + id + action;
        }
        ResponseEntity<String> exchange = restTemplate.exchange(query, HttpMethod.GET, entity, String.class);
        return jsonParse.parseData(exchange);
    }

    public ArrayList<Movie> getMoviesData(String command) {
        action = "/" + command;
        String query = apiUrl + mediaType + action;
        ResponseEntity<String> exchange = restTemplate.exchange(query, HttpMethod.GET, entity, String.class);
        return jsonParse.parseData(exchange);
    }
}
