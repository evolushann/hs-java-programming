package space.harbour.java.hw4;

import javax.json.*;

public class Actor implements Jsonable {
    private String name;
    private String as;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}