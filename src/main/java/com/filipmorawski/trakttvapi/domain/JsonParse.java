package com.filipmorawski.trakttvapi.domain;

import com.filipmorawski.trakttvapi.clients.TmdbClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JsonParse {
    private ArrayList<Movie> moviesList;
    private JSONParser parser;
    @Autowired
    private TmdbClient tmdbClient;

    public JsonParse() {
        this.parser = new JSONParser();
    }

    public ArrayList<Movie> parseData(ResponseEntity<String> data) {
        this.moviesList = new ArrayList<>();
        try {
            String body = data.getBody();
            Object object = parser.parse(body);
            JSONArray receivedMovies = (JSONArray) object;

            for (int i = 0; i < receivedMovies.size(); i++) {
                Movie movie = new Movie();
                JSONObject receivedMovie = (JSONObject) receivedMovies.get(i);
                JSONObject movieNode = (JSONObject) receivedMovie.get("movie");
                if (movieNode == null) {
                    movieNode = (JSONObject) receivedMovies.get(i);
                }
                movie.setTitle((String) movieNode.get("title"));
                movie.setYear(String.valueOf(movieNode.get("year")));
                JSONObject idsNode = (JSONObject) movieNode.get("ids");
                movie.setImdb((String) idsNode.get("imdb"));
                String poster = tmdbClient.getPosterUrl(String.valueOf(idsNode.get("tmdb")));
                movie.setImdbUrl();
                movie.setPosterUrl(poster);
                this.moviesList.add(movie);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.moviesList;
    }
}


