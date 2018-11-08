package space.harbour.java.hw4;

import javax.json.*;
import java.io.StringReader;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.*;

public class BladeRunner implements Jsonable {
    public String title;
    public int year;
    public String released;
    public int runtime;
    public String[] genres;
    public Director director;
    public Writer[] writers;
    public Actor[] actors;
    public String plot;
    public String[] languages;
    public String[] countries;
    public String awards;
    public String poster;
    public Rating[] ratings;

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public JsonObject toJsonObject() {
        JsonArrayBuilder genresBuilder = Json.createArrayBuilder();
        for(String genre : genres)
            genresBuilder.add(genre);

        JsonArrayBuilder writersBuilder = Json.createArrayBuilder();
            for(Writer writer : writers)
                writersBuilder.add(writer.toJsonObject());

        JsonArrayBuilder actorsBuilder = Json.createArrayBuilder();
            for(Actor actor : actors)
                actorsBuilder.add(actor.toJsonObject());

        JsonArrayBuilder languagesBuilder = Json.createArrayBuilder();
            for(String language : languages)
                languagesBuilder.add(language);

        JsonArrayBuilder countriesBuilder = Json.createArrayBuilder();
            for(String country : countries)
                countriesBuilder.add(country);

        JsonArrayBuilder ratingsBuilder = Json.createArrayBuilder();
            for(Rating rating : ratings)
                ratingsBuilder.add(rating.toJsonObject());

		return Json.createObjectBuilder()
                .add("Title", title)
                .add("Year", year)
                .add("Released", released)
                .add("Runtime", runtime)
                .add("Genres", genresBuilder)
                .add("Director", director.toJsonObject())
                .add("Writers", writersBuilder)
                .add("Actors", actorsBuilder)
                .add("Plot", plot)
                .add("Languages", languagesBuilder)
                .add("Countries", countriesBuilder)
                .add("Awards", awards)
                .add("Poster", poster)
                .add("Ratings", ratingsBuilder)
                .build();
	}

    public void fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject object = reader.readObject();

        this.title = object.getString("Title");
        this.year = object.getInt("Year");
        this.released = object.getString("Released");
        this.runtime = object.getInt("Runtime");

        JsonArray array = object.getJsonArray("Genres");
        this.genres = new String[array.size()];
        for(int i = 0; i < array.size(); i ++) {
            this.genres[i] = array.getString(i);
        }

        this.director = new Director();
        this.director.fromJsonObject(object.getJsonObject("Director"));

        array = object.getJsonArray("Writers");
        this.writers = new Writer[array.size()];
        for(int i = 0; i < array.size(); i ++) {
            this.writers[i] = new Writer();
            this.writers[i].fromJsonObject(array.getJsonObject(i));
        }

        array = object.getJsonArray("Actors");
        this.actors = new Actor[array.size()];
        for(int i = 0; i < array.size(); i ++) {
            this.actors[i] = new Actor();
            this.actors[i].fromJsonObject(array.getJsonObject(i));
        }

        this.plot = object.getString("Plot");

        array = object.getJsonArray("Languages");
        this.languages = new String[array.size()];
        for(int i = 0; i < array.size(); i ++) {
            this.languages[i] = array.getString(i);
        }

        array = object.getJsonArray("Countries");
        this.countries = new String[array.size()];
        for(int i = 0; i < array.size(); i ++) {
            this.countries[i] = array.getString(i);
        }

        this.awards = object.getString("Awards");
        this.poster = object.getString("Poster");

        array = object.getJsonArray("Ratings");
        this.ratings = new Rating[array.size()];
        for(int i = 0; i < array.size(); i ++) {
            this.ratings[i] = new Rating();
            this.ratings[i].fromJsonObject(array.getJsonObject(i));
        }
    }

    public void readFile() {
        String json = "";

        try {
            List<String> lines = Files.readAllLines(Paths.get("BladeRunner.json"), Charset.defaultCharset());

            for (String line : lines) {
                json += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(json.length() != 0)
            fromJson(json);
    }

    public static void main(String[] args) {
        BladeRunner br = new BladeRunner();
        
        br.readFile();

        System.out.println(br.toJsonString());
    }
}