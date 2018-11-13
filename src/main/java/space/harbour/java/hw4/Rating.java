package space.harbour.java.hw4;

import javax.json.*;

public class Rating implements Jsonable {
    private String source;
    private String value;

	public String getSource() {
		return source;
	}

	public String getValue() {
		return value;
	}

	private Integer votes;

    public void fromJsonObject(JsonObject object) {
        this.source = object.getString("Source");
        this.value = object.getString("Value");

        if(object.containsKey("Votes"))
            this.votes = object.getInt("Votes");
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
            .add("Source", source)
            .add("Value", value);

        if(votes != null)
            builder.add("Votes", votes);
            
        return builder.build();
    }
}