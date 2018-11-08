package com.hsjavaclass.serialization;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ExampleValue implements Jsonable {
    public Integer i = 10;
    private String s = "ABC";
    protected float f = .9f;
    private InsideClass hiddenClass = new InsideClass();

    class InsideClass implements Jsonable {
        String s = "XYZ";
        Integer i = 69;

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                .add("s", s)
                .add("i", i)
                .build();
        }

        public void fromJson(String json) {
            JsonReader jsonReader = Json.createReader(new StringReader(json));
            JsonObject object = jsonReader.readObject();
            jsonReader.close();
    
            this.i = object.getInt("i");
            this.s = object.getString("s");
        }
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
            .add("i", i)
            .add("s", s)
            .add("f", f)
            .add("hiddenClass", hiddenClass.toJsonObject())
            .build();
    }

    public void fromJson(String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        this.i = object.getInt("i");
        this.s = object.getString("s");
        this.f = (float) object.getJsonNumber("f").doubleValue();
        this.hiddenClass = new InsideClass();

        hiddenClass.fromJson(object.getJsonObject("hiddenClass").toString());
    }

    public static void main(String[] args) {
        ExampleValue e = new ExampleValue();
        System.out.println(e.toJsonString());
    }
}