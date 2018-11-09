package com.hsjavaclass.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class GenderDetector {
    public String detectGenderByName(String name) {
        try {
            URL url = new URL("https://api.genderize.io/?name=" + name);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jsonReader = Json.createReader(br);
            JsonObject json = jsonReader.readObject();
            jsonReader.close();

            return json.getString("gender");
        } catch(Exception e) {
            System.out.println(e);
        }
        
        return "male";
    }
}