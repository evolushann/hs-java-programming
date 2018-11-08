package space.harbour.java.hw4;

import javax.json.*;

public class Director implements Jsonable{
    public String name;

    public void fromJsonObject(JsonObject object) {
        this.name = object.getString("Name");
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
            .add("Name", name)
            .build();
    }
}