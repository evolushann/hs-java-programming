package space.harbour.java.hw4;

import javax.json.*;

public class Actor implements Jsonable {
    public String name;
    public String as;

    public void fromJsonObject(JsonObject object) {
        this.name = object.getString("Name");
        this.as = object.getString("As");
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
            .add("Name", name)
            .add("As", as)
            .build();
    }
}