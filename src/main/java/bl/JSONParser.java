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
        String distance = obj.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getString("text");
        return distance;
    }

}
