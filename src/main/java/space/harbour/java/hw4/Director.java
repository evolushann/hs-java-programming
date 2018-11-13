package space.harbour.java.hw4;

import javax.json.*;

public class Director implements Jsonable{
    private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}