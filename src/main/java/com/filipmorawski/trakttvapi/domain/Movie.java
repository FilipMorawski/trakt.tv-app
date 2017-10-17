package com.filipmorawski.trakttvapi.domain;

import org.springframework.stereotype.Component;

@Component
public class Movie {
    private String title;
    private String year;
    private String imdb;
    private String tmdb;

    private String posterUrl;
    private String imdbUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getTmdb() {
        return tmdb;
    }

    public void setTmdb(String tmdb) {
        this.tmdb = tmdb;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setImdbUrl() {
        String imdbUrl = "http://www.imdb.com/title/" + this.imdb + "/";
        this.imdbUrl = imdbUrl;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }
}
