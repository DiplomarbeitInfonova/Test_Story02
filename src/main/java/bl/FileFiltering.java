/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bl;

import beans.Leg;
import beans.Location;
import java.net.MalformedURLException;
import org.json.JSONException;


/**
 *
 * @author David
 */
public class FileFiltering {

    public FileFiltering() {
        
    }
    
    public void inputMissingColumns(Leg l, Location[] locs) throws MalformedURLException, JSONException
    { 
         String url="http://maps.googleapis.com/maps/api/directions/json?origin=" + locs[0].getLatitude() + "," + locs[0].getLongitude() + "&destination=" + locs[1].getLatitude() + "," + locs[1].getLongitude() + "";
            TextURL texturl = new TextURL(url);
            String response=texturl.read();
            JSONParser jsonparser = new JSONParser(response);
            l.setDistance(Double.parseDouble(jsonparser.getDistanceFromJSON().split(" ")[0]));
    }
    
}
