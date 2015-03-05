package bl;

import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONParser {

    private String parsingString;

    public JSONParser(String parsingString) {
        this.parsingString = parsingString;
    }

    public String getDistanceFromJSON() throws JSONException {

        JSONObject obj = new JSONObject(parsingString);
        String distance = obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");
        return distance;
    }

}
