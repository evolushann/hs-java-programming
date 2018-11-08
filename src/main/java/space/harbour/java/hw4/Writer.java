package space.harbour.java.hw4;

import javax.json.*;

public class Writer implements Jsonable {
    public String name;
    public String type;

    public void fromJsonObject(JsonObject object) {
        this.name = object.getString("Name");
        this.type = object.getString("Type");
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
            .add("Name", name)
            .add("Type", type)
            .build();
    }
}