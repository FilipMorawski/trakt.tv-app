package com.filipmorawski.trakttvapi.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class TmdbParse {
    private JSONParser parser;

    public TmdbParse() {
        this.parser = new JSONParser();
    }

    public String getPoster(String receivedData) {
        String posterFilePath = null;
        try {
            Object receivedImages = parser.parse(receivedData);
            JSONObject jsonData = (JSONObject) receivedImages;
            JSONArray postersNode = (JSONArray) jsonData.get("posters");
            for (int i = 0; i < postersNode.size(); i++) {
                JSONObject poster = (JSONObject) postersNode.get(i);
                String language = (String) poster.get("iso_639_1");
                if (language == null || language.equals("en")) {
                    posterFilePath = (String) poster.get("file_path");
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return posterFilePath;

    }
}

