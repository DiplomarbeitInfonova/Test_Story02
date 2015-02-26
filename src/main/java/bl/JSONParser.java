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
        //String duration = obj.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("duration").getString("text");

        return distance;
//        String distance = parsingString.substring(parsingString.indexOf("text") + 7, parsingString.indexOf("km"));
//        distance = distance.trim().substring(1);
//        return distance;
    }

}
